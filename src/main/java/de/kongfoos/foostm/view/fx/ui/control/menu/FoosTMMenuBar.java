package de.kongfoos.foostm.view.fx.ui.control.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class FoosTMMenuBar extends MenuBar {
    public FoosTMMenuBar() {

        final Menu testMenu = new Menu("test");

        final MenuItem testMenuItem = new MenuItem("exit with key");
        testMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        testMenuItem.setOnAction(e -> System.exit(0));

        final MenuItem exitMenuItem = new MenuItem("exit");
        exitMenuItem.setOnAction(e -> System.exit(0));

        testMenu.getItems().addAll(testMenuItem, new SeparatorMenuItem(),
                exitMenuItem);
        getMenus().add(testMenu);
        setUseSystemMenuBar(true);
    }
}
