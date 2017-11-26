package com.javacreed.api.vaadin.time;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

public class DateTimeInput extends Panel {

  private static final long serialVersionUID = -6123916945466707084L;

  private static List<LocalTime> createTime() {
    final List<LocalTime> list = new ArrayList<>();
    final LocalTime midnight = LocalTime.of(0, 0);
    for (int i = 0; i < 48; i++) {
      list.add(midnight.plusMinutes(i * 30));
    }
    return list;
  }

  private static List<ZoneId> createZones() {
    final List<ZoneId> zones = new ArrayList<>();
    for (final String id : ZoneId.getAvailableZoneIds()) {
      zones.add(ZoneId.of(id));
    }
    return zones;
  }

  public static DateTimeInput of() {
    final DateTimeInput input = new DateTimeInput();
    input.init();
    return input;
  }

  private DateField dateField;

  private ComboBox<LocalTime> timeComboBox;

  private ComboBox<ZoneId> zoneComboBox;

  private void init() {
    addStyleName(ValoTheme.PANEL_BORDERLESS);

    final HorizontalLayout layout = new HorizontalLayout();
    setContent(layout);

    dateField = new DateField();
    layout.addComponent(dateField);

    timeComboBox = new ComboBox<>();
    timeComboBox.setItems(DateTimeInput.createTime());
    timeComboBox.setDescription("Time");
    layout.addComponent(timeComboBox);

    zoneComboBox = new ComboBox<>();
    zoneComboBox.setItems(DateTimeInput.createZones());
    zoneComboBox.setDescription("Time/Zone");
    layout.addComponent(zoneComboBox);
  }

  public void setDateFormat(final String dateFormat) {
    dateField.setDateFormat(dateFormat);
  }
}
