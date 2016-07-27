package de.kongfoos.foostm.view.model.builder;

import de.kongfoos.foostm.model.discipline.DisciplineImpl;
import de.kongfoos.foostm.model.registration.RegistrationImpl;
import de.kongfoos.foostm.model.registration.RegistrationStatus;
import de.kongfoos.foostm.model.team.TeamImpl;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class RegistrationBuilder<R extends RegistrationImpl, T extends TeamImpl> {

    private final T team;
    private List<? extends DisciplineImpl> disciplines = Collections.emptyList();
    private RegistrationStatus status = RegistrationStatus.OPEN;

    public RegistrationBuilder(@NotNull T team) {
        this.team = team;
    }

    public RegistrationBuilder<R, T> setDisciplines(@NotNull Collection<? extends DisciplineImpl> disciplines) {
        this.disciplines = (List<? extends DisciplineImpl>) disciplines;
        return this;
    }

    public RegistrationBuilder<R, T> setStatus(@NotNull RegistrationStatus status) {
        this.status = status;
        return this;
    }

    protected abstract R getInstance();

    public R build() {
        final R registration = getInstance();
        registration.setTeam(team);
        disciplines.forEach(registration::addDiscipline);
        registration.setStatus(status);
        return registration;
    }
}
