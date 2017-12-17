package com.javacreed.api.vaadin.utils;

import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractTextField;

public class ValidationUtils {

  public static void checkValue(final AbstractTextField textField, final Consumer<String> consumer) {
    textField.setComponentError(null);
    try {
      consumer.accept(textField.getValue());
    } catch (NullPointerException | IllegalArgumentException ex) {
      textField.setComponentError(new UserError(StringUtils.defaultIfBlank(ex.getMessage(), "Invalid input")));
    }
  }

  public static void onBlur(final AbstractTextField textField, final Consumer<String> consumer) {
    textField.addBlurListener(e -> ValidationUtils.checkValue(textField, consumer));
  }

  public static void onValueChange(final AbstractTextField textField, final Consumer<String> consumer) {
    textField.addValueChangeListener(e -> ValidationUtils.checkValue(textField, consumer));
  }

  private ValidationUtils() {}
}
