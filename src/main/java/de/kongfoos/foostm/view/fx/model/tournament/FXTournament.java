package de.kongfoos.foostm.view.fx.model.tournament;

import de.kongfoos.foostm.model.tournament.ATournament;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FXTournament extends ATournament<FXDiscipline, FXRegistration> {
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
