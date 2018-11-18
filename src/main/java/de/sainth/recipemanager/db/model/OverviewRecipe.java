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

package de.sainth.recipemanager.db.model;

import java.io.Serializable;

public class OverviewRecipe implements Serializable {
  private final long recipeId;
  private final String name;

  public OverviewRecipe(long recipeId, String name) {
    this.name = name;
    this.recipeId = recipeId;
  }

  public long getRecipeId() {
    return recipeId;
  }

  public String getName() {
    return name;
  }
}
