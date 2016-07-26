package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.match.MatchImpl;
import de.kongfoos.foostm.model.match.MatchStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.validation.constraints.NotNull;

public class FXMatch extends MatchImpl<FXTeam, FXTable> {
    private final ObjectProperty<FXTeam> team1 = new SimpleObjectProperty<>();
    private final ObjectProperty<FXTeam> team2 = new SimpleObjectProperty<>();
    private final ObjectProperty<FXTable> table = new SimpleObjectProperty<>();
    private final ObjectProperty<MatchStatus> status = new SimpleObjectProperty<>();


    @Override
    public FXTeam getTeam1() {
        return team1.get();
    }

    public ObjectProperty<FXTeam> team1Property() {
        return team1;
    }

    @Override
    public FXTeam getTeam2() {
        return team2.get();
    }

    public ObjectProperty<FXTeam> team2Property() {
        return team2;
    }

    @Override
    public FXTable getTable() {
        return table.get();
    }

    @Override
    public void setTable(FXTable table) {
        this.table.set(table);
    }

    public ObjectProperty<FXTable> tableProperty() {
        return table;
    }

    @Override
    public MatchStatus getStatus() {
        return status.get();
    }

    @Override
    public void setStatus(@NotNull MatchStatus status) {
        this.status.set(status);
    }

    public ObjectProperty<MatchStatus> statusProperty() {
        return status;
    }
}
