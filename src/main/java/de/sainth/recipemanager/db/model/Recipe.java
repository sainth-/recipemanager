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
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
  private Long recipeId;
  private String name;
  private String description;
  private short portions;
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

  public Recipe() {
    this.ingredients = new ArrayList<>();
    this.directions = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
