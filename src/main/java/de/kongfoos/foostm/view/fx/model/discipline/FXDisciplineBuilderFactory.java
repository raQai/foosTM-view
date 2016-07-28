package de.kongfoos.foostm.view.fx.model.discipline;

import de.kongfoos.foostm.model.discipline.ADisciplineBuilder;
import de.kongfoos.foostm.model.team.Type;

import javax.validation.constraints.NotNull;

public class FXDisciplineBuilderFactory {
    public static FXDiscipline buildSingles(@NotNull String name, @NotNull String shortName) {
        return new Builder(name, shortName, Type.SINGLES).build();
    }

    public static FXDiscipline buildDoubles(@NotNull String name, @NotNull String shortName) {
        return new Builder(name, shortName, Type.DOUBLES).build();
    }

    public static FXDiscipline buildTeam(@NotNull String name, @NotNull String shortName) {
        return new Builder(name, shortName, Type.TEAM).build();
    }

    private static class Builder extends ADisciplineBuilder<FXDiscipline> {
        private Builder(@NotNull String name, @NotNull String shortName, @NotNull Type type) {
            super(name, shortName, type);
        }

        @Override
        protected FXDiscipline getInstance() {
            return new FXDiscipline();
        }
    }
}
