package de.kongfoos.foostm.view.fx.model.match;

import de.kongfoos.foostm.view.fx.model.team.FXTeam;
import de.kongfoos.foostm.view.model.builder.MatchBuilder;

import javax.validation.constraints.NotNull;

public class FXMatchBuilderFactory {
    public static FXMatch buildMatch(@NotNull FXTeam team1, @NotNull FXTeam team2) {
        return new Builder(team1, team2).build();
    }

    private static class Builder extends MatchBuilder<FXMatch, FXTeam> {
        private Builder(FXTeam team1, FXTeam team2) {
            super(team1, team2);
        }

        @Override
        protected FXMatch getInstance() {
            return new FXMatch();
        }
    }
}
