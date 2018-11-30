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
import java.math.BigDecimal;

public class Ingredient implements Serializable {
  private long ingredientId;
  private String foodName;
  private BigDecimal amount;
  private Unit unit;

  public Ingredient(long ingredientId,
                    String foodName,
                    BigDecimal amount,
                    Unit unit) {
    this.ingredientId = ingredientId;
    this.foodName = foodName;
    this.amount = amount;
    this.unit = unit;
  }

  public Ingredient() {

  }

  public Ingredient(String foodName) {
    this.foodName = foodName;
  }

  public long getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(long ingredientId) {
    this.ingredientId = ingredientId;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(foodName);
    if (amount != null && !amount.equals(BigDecimal.ZERO)) {
      sb.append(" ");
      sb.append(amount.setScale(2, BigDecimal.ROUND_DOWN));
      sb.append(" ");
      sb.append(unit.getName());
    }
    return sb.toString();
  }
}
