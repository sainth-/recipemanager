/*
 * This file is generated by jOOQ.
 */
package de.sainth.recipemanager.db.generated.tables.records;


import javax.annotation.Generated;

import de.sainth.recipemanager.db.generated.tables.Unit;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UnitRecord extends UpdatableRecordImpl<UnitRecord> implements Record1<String> {

  private static final long serialVersionUID = 263817795;

  /**
   * Setter for <code>public.unit.unit_name</code>.
   */
  public void setUnitName(String value) {
    set(0, value);
  }

  /**
   * Getter for <code>public.unit.unit_name</code>.
   */
  public String getUnitName() {
    return (String) get(0);
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override
  public Record1<String> key() {
    return (Record1) super.key();
  }

  // -------------------------------------------------------------------------
  // Record1 type implementation
  // -------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override
  public Row1<String> fieldsRow() {
    return (Row1) super.fieldsRow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Row1<String> valuesRow() {
    return (Row1) super.valuesRow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Field<String> field1() {
    return Unit.UNIT.UNIT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String component1() {
    return getUnitName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String value1() {
    return getUnitName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UnitRecord value1(String value) {
    setUnitName(value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UnitRecord values(String value1) {
    value1(value1);
    return this;
  }

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  /**
   * Create a detached UnitRecord
   */
  public UnitRecord() {
    super(Unit.UNIT);
  }

  /**
   * Create a detached, initialised UnitRecord
   */
  public UnitRecord(String unitName) {
    super(Unit.UNIT);

    set(0, unitName);
  }
}