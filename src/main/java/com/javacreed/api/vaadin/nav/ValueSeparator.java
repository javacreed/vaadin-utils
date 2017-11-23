package com.javacreed.api.vaadin.nav;

public class ValueSeparator extends PathPart {

  private static final ValueSeparator DEFAULT = new ValueSeparator("|");

  public static ValueSeparator defaultSeparator() {
    return ValueSeparator.DEFAULT;
  }

  private ValueSeparator(final String value) throws NullPointerException {
    super(value);
  }

}
