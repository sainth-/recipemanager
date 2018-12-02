/*
 * Copyright (C) 2018 Tobias Wink <sainth@sainth.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.sainth.recipemanager;

import java.util.List;
import java.util.Map;

import de.sainth.recipemanager.db.RecipeRepository;
import de.sainth.recipemanager.db.UnitDao;
import de.sainth.recipemanager.db.model.Direction;
import de.sainth.recipemanager.db.model.Ingredient;
import de.sainth.recipemanager.db.model.Recipe;
import de.sainth.recipemanager.db.model.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
  private final RecipeRepository recipeRepository;
  private final UnitDao unitDao;

  @Autowired
  public RecipeController(RecipeRepository recipeRepository, UnitDao unitDao) {
    this.recipeRepository = recipeRepository;
    this.unitDao = unitDao;
  }

  @GetMapping("/show")
  public ModelAndView showRecipe(@RequestParam long id, Map<String, Object> model) {
    Recipe recipe = recipeRepository.query(id);
    model.put("recipe", recipe);
    return new ModelAndView("recipe/show", model);
  }

  @GetMapping("/edit")
  public ModelAndView editRecipe(@RequestParam long id, Map<String, Object> model) {
    Recipe recipe = recipeRepository.query(id);
    padIngredients(recipe.getIngredients(), (short) 20);
    padDirections(recipe.getDirections(), (short) 1);
    model.put("recipe", recipe);
    List<Unit> units = unitDao.query();
    model.put("units", units);
    return new ModelAndView("recipe/upsert", model);
  }

  @GetMapping("/create")
  public ModelAndView createRecipe(Map<String, Object> model) {
    List<Unit> units = unitDao.query();
    model.put("units", units);
    Recipe recipe = new Recipe();
    padIngredients(recipe.getIngredients(), (short) 20);
    padDirections(recipe.getDirections(), (short) 1);
    model.put("recipe", recipe);
    return new ModelAndView("recipe/upsert", model);
  }

  @PostMapping("/create")
  public String create(@ModelAttribute Recipe recipe) {
    final List<Ingredient> ingredients = recipe.getIngredients();
    for (int i = 0; i < ingredients.size(); i++) {
      if (Util.isNullOrEmpty(ingredients.get(i).getFoodName())) {
        ingredients.remove(i--);
      }
    }
    final List<Direction> directions = recipe.getDirections();
    for (int i = 0; i < directions.size(); i++) {
      if (Util.isNullOrEmpty(directions.get(i).getDescription())) {
        directions.remove(i--);
      }
    }
    Long recipeId;
    if (recipe.getRecipeId() == null) {
      recipeId = recipeRepository.add(recipe);
    }
    else {
      recipeRepository.update(recipe);
      recipeId = recipe.getRecipeId();
    }
    return "redirect:/recipe/show?id=" + recipeId;
  }

  @GetMapping("/delete")
  public String delete(@RequestParam long id) {
    recipeRepository.remove(id);
    return "redirect:/";
  }

  private void padIngredients(List<Ingredient> ingredients, short cap) {
    for (int i = ingredients.size(); i < cap; i++) {
      ingredients.add(new Ingredient());
    }
  }

  private void padDirections(List<Direction> directions, short cap) {
    for (int i = directions.size(); i < cap; i++) {
      directions.add(new Direction((short) i, ""));
    }
  }
}
