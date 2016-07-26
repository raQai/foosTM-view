package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.IRegistration;
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
import java.util.List;
import java.util.stream.Collectors;

public class FXRegistration implements IRegistration<FXTeam, FXDiscipline> {
    private final ObjectProperty<FXTeam> team = new SimpleObjectProperty<>();
    private final ObservableList<FXDiscipline> disciplines = FXCollections.observableArrayList();
    private final ObjectProperty<RegistrationStatus> status = new SimpleObjectProperty<>(RegistrationStatus.OPEN);

    private final ObservableMap<FXDiscipline, BooleanProperty> disciplinesMap = FXCollections.observableHashMap();

    public FXRegistration(@NotNull FXTeam FXTeam, @NotNull Collection<FXDiscipline> allDisciplines) {
        this.team.set(FXTeam);
        allDisciplines.forEach(d -> disciplinesMap.put(d, new SimpleBooleanProperty()));

        disciplines().addListener(new ListChangeListener<FXDiscipline>() {
            @Override
            public void onChanged(Change<? extends FXDiscipline> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        c.getAddedSubList().forEach(d -> disciplinesMap.get(d).set(true));
                    } else if (c.wasRemoved()) {
                        c.getRemoved().forEach(d -> disciplinesMap.get(d).set(false));
                    }
                }
                if (disciplines.isEmpty()) {
                    setStatus(RegistrationStatus.OPEN);
                } else {
                    setStatus(RegistrationStatus.REGISTERED);
                }
            }
        });
    }

    @Override
    public FXTeam getTeam() {
        return this.team.get();
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
    public List<FXDiscipline> getDisciplines() {
        return Collections.unmodifiableList(disciplines.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addDiscipline(@NotNull FXDiscipline discipline) {
        return (!disciplines.contains(discipline)) && disciplines.add(discipline);
    }

    @Override
    public boolean removeDiscipline(@NotNull FXDiscipline discipline) {
        return disciplines.remove(discipline);
    }

    public ObservableList<FXDiscipline> disciplines() {
        return disciplines;
    }

    public ObservableMap<FXDiscipline, BooleanProperty> disciplinesMap() {
        return disciplinesMap;
    }
}
