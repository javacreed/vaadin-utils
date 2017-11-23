package com.javacreed.api.vaadin.nav;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class PartNameValue {

  public static PartNameValue ofNullable(final ParamName name, final ParamValue value) {
    Preconditions.checkNotNull(name);
    final Optional<ParamValue> optional = Optional.ofNullable(value);
    return new PartNameValue(name, optional);
  }

  private final ParamName name;
  private final Optional<ParamValue> value;

  private PartNameValue(final ParamName name, final Optional<ParamValue> value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object != null && getClass() == object.getClass()) {
      final PartNameValue other = (PartNameValue) object;
      return name.equals(other.name) && value.equals(other.value);
    }

    return false;
  }

  public ParamName getName() {
    return name;
  }

  public Optional<ParamValue> getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + name.hashCode();
    result = prime * result + value.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Parameter [name=" + name + ", value=" + value + "]";
  }
}