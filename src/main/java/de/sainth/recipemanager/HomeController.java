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
import de.sainth.recipemanager.db.model.OverviewRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
  private final RecipeRepository recipeRepository;

  @Autowired
  public HomeController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping("/")
  public ModelAndView index(Map<String, Object> model) {
    List<OverviewRecipe> recipes = recipeRepository.overviewQuery();
    model.put("recipes", recipes);
    return new ModelAndView("index", model);
  }

}
