package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Preconditions;

import de.kongfoos.foostm.view.fx.model.discipline.Discipline;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.Collection;


/**
 * Created by patrick on 23/06/16.
 */
public class Registration {
    private final SimpleObjectProperty<Team> team = new SimpleObjectProperty<>();
    private final Type type;

    private final SimpleMapProperty<Discipline, SimpleBooleanProperty> disciplineMap = new SimpleMapProperty<>(FXCollections.observableHashMap());
    private final SimpleObjectProperty<RegistrationStatus> status = new SimpleObjectProperty<>(RegistrationStatus.OPEN);

    public Registration(Team team, Collection<Discipline> disciplines) {
        this.team.set(team);
        this.type = team.getType();
        disciplines.forEach(d -> disciplineMap.put(d, new SimpleBooleanProperty()));
    }

    public Team getTeam() {
        return team.get();
    }

    public Type getType() {
        return type;
    }

    public ObservableMap<Discipline, SimpleBooleanProperty> getDisciplineMap() {
        return disciplineMap.get();
    }

    public SimpleMapProperty<Discipline, SimpleBooleanProperty> disciplineMapProperty() {
        return disciplineMap;
    }

    public void registerForDiscipline(Discipline discipline) {
        Preconditions.checkNotNull(discipline, "cannot create registration for null discipline");
        disciplineMap.get(discipline).set(true);
        setStatus(RegistrationStatus.REGISTERED);
    }

    public void unregisterFromDiscipline(Discipline discipline) {
        Preconditions.checkNotNull(discipline, "cannot create registration for null discipline");
        disciplineMap.get(discipline).set(false);
        if (!disciplineMap.values().stream().anyMatch(BooleanPropertyBase::get)) {
            setStatus(RegistrationStatus.OPEN);
        }
    }

    public String getName() {
        return team.get().getName();
    }

    public String getStatus() {
        return status.get().toString();
    }

    public SimpleObjectProperty<RegistrationStatus> statusProperty() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status.set(status);
    }


}
