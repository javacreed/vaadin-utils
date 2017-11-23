package com.javacreed.api.vaadin.nav;

import com.vaadin.ui.UI;

public class AbstractViewPath {

  protected final ViewPathBuilder builder;

  protected AbstractViewPath(final String name) {
    builder = ViewPathBuilder.of(name);
  }

  protected AbstractViewPath(final ViewName name) {
    builder = ViewPathBuilder.of(name);
  }

  public void navigate() {
    UI.getCurrent().getNavigator().navigateTo(path());
  }

  public String path() {
    return builder.path();
  }
}
