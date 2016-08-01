package de.kongfoos.foostm.view.fx.model.registration;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

import de.kongfoos.foostm.model.registration.ARegistrationBuilder;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;

public class FXRegistrationBuilderFactory {
    public static Builder create(@NotNull FXTeam team, @NotNull Collection<FXDiscipline> allDisciplines) {
        return new Builder(team, allDisciplines);
    }

    public static class Builder extends ARegistrationBuilder<FXRegistration, FXTeam> {
        final List<FXDiscipline> allDisciplines = Lists.newArrayList();

        private Builder(@NotNull FXTeam team, @NotNull Collection<FXDiscipline> allDisciplines) {
            super(team);
            this.allDisciplines.addAll(allDisciplines);
        }

        @Override
        protected FXRegistration getInstance() {
            final FXRegistration registration = new FXRegistration();
            registration.setAllDisciplines(allDisciplines);
            return registration;
        }
    }
}
