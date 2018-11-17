/*
 * This file is generated by jOOQ.
 */
package de.sainth.recipemanager.db.generated;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

  /**
   * The sequence <code>public.ingredient_ingredient_id_seq</code>
   */
  public static final Sequence<Long> INGREDIENT_INGREDIENT_ID_SEQ = new SequenceImpl<Long>(
      "ingredient_ingredient_id_seq",
      Public.PUBLIC,
      org.jooq.impl.SQLDataType.BIGINT.nullable(false));

  /**
   * The sequence <code>public.recipe_recipe_id_seq</code>
   */
  public static final Sequence<Long> RECIPE_RECIPE_ID_SEQ = new SequenceImpl<Long>("recipe_recipe_id_seq",
                                                                                   Public.PUBLIC,
                                                                                   org.jooq.impl.SQLDataType.BIGINT
                                                                                       .nullable(false));
}