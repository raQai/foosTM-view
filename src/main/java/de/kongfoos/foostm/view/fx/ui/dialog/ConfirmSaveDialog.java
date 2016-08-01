package de.kongfoos.foostm.view.fx.ui.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ConfirmSaveDialog<T> extends Dialog<T> {
    private static final ButtonType APPLY_BUTTON = new ButtonType("save", ButtonData.APPLY);
    private static final ButtonType CANCLE_BUTTON = new ButtonType("cancel", ButtonData.CANCEL_CLOSE);

    private String confirmationTitle = "Confirm changes";
    private String confirmationText = "Confirm changes";

    public ConfirmSaveDialog(@NotNull String title) {
        setTitle(title);
        getDialogPane().getButtonTypes().setAll(APPLY_BUTTON, CANCLE_BUTTON);
    }

    public void setConfirmationTitle(@NotNull String title) {
        this.confirmationTitle = title;
    }

    public void setConfirmationText(@NotNull String text) {
        this.confirmationText = text;
    }

    public ConfirmSaveDialog<T> init() {
        getDialogPane().lookupButton(APPLY_BUTTON).addEventFilter(ActionEvent.ACTION, e -> {
            final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle(confirmationTitle);
            confirmation.setHeaderText(null);
            confirmation.setContentText(confirmationText);
            confirmation.getButtonTypes().setAll(APPLY_BUTTON, CANCLE_BUTTON);
            confirmation.showAndWait()
                    .ifPresent(result -> {
                        if (result == CANCLE_BUTTON) {
                            e.consume();
                        }
                    });
        });

        getDialogPane().setContent(createContent());

        setResultConverter(button -> {
            if (button == APPLY_BUTTON) {
                return createResult();
            }
            return null;
        });
        return this;
    }

    abstract T createResult();

    abstract Node createContent();
}
