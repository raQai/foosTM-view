package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.IRegistration;
import de.kongfoos.foostm.model.RegistrationStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FXRegistration implements IRegistration<FXTeam, FXDiscipline> {
    private final ObjectProperty<FXTeam> team = new SimpleObjectProperty<>();
    private final ObservableList<FXDiscipline> disciplines = FXCollections.observableArrayList();
    private final ObjectProperty<RegistrationStatus> status = new SimpleObjectProperty<>(RegistrationStatus.OPEN);

    public FXRegistration(@NotNull FXTeam FXTeam) {
        this.team.set(FXTeam);
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
}
