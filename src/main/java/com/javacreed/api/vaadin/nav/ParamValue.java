package com.javacreed.api.vaadin.nav;

import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class ParamValue extends PathPart {

  /*
   * TODO: These constraints are too restrictive and we will definitely need to relax them. For example, numbers may
   * include commas which are not supported by this pattern
   */
  private static final Pattern REGEX = Pattern.compile("^[\\{\\}\\\"\\:\\,a-zA-Z0-9\\- ]{1,128}$");

  public static ParamValue of(final Number value) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value);
    return ParamValue.of(value.toString());
  }

  public static ParamValue of(final String value) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(value);
    Preconditions.checkArgument(false == value.isEmpty());
    Preconditions.checkArgument(value.length() < 129);
    Preconditions.checkArgument(ParamValue.REGEX.matcher(value).matches(),
                                "Parameter value: '%s' contains invalid characters", value);
    return new ParamValue(value);
  }

  private ParamValue(final String value) throws NullPointerException {
    super(value);
  }
}
