package com.javacreed.api.vaadin.nav;

import java.util.Objects;
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

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final PartNameValue other = (PartNameValue) object;
    return name.equals(other.name) && value.equals(other.value);
  }

  public ParamName getName() {
    return name;
  }

  public Optional<ParamValue> getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  public boolean hasName(final String name) {
    return this.name.getValue().equals(name);
  }

  public boolean hasValue() {
    return value.isPresent();
  }

  public boolean hasValueFor(final String name) {
    return hasValue() && hasName(name);
  }

  @Override
  public String toString() {
    return "Parameter [name=" + name + ", value=" + value + "]";
  }
}