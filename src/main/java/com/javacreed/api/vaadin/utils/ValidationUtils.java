package com.javacreed.api.vaadin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.shared.Registration;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;

public class ValidationUtils {

  public static <T> Optional<T> checkAndReturnValue(final AbstractTextField textField,
      final Function<String, T> function) {
    textField.setComponentError(null);
    try {
      return Optional.of(function.apply(textField.getValue()));
    } catch (NullPointerException | IllegalArgumentException e) {
      textField.setComponentError(new UserError(StringUtils.defaultIfBlank(e.getMessage(), "Invalid input")));
    } catch (final RuntimeException e) {
      textField.setComponentError(new UserError("Failed to validate the input"));
    }

    return Optional.empty();
  }

  public static boolean checkValue(final AbstractTextField textField, final Consumer<String> consumer) {
    textField.setComponentError(null);
    try {
      consumer.accept(textField.getValue());
      return true;
    } catch (NullPointerException | IllegalArgumentException e) {
      textField.setComponentError(new UserError(StringUtils.defaultIfBlank(e.getMessage(), "Invalid input")));
    } catch (final RuntimeException e) {
      textField.setComponentError(new UserError("Failed to validate the input"));
    }

    return false;
  }

  public static <T> Optional<T> checkValueAndGrab(final AbstractTextField textField,
      final Function<String, T> function) {
    final Optional<T> optional = ValidationUtils.checkAndReturnValue(textField, function);
    if (false == optional.isPresent()) {
      textField.selectAll();
      textField.focus();
    }

    return optional;
  }

  public static List<ErrorMessage> getChildrenErrors(final AbstractLayout layout) {
    final List<ErrorMessage> messages = new ArrayList<>();

    for (final Component component : layout) {
      if (component instanceof AbstractComponent) {
        final ErrorMessage errorMessage = ((AbstractComponent) component).getComponentError();
        if (errorMessage == null) {
          continue;
        }

        messages.add(errorMessage);
      }
    }

    return messages;
  }

  public static Registration onBlur(final AbstractTextField textField, final Consumer<String> consumer) {
    return textField.addBlurListener(e -> ValidationUtils.checkValue(textField, consumer));
  }

  public static Registration onValueChange(final AbstractTextField textField, final Consumer<String> consumer) {
    return textField.addValueChangeListener(e -> ValidationUtils.checkValue(textField, consumer));
  }

  private ValidationUtils() {}
}
