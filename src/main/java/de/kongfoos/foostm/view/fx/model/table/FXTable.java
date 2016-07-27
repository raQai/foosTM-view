package de.kongfoos.foostm.view.fx.model.table;

import de.kongfoos.foostm.model.table.TableImpl;
import de.kongfoos.foostm.model.table.TableType;
import de.kongfoos.foostm.view.fx.model.match.FXMatch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FXTable extends TableImpl<FXMatch> {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<TableType> type = new SimpleObjectProperty<>();
    private final ObjectProperty<FXMatch> match = new SimpleObjectProperty<>();

    FXTable() {
    }

    /**
     * @deprecated do not use constructor with parameters and use package local constructor with builder instead
     */
    @Deprecated
    public FXTable(int id, TableType type) {
        setID(id);
        setType(type);
    }

    @Override
    public int getID() {
        return id.get();
    }

    @Override
    public void setID(int i) {
        id.set(i);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @Override
    public TableType getType() {
        return type.get();
    }

    @Override
    public void setType(TableType type) {
        this.type.set(type);
    }

    public ObjectProperty<TableType> tableTypeProperty() {
        return type;
    }

    @Override
    public FXMatch getMatch() {
        return match.get();
    }

    @Override
    public void setMatch(FXMatch match) {
        this.match.set(match);
    }

    public ObjectProperty<FXMatch> matchProperty() {
        return match;
    }
}