package de.sainth.recipemanager.db.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ingredient implements Serializable {
  private long ingredientId;
  private final String foodName;
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
}
