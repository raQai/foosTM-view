package de.kongfoos.foostm.view.fx.model.tournament;


import de.kongfoos.foostm.model.tournament.TournamentBuilder;

public class FXTournamentBuilderFactory {
    public static Builder create() {
        return new Builder();
    }

    public static class Builder extends TournamentBuilder<FXTournament> {
        @Override
        protected FXTournament getIntance() {
            return new FXTournament();
        }
    }
}
