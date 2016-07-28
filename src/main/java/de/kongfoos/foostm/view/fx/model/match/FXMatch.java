package de.kongfoos.foostm.view.fx.model.match;

import de.kongfoos.foostm.model.match.AMatch;
import de.kongfoos.foostm.model.match.MatchStatus;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.validation.constraints.NotNull;

public class FXMatch extends AMatch<FXTeam> {
    private final ObjectProperty<FXTeam> team1 = new SimpleObjectProperty<>();
    private final ObjectProperty<FXTeam> team2 = new SimpleObjectProperty<>();
    private final ObjectProperty<MatchStatus> status = new SimpleObjectProperty<>();

    FXMatch() {
    }

    @Override
    public FXTeam getTeam1() {
        return team1.get();
    }

    @Override
    public void setTeam1(@NotNull FXTeam team) {
        team1.set(team);
    }

    public ObjectProperty<FXTeam> team1Property() {
        return team1;
    }

    @Override
    public FXTeam getTeam2() {
        return team2.get();
    }

    @Override
    public void setTeam2(@NotNull FXTeam team) {
        team2.set(team);
    }

    public ObjectProperty<FXTeam> team2Property() {
        return team2;
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
