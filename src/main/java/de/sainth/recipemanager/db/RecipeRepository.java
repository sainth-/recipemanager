package de.sainth.recipemanager.db;

import static de.sainth.recipemanager.db.generated.tables.Direction.DIRECTION;
import static de.sainth.recipemanager.db.generated.tables.Ingredient.INGREDIENT;
import static de.sainth.recipemanager.db.generated.tables.Recipe.RECIPE;

import java.util.List;

import de.sainth.recipemanager.db.generated.tables.records.RecipeRecord;
import de.sainth.recipemanager.db.model.Direction;
import de.sainth.recipemanager.db.model.Ingredient;
import de.sainth.recipemanager.db.model.OverviewRecipe;
import de.sainth.recipemanager.db.model.Recipe;
import de.sainth.recipemanager.db.model.Unit;

import org.jooq.BatchBindStep;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeRepository {
  private final DSLContext create;

  @Autowired
  public RecipeRepository(DSLContext create) {
    this.create = create;
  }

  public void add(Recipe recipe) {
    if (recipe.getRecipeId() == null) {
      create.transaction(configuration -> {
        Long recipeId = DSL.using(configuration)
                           .insertInto(RECIPE)
                           .set(RECIPE.NAME, recipe.getName())
                           .set(RECIPE.DESCRIPTION, recipe.getDescription())
                           .set(RECIPE.PORTIONS, recipe.getPortions())
                           .returning(RECIPE.RECIPE_ID)
                           .fetchOne()
                           .getRecipeId();
        insertIngredients(configuration, recipeId, recipe.getIngredients());
        insertDirections(configuration, recipeId, recipe.getDirections());
      });
    }
  }

  public void remove(Recipe recipe) {
    create.deleteFrom(RECIPE)
          .where(RECIPE.RECIPE_ID.eq(recipe.getRecipeId()))
          .execute();
  }

  public void update(Recipe recipe) {
    if (recipe.getRecipeId() != null) {
      create.transaction(configuration -> {
        DSL.using(configuration)
           .update(RECIPE)
           .set(RECIPE.NAME, recipe.getName())
           .set(RECIPE.DESCRIPTION, recipe.getDescription())
           .set(RECIPE.PORTIONS, recipe.getPortions())
           .where(RECIPE.RECIPE_ID.eq(recipe.getRecipeId()))
           .execute();
        deleteIngredients(configuration, recipe.getRecipeId());
        insertIngredients(configuration, recipe.getRecipeId(), recipe.getIngredients());
        deleteDirections(configuration, recipe.getRecipeId());
        insertDirections(configuration, recipe.getRecipeId(), recipe.getDirections());
      });
    }
  }

  public Recipe query(long recipeId) {
    RecipeRecord recipeRecord = create.selectFrom(RECIPE)
                                      .where(RECIPE.RECIPE_ID.eq(recipeId))
                                      .fetchOne();
    if (recipeRecord != null) {
      List<Ingredient> ingredients = create.selectFrom(INGREDIENT)
                                           .where(INGREDIENT.RECIPE_ID.eq(recipeId))
                                           .fetch()
                                           .map(record -> new Ingredient(record.getIngredientId(),
                                                                         record.getFoodName(),
                                                                         record.getAmount(),
                                                                         new Unit(record.getUnitName())));

      List<Direction> directions = create.selectFrom(DIRECTION)
                                         .where(DIRECTION.RECIPE_ID.eq(recipeId))
                                         .fetch()
                                         .map(record -> new Direction(record.getPosition(),
                                                                      record.getDescription()));
      return new Recipe(recipeRecord.getRecipeId(),
                        recipeRecord.getName(),
                        recipeRecord.getDescription(),
                        recipeRecord.getPortions(),
                        ingredients,
                        directions);
    }
    else {
      return null;
    }
  }

  public List<OverviewRecipe> overviewQuery() {
    return create.select(RECIPE.RECIPE_ID, RECIPE.NAME)
                 .from(RECIPE)
                 .fetch()
                 .map(record -> new OverviewRecipe(record.value1(), record.value2()));
  }
  
  private void deleteIngredients(Configuration configuration, long recipeId) {
    DSL.using(configuration)
       .deleteFrom(INGREDIENT)
       .where(INGREDIENT.RECIPE_ID.eq(recipeId))
       .execute();
  }

  private void deleteDirections(Configuration configuration, long recipeId) {
    DSL.using(configuration)
       .deleteFrom(DIRECTION)
       .where(DIRECTION.RECIPE_ID.eq(recipeId))
       .execute();
  }

  private void insertIngredients(Configuration configuration, Long recipeId, List<Ingredient> ingredients) {
    if (!ingredients.isEmpty()) {
      BatchBindStep batch = DSL.using(configuration)
                               .batch(DSL.using(configuration)
                                         .insertInto(INGREDIENT,
                                                     INGREDIENT.RECIPE_ID,
                                                     INGREDIENT.FOOD_NAME,
                                                     INGREDIENT.AMOUNT,
                                                     INGREDIENT.UNIT_NAME)
                                         .values((Long) null, null, null, null));
      for (Ingredient ingredient : ingredients) {
        batch.bind(recipeId,
                   ingredient.getFoodName(),
                   ingredient.getAmount(),
                   ingredient.getUnit() != null ? ingredient.getUnit().getName() : null);
      }
      batch.execute();
    }
  }

  private void insertDirections(Configuration configuration, Long recipeId, List<Direction> directions) {
    if (!directions.isEmpty()) {
      BatchBindStep batch = DSL.using(configuration)
                               .batch(DSL.using(configuration)
                                         .insertInto(DIRECTION,
                                                     DIRECTION.RECIPE_ID,
                                                     DIRECTION.POSITION,
                                                     DIRECTION.DESCRIPTION)
                                         .values((Long) null, null, null));
      for (Direction direction : directions) {
        batch.bind(recipeId,
                   direction.getPosition(),
                   direction.getDescription());
      }
      batch.execute();
    }
  }

}
