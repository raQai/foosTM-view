package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.table.ITable;
import de.kongfoos.foostm.model.table.TableType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FXTable implements ITable {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<TableType> type = new SimpleObjectProperty<>();

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

}
