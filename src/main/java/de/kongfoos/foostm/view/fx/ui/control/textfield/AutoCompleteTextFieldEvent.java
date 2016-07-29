package de.kongfoos.foostm.view.fx.ui.control.textfield;

import javafx.event.Event;
import javafx.event.EventType;

public class AutoCompleteTextFieldEvent extends Event {
    public static final EventType<AutoCompleteTextFieldEvent> UPDATE = new EventType<>(Event.ANY, "UPDATE");

    AutoCompleteTextFieldEvent() {
        super(UPDATE);
    }
}
