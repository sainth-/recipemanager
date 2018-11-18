--     Copyright (C) 2018 Tobias Wink <sainth@sainth.de>
--
--     This program is free software: you can redistribute it and/or modify
--     it under the terms of the GNU General Public License as published by
--     the Free Software Foundation, either version 3 of the License, or
--     (at your option) any later version.
--
--     This program is distributed in the hope that it will be useful,
--     but WITHOUT ANY WARRANTY; without even the implied warranty of
--     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--     GNU General Public License for more details.
--
--     You should have received a copy of the GNU General Public License
--     along with this program.  If not, see <https://www.gnu.org/licenses/>.

CREATE TABLE unit (
  unit_name   VARCHAR(10),
  PRIMARY KEY (unit_name)
);

CREATE TABLE recipe (
  recipe_id   BIGSERIAL,
  name        VARCHAR(64) NOT NULL,
  description TEXT,
  portions    SMALLINT NOT NULL,
  PRIMARY KEY (recipe_id)
);

CREATE TABLE ingredient (
  ingredient_id BIGSERIAL,
  recipe_id     BIGINT NOT NULL,
  food_name     VARCHAR(64) NOT NULL,
  amount        NUMERIC(14,4),
  unit_name     VARCHAR(10),
  PRIMARY KEY (ingredient_id),
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (unit_name) REFERENCES unit (unit_name) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE direction (
  recipe_id     BIGINT NOT NULL,
  position      SMALLINT NOT NULL,
  description   VARCHAR(1024) NOT NULL,
  PRIMARY KEY (recipe_id, position),
  FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON UPDATE CASCADE ON DELETE CASCADE
);
