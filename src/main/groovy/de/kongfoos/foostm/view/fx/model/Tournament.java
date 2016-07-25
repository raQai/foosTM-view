package de.kongfoos.foostm.view.fx.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import de.kongfoos.foostm.view.fx.model.discipline.Discipline;

public class Tournament {
    private final List<Discipline> disciplines = Lists.newArrayList();
    private final ObservableList<Registration> registrations = FXCollections.observableArrayList();

    public Tournament() {
    }

    public void addDiscipline(Discipline discipline) {
        Preconditions.checkArgument(!disciplines.stream().anyMatch(d ->
                d.getName().equals(discipline.getName()) ||
                        d.getShortName().equals(discipline.getShortName())
        ));
        disciplines.add(discipline);
    }

    public void removeDiscipline(Discipline discipline) {
        // TODO await confirmation because all teams will be deleted
        disciplines.remove(discipline);
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public Discipline getDisciplineByShortName(String shortName) {
        return disciplines.stream().filter(d -> d.getShortName().equals(shortName)).findFirst().orElse(null);
    }

    public void addRegistration(Registration registration) {
        Preconditions.checkNotNull(registration, "Null registration not allowed");
        registrations.add(registration);
    }

    public ObservableList<Registration> getRegistrations() {
        return registrations;
    }
}
