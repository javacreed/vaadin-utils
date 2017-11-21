package com.javacreed.api.vaadin.utils;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class VaadinUtils {

    public static void maybe(final String caption) {
        Notification.show(caption,
                "This feature is not yet implemented because Mona is not sure whether it will be used or not.  If this is something that you really like to have, then please speak with Mona about this.",
                Type.TRAY_NOTIFICATION);
    }

    private VaadinUtils() {}
}
