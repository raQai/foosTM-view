package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.Discipline;
import de.kongfoos.foostm.model.Registration;
import de.kongfoos.foostm.model.RegistrationStatus;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

public class FXRegistration extends Registration {
    private final ObjectProperty<FXTeam> team = new SimpleObjectProperty<>();
    private final ObservableList<Discipline> disciplines = FXCollections.observableArrayList();
    private final ObjectProperty<RegistrationStatus> status =
            new SimpleObjectProperty<>(RegistrationStatus.OPEN);
    private final ObservableMap<Discipline, BooleanProperty> disciplineMap = FXCollections.observableHashMap();

    public FXRegistration(FXTeam FXTeam, @NotNull Collection<Discipline> allDisciplines) {
        super(FXTeam, Collections.emptyList());
        setTeam(FXTeam);
        allDisciplines.forEach(d -> disciplineMap.put(d, new SimpleBooleanProperty()));

        getDisciplines().addListener(new ListChangeListener<Discipline>() {
            @Override
            public void onChanged(Change<? extends Discipline> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        c.getAddedSubList().forEach(d -> getDisciplineMap().get(d).set(true));
                    } else if (c.wasRemoved()) {
                        c.getRemoved().forEach(d -> getDisciplineMap().get(d).set(false));
                    }
                }
            }
        });
    }

    @Override
    public FXTeam getTeam() {
        return this.team.getValue();
    }

    private void setTeam(FXTeam FXTeam) {
        this.team.set(FXTeam);
    }

    public ObjectProperty<FXTeam> teamProperty() {
        return team;
    }

    @Override
    public RegistrationStatus getStatus() {
        return status.get();
    }

    public void setStatus(RegistrationStatus status) {
        if (!this.status.get().equals(status)) {
            this.status.set(status);
        }
    }

    public ObjectProperty<RegistrationStatus> statusProperty() {
        return status;
    }

    @Override
    public ObservableList<Discipline> getDisciplines() {
        return disciplines;
    }

    public ObservableMap<Discipline, BooleanProperty> getDisciplineMap() {
        return disciplineMap;
    }

    @Override
    public void addDiscipline(@NotNull Discipline discipline) {
        if (!getDisciplines().contains(discipline)) {
            getDisciplines().add(discipline);
            setStatus(RegistrationStatus.REGISTERED);
        }
    }

    @Override
    public void removeDiscipline(@NotNull Discipline discipline) {
        getDisciplines().remove(discipline);
        if (getDisciplines().isEmpty()) {
            setStatus(RegistrationStatus.OPEN);
        }
    }

    public void flipDiscipline(@NotNull Discipline discipline) {
        if (getDisciplines().contains(discipline)) {
            removeDiscipline(discipline);
        } else {
            addDiscipline(discipline);
        }
    }
}
