package de.kongfoos.foostm.view.fx.ui.control.textfield;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a supplied list of entries.
 */
public class AutoCompleteTextField<T> extends TextField {
    /**
     * The existing autocomplete entries.
     */
    private final SortedSet<T> entries;
    private final LinkedList<T> searchResult = new LinkedList<>();
    /**
     * The popup used to select an entry.
     */
    private ContextMenu entriesPopup;
    /**
     * Last selected entry of the popup
     */
    private T lastSelectedEntry;

    /**
     * Construct a new AutoCompleteTextField.
     */
    AutoCompleteTextField() {
        super();
        invalidEntryFormat();
        entries = new TreeSet<>();
        entriesPopup = new ContextMenu();
        textProperty().addListener((v, o, n) -> {
            if (getText().length() < 3 || lastSelectedEntry != null) {
                entriesPopup.hide();
                lastSelectedEntry = null;
                fireEvent(new AutoCompleteTextFieldEvent());
            } else {
                searchResult.clear();
                searchResult.addAll(entries.stream().filter(
                        e -> containsSubString(printPopupEntry(e), getText())).collect(Collectors.toList()));
                if (searchResult.size() > 0) {
                    populatePopup();
                    if (!entriesPopup.isShowing()) {
                        entriesPopup.show(AutoCompleteTextField.this, Side.BOTTOM, 0, 0);
                    }
                    //TODO make it possible to enter the first result on enter
//                    if (entriesPopup.isShowing()) {
//                        entriesPopup.getSkin().getNode().lookup(".menu-item").requestFocus();
//                    }
                } else {
                    entriesPopup.hide();
                }
            }
            if (lastSelectedEntry == null || !printEntry(lastSelectedEntry).equals(getText())) {
                invalidEntryFormat();
            }
        });

        //TODO make it possible to enter the first result on enter
//        entriesPopup.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
//            if (e.getCode().equals(KeyCode.ENTER)) {
//                final Optional<Node> node = entriesPopup.getSkin().getNode().lookupAll(".menu-item")
//                        .stream().filter(Node::isFocused).findFirst();
//                if (node.isPresent()) {
//                    node.get().fireEvent(new ActionEvent(null, null));
//                }
//            }
//        });

        focusedProperty().addListener((v, o, n) -> entriesPopup.hide());
    }

    public void addEntry(T entry) {
        if (entry != null) {
            this.entries.add(entry);
        }
    }

    void addEntries(Collection<T> entries) {
        this.entries.addAll(entries.stream().filter(e -> e != null).collect(Collectors.toList()));
    }

    /**
     * returns the last selection that from the popup
     */
    public T getLastSelectedEntry() {
        return lastSelectedEntry;
    }

    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     */
    private void populatePopup() {
        final List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        final int maxEntries = 5;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final T result = searchResult.get(i);
            final Label entryLabel = new Label(printPopupEntry(result));
            final CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> setActiveEntry(result));
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }

    private void setActiveEntry(T result) {
        setText(printEntry(result));
        validEntryFormat();
        lastSelectedEntry = result;
        entriesPopup.hide();
        fireEvent(new AutoCompleteTextFieldEvent());
    }

    private boolean containsSubString(String string, String subString) {
        return string != null &&
                subString != null &&
                string.toLowerCase().contains(subString.toLowerCase());
    }

    private void validEntryFormat() {
        this.setStyle("-fx-text-inner-color: black;");
    }

    private void invalidEntryFormat() {
        this.setStyle("-fx-text-inner-color: red;");
    }

    /**
     * specifies how the popup values will be displayed
     */
    public String printPopupEntry(T entry) {
        return entry.toString();
    }

    /**
     * specifies how the text field values will be displayed if a selection was made
     */
    String printEntry(T entry) {
        return entry.toString();
    }
}