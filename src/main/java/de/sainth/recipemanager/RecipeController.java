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

import java.math.BigDecimal;
import java.util.ArrayList;
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
    return new ModelAndView("showRecipe", model);
  }

  @GetMapping("/create")
  public ModelAndView createRecipe(Map<String, Object> model) {
    List<Unit> units = unitDao.query();
    model.put("units", units);
    return new ModelAndView("createRecipe", model);
  }

  @PostMapping("/create")
  public String create(@RequestParam Map<String, String> map) {
    Recipe recipe = new Recipe(map.get("name"), Short.valueOf(map.get("portions")));
    recipe.setDescription(map.get("description"));
    List<Ingredient> ingredients = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      String food = map.get("food" + i);
      if (!Util.isNullOrEmpty(food)) {
        Ingredient ingredient = new Ingredient(food);
        String amountString = map.get("amount" + i);
        if (!Util.isNullOrEmpty(amountString)) {
          ingredient.setAmount(new BigDecimal(amountString));
          ingredient.setUnit(new Unit(map.get("unit" + i)));
        }
        ingredients.add(ingredient);
      }
    }
    recipe.setIngredients(ingredients);
    List<Direction> directions = new ArrayList<>();
    for (short i = 1; i <= 20; i++) {
      String direction = map.get("direction" + i);
      if (!Util.isNullOrEmpty(direction)) {
        directions.add(new Direction(i, direction));
      }
    }
    recipe.setDirections(directions);
    recipeRepository.add(recipe);
    return "redirect:/";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam long id) {
    recipeRepository.remove(id);
    return "redirect:/";
  }
}
