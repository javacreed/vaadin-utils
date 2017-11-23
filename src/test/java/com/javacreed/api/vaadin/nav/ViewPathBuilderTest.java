package com.javacreed.api.vaadin.nav;

import org.junit.Assert;
import org.junit.Test;

public class ViewPathBuilderTest {

  @Test
  public void noParameters() {
    Assert.assertEquals("testView", ViewPathBuilder.of("testView").path());
  }

  @Test
  public void withManyParameters() {
    final ViewPathBuilder builder = ViewPathBuilder.of("testView");
    builder.param("a", "b");
    builder.param("c", "d");
    builder.param("e", "f");

    Assert.assertEquals("testView/a|b/c|d/e|f", builder.path());
  }

  @Test
  public void withManyParametersWithoutValues() {
    final ViewPathBuilder builder = ViewPathBuilder.of("testView");
    builder.param("a", "b");
    builder.param("c");
    builder.param("e", "f");

    Assert.assertEquals("testView/a|b/c/e|f", builder.path());
  }

  @Test
  public void withOneParameter() {
    final ViewPathBuilder builder = ViewPathBuilder.of("testView");
    builder.param("a", "b");

    Assert.assertEquals("testView/a|b", builder.path());
  }
}
