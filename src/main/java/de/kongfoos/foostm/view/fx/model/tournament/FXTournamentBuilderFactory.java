package de.kongfoos.foostm.view.fx.model.tournament;


import de.kongfoos.foostm.model.tournament.ATournamentBuilder;

public class FXTournamentBuilderFactory {
    public static Builder create() {
        return new Builder();
    }

    public static class Builder extends ATournamentBuilder<FXTournament> {
        @Override
        protected FXTournament getIntance() {
            return new FXTournament();
        }
    }
}
