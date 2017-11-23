package com.javacreed.api.vaadin.nav;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

import com.google.common.base.Preconditions;
import com.javacreed.api.domain.objects.mandatory.StringBasedDomainObject;

public class ViewPathBuilder {

  public static ViewPathBuilder of(final String name) throws NullPointerException, IllegalArgumentException {
    return ViewPathBuilder.of(ViewName.of(name));
  }

  public static ViewPathBuilder of(final ViewName name) throws NullPointerException {
    Preconditions.checkNotNull(name);
    return new ViewPathBuilder(name);
  }

  private final ViewName name;
  private PartSeparator partSeparator = PartSeparator.defaultSeparator();
  private ValueSeparator valueSeparator = ValueSeparator.defaultSeparator();
  private final Map<ParamName, Optional<ParamValue>> parameters;

  private ViewPathBuilder(final ViewName name) {
    this.name = name;
    parameters = new TreeMap<>(StringBasedDomainObject.CASE_INSENSITIVE);
  }

  public ViewPathBuilder param(final ParamName name) throws NullPointerException {
    Preconditions.checkNotNull(name);
    parameters.put(name, Optional.empty());
    return this;
  }

  public ViewPathBuilder param(final ParamName name, final ParamValue value)
      throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(name);
    Preconditions.checkNotNull(value);
    parameters.put(name, Optional.of(value));
    return this;
  }

  public ViewPathBuilder param(final String name) throws NullPointerException, IllegalArgumentException {
    return param(ParamName.of(name));
  }

  public ViewPathBuilder param(final String name, final Number value)
      throws NullPointerException, IllegalArgumentException {
    return param(ParamName.of(name), ParamValue.of(value));
  }

  public ViewPathBuilder param(final String name, final String value)
      throws NullPointerException, IllegalArgumentException {
    return param(ParamName.of(name), ParamValue.of(value));
  }

  public ViewPathBuilder partSeparator(final PartSeparator partSeparator) throws NullPointerException {
    this.partSeparator = Preconditions.checkNotNull(partSeparator);
    return this;
  }

  public String path() {
    if (parameters.isEmpty()) {
      return name.getValue();
    }

    final StringBuilder path = new StringBuilder(name.getValue());
    for (final Entry<ParamName, Optional<ParamValue>> entry : parameters.entrySet()) {
      path.append(partSeparator).append(entry.getKey());
      entry.getValue().ifPresent(v -> path.append(valueSeparator).append(v));
    }
    return path.toString();
  }

  @Override
  public String toString() {
    return path();
  }

  public ViewPathBuilder valueSeparator(final ValueSeparator valueSeparator) throws NullPointerException {
    this.valueSeparator = Preconditions.checkNotNull(valueSeparator);
    return this;
  }
}
