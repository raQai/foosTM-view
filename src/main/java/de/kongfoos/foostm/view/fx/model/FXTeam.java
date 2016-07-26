package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.ITeam;
import de.kongfoos.foostm.model.Type;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FXTeam implements ITeam<FXPlayer> {
    private final ObservableList<FXPlayer> players = FXCollections.observableArrayList();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();

    private FXTeam(@NotNull Collection<FXPlayer> players, @NotNull Type type) {
        players.forEach(this.players::add);
        this.type.set(type);
    }

    @Override
    public List<FXPlayer> getPlayers() {
        return players.stream().collect(Collectors.toList());
    }

    public ObservableList<FXPlayer> playersProperty() {
        return players;
    }

    @Override
    public Type getType() {
        return type.get();
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

    public static class Builder {
        private final List<FXPlayer> players = Lists.newArrayList();
        private final Type type;
        private String name;

        public Builder(@NotNull Collection<FXPlayer> players, @NotNull Type type) {
            players.forEach(this.players::add);
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
