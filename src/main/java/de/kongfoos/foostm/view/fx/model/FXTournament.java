package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.ITournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FXTournament implements ITournament<FXDiscipline, FXRegistration> {
    private ObservableList<FXDiscipline> disciplines = FXCollections.observableArrayList();
    private ObservableList<FXRegistration> registrations = FXCollections.observableArrayList();

    @Override
    public List<FXDiscipline> getDisciplines() {
        return Collections.unmodifiableList(disciplines.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addDiscipline(@NotNull FXDiscipline discipline) {
        return disciplines.add(discipline);
    }

    @Override
    public boolean removeDiscipline(@NotNull FXDiscipline discipline) {
        return disciplines.remove(discipline);
    }

    @Override
    public List<FXRegistration> getRegistrations() {
        return Collections.unmodifiableList(registrations.stream().collect(Collectors.toList()));
    }

    @Override
    public boolean addRegistration(@NotNull FXRegistration registration) {
        return registrations.add(registration);
    }

    @Override
    public boolean removeRegistration(@NotNull FXRegistration registration) {
        return registrations.remove(registration);
    }
}
