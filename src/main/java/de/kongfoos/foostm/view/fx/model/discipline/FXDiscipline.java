package de.kongfoos.foostm.view.fx.model.discipline;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.discipline.Discipline;
import de.kongfoos.foostm.model.team.Type;
import de.kongfoos.foostm.view.fx.model.match.FXMatch;
import de.kongfoos.foostm.view.fx.model.table.FXTable;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;

public class FXDiscipline extends Discipline<FXTeam, FXMatch, FXTable> {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty shortName = new SimpleStringProperty();
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();
    private final ObservableList<Predicate<FXTeam>> rules = FXCollections.observableArrayList();
    private final ObservableList<FXTeam> teams = FXCollections.observableArrayList();
    private final ObservableList<FXMatch> matches = FXCollections.observableArrayList();
    private final ObservableList<FXTable> tables = FXCollections.observableArrayList();

    FXDiscipline() {
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public void setName(String s) {
        this.name.set(s);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String getShortName() {
        return shortName.get();
    }

    @Override
    public void setShortName(String s) {
        this.shortName.set(s);
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    @Override
    public List<Predicate<FXTeam>> getParticipationRules() {
        return Collections.unmodifiableList(rules.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addRule(@NotNull Predicate<FXTeam> predicate) {
        return rules.add(predicate);
    }

    @Override
    public boolean removeRule(@NotNull Predicate<FXTeam> predicate) {
        return rules.remove(predicate);
    }

    public ObservableList<Predicate<FXTeam>> rules() {
        return rules;
    }

    @Override
    public List<FXTeam> getTeams() {
        return Collections.unmodifiableList(teams.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addTeam(@NotNull FXTeam team) {
        return teams.add(team);
    }

    @Override
    public boolean removeTeam(@NotNull FXTeam team) {
        return teams.remove(team);
    }

    public ObservableList<FXTeam> teams() {
        return teams;
    }

    @Override
    public List<FXMatch> getMatches() {
        return Collections.unmodifiableList(matches.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addMatch(@NotNull FXMatch match) {
        return matches.add(match);
    }

    @Override
    public boolean removeMatch(@NotNull FXMatch match) {
        return matches.remove(match);
    }

    public ObservableList<FXMatch> matches() {
        return matches;
    }

    @Override
    public List<FXTable> getTables() {
        return Collections.unmodifiableList(tables.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addTable(@NotNull FXTable table) {
        return tables.add(table);
    }

    @Override
    public boolean removeTable(@NotNull FXTable table) {
        return tables.remove(table);
    }

    public ObservableList<FXTable> tables() {
        return tables;
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
}
