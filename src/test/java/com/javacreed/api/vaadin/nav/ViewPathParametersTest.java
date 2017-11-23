package com.javacreed.api.vaadin.nav;

import org.junit.Assert;
import org.junit.Test;

public class ViewPathParametersTest {

  @Test
  public void blank() {
    Assert.assertTrue(ViewPathParameters.of("").isEmpty());
  }

}
