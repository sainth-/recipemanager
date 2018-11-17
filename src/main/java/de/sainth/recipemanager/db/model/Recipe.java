package de.sainth.recipemanager.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
  private Long recipeId;
  private final String name;
  private String description;
  private final short portions;
  private List<Ingredient> ingredients;
  private List<Direction> directions;

  public Recipe(long recipeId,
                String name,
                String description,
                short portions,
                List<Ingredient> ingredients,
                List<Direction> directions) {
    this.recipeId = recipeId;
    this.name = name;
    this.description = description;
    this.portions = portions;
    this.ingredients = ingredients;
    this.directions = directions;
  }

  public Recipe(String name, short portions) {
    this.name = name;
    this.portions = portions;
    this.ingredients = new ArrayList<>();
    this.directions = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public Long getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(long recipeId) {
    this.recipeId = recipeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public short getPortions() {
    return portions;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Direction> getDirections() {
    return directions;
  }

  public void setDirections(List<Direction> directions) {
    this.directions = directions;
  }
}
