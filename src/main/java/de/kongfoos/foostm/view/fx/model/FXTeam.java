package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import de.kongfoos.foostm.model.Team;
import de.kongfoos.foostm.model.Type;
import de.kongfoos.foostm.model.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FXTeam extends Team {
    private final StringProperty name = new SimpleStringProperty();

    protected FXTeam(List<Player> players, Type type) {
        super(players, type);
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public static class Builder extends Team.Builder {
        private final List<Player> players;
        private final Type type;
        private String name;

        public Builder(@NotNull List<Player> players, Type type) {
            super(players, type);
            this.players = players;
            this.type = type;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public FXTeam build() {
            final FXTeam FXTeam = new FXTeam(players, type);
            if (Strings.isNullOrEmpty(name)) {
                name(Joiner.on(" / ").join(players));
            }
            FXTeam.setName(this.name);
            return FXTeam;
        }
    }
}
