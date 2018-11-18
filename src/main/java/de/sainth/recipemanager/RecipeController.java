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

import java.util.Map;

import de.sainth.recipemanager.db.RecipeRepository;
import de.sainth.recipemanager.db.model.Recipe;

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

  @Autowired
  public RecipeController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping("/show")
  public ModelAndView showRecipe(@RequestParam long id, Map<String, Object> model) {
    Recipe recipe = recipeRepository.query(id);
    model.put("recipe", recipe);
    return new ModelAndView("showRecipe", model);
  }

  @GetMapping("/create")
  public String createRecipe() {
    return "createRecipe";
  }

  @PostMapping("/create")
  public String authenticate(@RequestParam Map<String, String> map) {
    Recipe recipe = new Recipe(map.get("name"), Short.valueOf(map.get("portions")));
    recipe.setDescription(map.get("description"));
    recipeRepository.add(recipe);
    return "redirect:/";
  }
}