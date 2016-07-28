package de.kongfoos.foostm.view.fx.model.registration;

import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.registration.RegistrationBuilder;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class FXRegistrationBuilderFactory {
    public static Builder create(@NotNull FXTeam team, @NotNull Collection<FXDiscipline> allDisciplines) {
        return new Builder(team, allDisciplines);
    }

    public static class Builder extends RegistrationBuilder<FXRegistration, FXTeam> {
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
