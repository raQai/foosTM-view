package de.kongfoos.foostm.view.fx.model.team;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.team.Team;
import de.kongfoos.foostm.model.team.Type;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;

public class FXTeam extends Team<FXPlayer> {
    private final ObservableList<FXPlayer> players = FXCollections.observableArrayList();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();

    FXTeam() {
    }

    @Override
    public List<FXPlayer> getPlayers() {
        return Collections.unmodifiableList(players.stream().collect(Collectors.toList()));
    }
    
    @Override
    public boolean addPlayer(@NotNull FXPlayer player) {
        return this.players.add(player);
    }

    @Override
    public boolean removePlayer(@NotNull FXPlayer player) {
        return this.players.remove(player);
    }

    public ObservableList<FXPlayer> playersProperty() {
        return players;
    }

    @Override
    public Type getType() {
        return type.get();
    }

    @Override
    public void setType(@NotNull Type type) {
        this.type.set(type);
    }

    public ObjectProperty<Type> typeProperty() {
        return type;
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

}
