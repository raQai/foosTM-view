package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import de.kongfoos.foostm.view.fx.model.discipline.Discipline;
import de.kongfoos.foostm.view.fx.model.player.Player;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by patrick on 18/06/16.
 */
public class Team {
    private final List<Player> players;
    private final Type type;
    private final SimpleStringProperty name = new SimpleStringProperty();

    private Team(List<Player> players, Type type) {
        this.players = players;
        this.type = type;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name.getValue();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isAllowedToParticipate(Discipline discipline) {
        return discipline.getParticipationRules().stream().allMatch(rule -> rule.test(this));
    }

    public static class Builder {
        private final List<Player> players;
        private final Type type;
        private String name;

        public Builder(List<Player> players, Type type) {
            Preconditions.checkArgument(players != null && !players.isEmpty(), "Player list null or emtpy not allowed");
            this.players = players;
            this.type = type;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Team build() {
            final Team team = new Team(players, type);
            if (Strings.isNullOrEmpty(name)) {
                name(Joiner.on(" / ").join(players));
            }
            team.setName(this.name);
            return team;
        }
    }
}
