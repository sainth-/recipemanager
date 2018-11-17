package de.sainth.recipemanager.db;

import static de.sainth.recipemanager.db.generated.tables.Unit.UNIT;

import java.util.Objects;

import de.sainth.recipemanager.db.model.Unit;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitDao {
  private final DSLContext create;

  @Autowired
  public UnitDao(DSLContext create) {
    this.create = create;
  }

  public void create(Unit unit) {
    Objects.requireNonNull(unit);
    create.insertInto(UNIT)
          .set(UNIT.UNIT_NAME, unit.getName())
          .execute();
  }

  public void delete(Unit unit) {
    Objects.requireNonNull(unit);
    create.deleteFrom(UNIT)
          .where(UNIT.UNIT_NAME.eq(unit.getName()))
          .execute();
  }
}
