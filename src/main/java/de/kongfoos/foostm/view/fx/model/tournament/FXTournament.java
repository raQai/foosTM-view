package de.kongfoos.foostm.view.fx.model.tournament;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.tournament.Tournament;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistration;

public class FXTournament extends Tournament<FXDiscipline, FXRegistration> {
    private ObservableList<FXDiscipline> disciplines = FXCollections.observableArrayList();
    private ObservableList<FXRegistration> registrations = FXCollections.observableArrayList();

    public FXTournament() {
    }

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

    public ObservableList<FXRegistration> registrations() {
        return registrations;
    }
}
