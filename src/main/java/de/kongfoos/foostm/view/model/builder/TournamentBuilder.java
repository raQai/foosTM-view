package de.kongfoos.foostm.view.model.builder;

import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.discipline.DisciplineImpl;
import de.kongfoos.foostm.model.registration.RegistrationImpl;
import de.kongfoos.foostm.model.tournament.TournamentImpl;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public abstract class TournamentBuilder<T extends TournamentImpl> {
    private List<? extends DisciplineImpl> disciplines = Lists.newArrayList();
    private List<? extends RegistrationImpl> registrations = Lists.newArrayList();

    protected TournamentBuilder() {
    }

    public TournamentBuilder<T> setDisciplines(@NotNull Collection<? extends DisciplineImpl> disciplines) {
        this.disciplines = (List<? extends DisciplineImpl>) disciplines;
        return this;
    }

    public TournamentBuilder<T> setRegistrations(@NotNull Collection<? extends RegistrationImpl> registrations) {
        this.registrations = (List<? extends RegistrationImpl>) registrations;
        return this;
    }

    protected abstract T getIntance();

    public T build() {
        final T tournament = getIntance();
        disciplines.forEach(tournament::addDiscipline);
        registrations.forEach(tournament::addRegistration);
        return tournament;
    }
}
