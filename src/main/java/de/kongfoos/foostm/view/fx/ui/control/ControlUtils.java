package de.kongfoos.foostm.view.fx.ui.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ControlUtils {
    public static Button addButton(Pane pane, String text, EventHandler<ActionEvent> e) {
        final Button button = new Button();
        button.setText(text);
        button.setOnAction(e);
        button.setMaxWidth(Double.MAX_VALUE);
        pane.getChildren().add(button);
        return button;
    }
}
