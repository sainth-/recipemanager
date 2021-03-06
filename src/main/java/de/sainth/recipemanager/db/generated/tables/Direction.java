/*
 * This file is generated by jOOQ.
 */
package de.sainth.recipemanager.db.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import de.sainth.recipemanager.db.generated.Keys;
import de.sainth.recipemanager.db.generated.Public;
import de.sainth.recipemanager.db.generated.tables.records.DirectionRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Direction extends TableImpl<DirectionRecord> {

  private static final long serialVersionUID = 2030941699;

  /**
   * The reference instance of <code>public.direction</code>
   */
  public static final Direction DIRECTION = new Direction();

  /**
   * The class holding records for this type
   */
  @Override
  public Class<DirectionRecord> getRecordType() {
    return DirectionRecord.class;
  }

  /**
   * The column <code>public.direction.recipe_id</code>.
   */
  public final TableField<DirectionRecord, Long> RECIPE_ID = createField("recipe_id",
                                                                         org.jooq.impl.SQLDataType.BIGINT.nullable(
                                                                             false),
                                                                         this,
                                                                         "");

  /**
   * The column <code>public.direction.position</code>.
   */
  public final TableField<DirectionRecord, Short> POSITION = createField("position",
                                                                         org.jooq.impl.SQLDataType.SMALLINT.nullable(
                                                                             false),
                                                                         this,
                                                                         "");

  /**
   * The column <code>public.direction.description</code>.
   */
  public final TableField<DirectionRecord, String> DESCRIPTION = createField("description",
                                                                             org.jooq.impl.SQLDataType.VARCHAR(
                                                                                 1024).nullable(false),
                                                                             this,
                                                                             "");

  /**
   * Create a <code>public.direction</code> table reference
   */
  public Direction() {
    this(DSL.name("direction"), null);
  }

  /**
   * Create an aliased <code>public.direction</code> table reference
   */
  public Direction(String alias) {
    this(DSL.name(alias), DIRECTION);
  }

  /**
   * Create an aliased <code>public.direction</code> table reference
   */
  public Direction(Name alias) {
    this(alias, DIRECTION);
  }

  private Direction(Name alias, Table<DirectionRecord> aliased) {
    this(alias, aliased, null);
  }

  private Direction(Name alias, Table<DirectionRecord> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""));
  }

  public <O extends Record> Direction(Table<O> child, ForeignKey<O, DirectionRecord> key) {
    super(child, key, DIRECTION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Schema getSchema() {
    return Public.PUBLIC;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UniqueKey<DirectionRecord> getPrimaryKey() {
    return Keys.DIRECTION_PKEY;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UniqueKey<DirectionRecord>> getKeys() {
    return Arrays.<UniqueKey<DirectionRecord>>asList(Keys.DIRECTION_PKEY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ForeignKey<DirectionRecord, ?>> getReferences() {
    return Arrays.<ForeignKey<DirectionRecord, ?>>asList(Keys.DIRECTION__DIRECTION_RECIPE_ID_FKEY);
  }

  public Recipe recipe() {
    return new Recipe(this, Keys.DIRECTION__DIRECTION_RECIPE_ID_FKEY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Direction as(String alias) {
    return new Direction(DSL.name(alias), this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Direction as(Name alias) {
    return new Direction(alias, this);
  }

  /**
   * Rename this table
   */
  @Override
  public Direction rename(String name) {
    return new Direction(DSL.name(name), null);
  }

  /**
   * Rename this table
   */
  @Override
  public Direction rename(Name name) {
    return new Direction(name, null);
  }
}
