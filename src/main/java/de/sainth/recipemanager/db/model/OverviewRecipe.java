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
