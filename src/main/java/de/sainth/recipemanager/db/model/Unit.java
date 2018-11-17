package de.sainth.recipemanager.db.model;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
  private final String name;

  public Unit(String name) {
    Objects.requireNonNull(name);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
