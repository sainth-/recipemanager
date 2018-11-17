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
