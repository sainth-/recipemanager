package de.sainth.recipemanager.db.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Direction implements Serializable {
  private short position;
  private final String description;

  public Direction(short position, String description) {
    this.position = position;
    this.description = description;
  }

  public Direction(String description) {
    this.description = description;
  }

  public short getPosition() {
    return position;
  }

  public void setPosition(short position) {
    this.position = position;
  }

  public String getDescription() {
    return description;
  }
}
