package de.kongfoos.foostm.view.fx.model.match;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.match.AMatchBuilder;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;

public class FXMatchBuilderFactory {
    public static FXMatch buildMatch(@NotNull FXTeam team1, @NotNull FXTeam team2) {
        return new Builder(team1, team2).build();
    }

    private static class Builder extends AMatchBuilder<FXMatch, FXTeam> {
        private Builder(FXTeam team1, FXTeam team2) {
            super(team1, team2);
        }

        @Override
        protected FXMatch getInstance() {
            return new FXMatch();
        }
    }
}
