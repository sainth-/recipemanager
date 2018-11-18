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

import de.sainth.recipemanager.db.UnitDao;
import de.sainth.recipemanager.db.model.OverviewRecipe;
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
@RequestMapping("/unit")
public class UnitController {
  private final UnitDao unitDao;

  @Autowired
  public UnitController(UnitDao unitDao) {
    this.unitDao = unitDao;
  }

  @GetMapping("/show")
  public ModelAndView index(Map<String, Object> model) {
    List<Unit> units = unitDao.query();
    model.put("units", units);
    return new ModelAndView("unit/show", model);
  }

  @PostMapping("/create")
  public String create(@RequestParam Map<String, String> map) {
    Unit unit = new Unit(map.get("name"));
    unitDao.create(unit);
    return "redirect:/unit/show";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam String name) {
    Unit unit = new Unit(name);
    unitDao.delete(unit);
    return "redirect:/unit/show";
  }

}
