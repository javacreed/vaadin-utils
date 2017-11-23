package com.javacreed.api.vaadin.nav;

import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class ViewName extends PathPart {

  /* TODO: */
  private static final Pattern REGEX = Pattern.compile("^[a-zA-Z0-9]{0,36}$");

  public static ViewName of(final String value) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value);
    Preconditions.checkArgument(value.length() < 37);
    Preconditions.checkArgument(ViewName.REGEX.matcher(value).matches());
    return new ViewName(value);
  }

  private ViewName(final String value) throws NullPointerException {
    super(value);
  }
}
