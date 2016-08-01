package de.kongfoos.foostm.view.fx.ui.panel;

import com.google.common.base.Strings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.validation.constraints.NotNull;
import java.util.List;

public abstract class FoosTMPanel extends VBox {
    public FoosTMPanel() {
        this(null);
    }
    public FoosTMPanel(String title) {
        setupStyle();
        if (!Strings.isNullOrEmpty(title)) {
            setupHeader(title);
        }
    }

    public FoosTMPanel init() {
        getChildren().addAll(addComponents());
        return this;
    }

    // TODO use css
    private void setupHeader(@NotNull String title) {
        final Label registrationLable = new Label(title);
        registrationLable.setFont(new Font(registrationLable.getFont()
                .getName(), 20));
        getChildren().add(registrationLable);
    }

    // TODO use css
    private void setupStyle() {
        setPadding(new Insets(15));
        setSpacing(10);
    }

    protected abstract List<? extends Node> addComponents();

}
