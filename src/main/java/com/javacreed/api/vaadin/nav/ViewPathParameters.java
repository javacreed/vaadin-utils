package com.javacreed.api.vaadin.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public class ViewPathParameters implements Iterable<PartNameValue> {

  private static final ViewPathParameters EMPTY = new ViewPathParameters(Collections.emptyList());

  public static ViewPathParameters empty() {
    return ViewPathParameters.EMPTY;
  }

  public static ViewPathParameters of(final String parameters) {
    if (StringUtils.isBlank(parameters)) {
      return ViewPathParameters.empty();
    }

    /* TODO: We need to be able to configure this separator */
    final String[] parts = parameters.split("\\/");
    final List<PartNameValue> list = new ArrayList<>(parts.length);
    for (final String part : parts) {
      /* TODO: We need to be able to configure this separator */
      final String[] pair = part.split("\\|", 2);
      final ParamName name = ParamName.of(pair[0]);
      final ParamValue value = pair.length == 2 ? ParamValue.of(pair[1]) : null;
      list.add(PartNameValue.ofNullable(name, value));
    }

    return new ViewPathParameters(Collections.unmodifiableList(list));
  }

  private final List<PartNameValue> parameters;

  private ViewPathParameters(final List<PartNameValue> parameters) throws NullPointerException {
    this.parameters = parameters;
  }

  public boolean isEmpty() {
    return parameters.isEmpty();
  }

  @Override
  public Iterator<PartNameValue> iterator() {
    return parameters.iterator();
  }

  public ViewPathParameters perform(final ParamName name, final Consumer<PartNameValue> function) {
    return perform(p -> name.equalsIgnoreCase(p), function);
  }

  public ViewPathParameters perform(final Predicate<ParamName> predicate, final Consumer<PartNameValue> function) {
    for (final PartNameValue p : this) {
      if (predicate.test(p.getName())) {
        function.accept(p);
        break;
      }
    }
    return this;
  }
}
