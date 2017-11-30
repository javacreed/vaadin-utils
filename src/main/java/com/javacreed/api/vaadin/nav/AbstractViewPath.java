package com.javacreed.api.vaadin.nav;

public class AbstractViewPath implements HasPath {

  protected final ViewPathBuilder builder;

  protected AbstractViewPath(final String name) {
    this.builder = ViewPathBuilder.of(name);
  }

  protected AbstractViewPath(final ViewName name) {
    this.builder = ViewPathBuilder.of(name);
  }

  @Override
  public String path() {
    return builder.path();
  }
}
