package de.kongfoos.foostm.view.fx.ui.controls;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by patrick on 22/06/16.
 */
public class AutoCompleteTextFieldEvent extends Event {
    public static final EventType<AutoCompleteTextFieldEvent> UPDATE = new EventType<>(Event.ANY, "UPDATE");

    AutoCompleteTextFieldEvent() {
        super(UPDATE);
    }
}
