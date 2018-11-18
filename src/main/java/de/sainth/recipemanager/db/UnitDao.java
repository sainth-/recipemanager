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
