package com.javacreed.api.vaadin.nav;

import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class ParamName extends PathPart {

  private static final Pattern REGEX = Pattern.compile("^[a-zA-Z0-9]{1,36}$");

  public static ParamName of(final String value) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value);
    Preconditions.checkArgument(false == value.isEmpty());
    Preconditions.checkArgument(value.length() < 37);
    Preconditions.checkArgument(ParamName.REGEX.matcher(value).matches());
    return new ParamName(value);
  }

  private ParamName(final String value) throws NullPointerException {
    super(value);
  }
}
