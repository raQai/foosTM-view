package de.kongfoos.foostm.view.model.builder;

import de.kongfoos.foostm.model.match.MatchImpl;
import de.kongfoos.foostm.model.match.MatchStatus;
import de.kongfoos.foostm.model.team.TeamImpl;

public abstract class MatchBuilder<M extends MatchImpl, T extends TeamImpl> {

    private final T team1;
    private final T team2;

    protected MatchBuilder(T team1, T team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    protected abstract M getInstance();

    public M build() {
        final M match = getInstance();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setStatus(MatchStatus.OPEN);
        return match;
    }
}
