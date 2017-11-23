package com.javacreed.api.vaadin.nav;

public class PartSeparator extends PathPart {

  private static final PartSeparator DEFAULT = new PartSeparator("/");

  public static PartSeparator defaultSeparator() {
    return PartSeparator.DEFAULT;
  }

  private PartSeparator(final String value) throws NullPointerException {
    super(value);
  }
}
