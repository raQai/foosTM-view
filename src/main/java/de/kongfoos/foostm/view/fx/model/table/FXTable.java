package de.kongfoos.foostm.view.fx.model.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import de.kongfoos.foostm.model.table.Table;
import de.kongfoos.foostm.model.table.TableType;
import de.kongfoos.foostm.view.fx.model.match.FXMatch;

public class FXTable extends Table<FXMatch> {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ObjectProperty<TableType> type = new SimpleObjectProperty<>();
    private final ObjectProperty<FXMatch> match = new SimpleObjectProperty<>();

    FXTable() {
    }

    @Override
    public int getNumber() {
        return id.get();
    }

    @Override
    public void setNumber(int i) {
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
