package com.javacreed.api.vaadin.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * TODO: The {@link #executables} field is modified every time the {@link #whenThen(ParamName, Consumer)} or
 * {@link #whenThen(Predicate, Consumer)} methods are invoked. Consider returning a new instance of this class instead
 * with the new {@link Executable} so that we can make this class immutable. Another option would be to freeze changes
 * to this class once any of the terminating methods ({@link #orElse(Consumer)} or {@link #end()}) are invoked.
 *
 * @author Albert Attard
 */
public class ViewPathParameters implements Iterable<PartNameValue> {

  private static class Executable {
    private static Executable of(final Predicate<PartNameValue> predicate, final Consumer<PartNameValue> consumer) {
      Preconditions.checkNotNull(predicate);
      Preconditions.checkNotNull(consumer);
      return new Executable(predicate, consumer);
    }

    final Predicate<PartNameValue> predicate;

    final Consumer<PartNameValue> consumer;

    private Executable(final Predicate<PartNameValue> predicate, final Consumer<PartNameValue> consumer) {
      this.predicate = predicate;
      this.consumer = consumer;
    }

    public void accept(final PartNameValue part) {
      consumer.accept(part);
    }

    public boolean test(final PartNameValue part) {
      return predicate.test(part);
    }
  }

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

  private final List<Executable> executables = new ArrayList<>();

  private ViewPathParameters(final List<PartNameValue> parameters) {
    this.parameters = parameters;
  }

  public void end() {
    orElse(p -> {});
  }

  public boolean isEmpty() {
    return parameters.isEmpty();
  }

  @Override
  public Iterator<PartNameValue> iterator() {
    return parameters.iterator();
  }

  public void orElse(final Consumer<ViewPathParameters> function) {
    for (final Executable e : executables) {
      for (final PartNameValue p : this) {
        if (e.test(p)) {
          e.accept(p);
          /* TODO: should we stop or process them all? */
          return;
        }
      }
    }

    function.accept(this);
  }

  public ViewPathParameters whenThen(final ParamName name, final Consumer<PartNameValue> function) {
    return whenThen(p -> name.equalsIgnoreCase(p.getName()), function);
  }

  public ViewPathParameters whenThen(final Predicate<PartNameValue> predicate, final Consumer<PartNameValue> function) {
    executables.add(Executable.of(predicate, function));
    return this;
  }
}
