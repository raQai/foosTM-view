package de.kongfoos.foostm.view.fx.model.team;

import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.team.Type;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.model.builder.TeamBuilder;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FXTeamBuilderFactory {
    public static FXTeam buildSingles(@NotNull FXPlayer player) {
        return new Builder(Collections.singletonList(player), Type.SINGLES).build();
    }

    public static FXTeam buildDoubles(@NotNull FXPlayer player1, @NotNull FXPlayer player2) {
        final List<FXPlayer> players = Lists.newArrayList();
        players.add(player1);
        players.add(player2);
        return new Builder(players, Type.DOUBLES).build();
    }

    public static FXTeam buildTeam(@NotNull Collection<FXPlayer> players, @NotNull String name) {
        return new Builder(players, Type.TEAM).name(name).build();
    }

    private static class Builder extends TeamBuilder<FXTeam, FXPlayer> {
        private Builder(@NotNull Collection<FXPlayer> players, @NotNull Type type) {
            super(players, type);
        }

        @Override
        protected FXTeam getInstance() {
            return new FXTeam();
        }
    }
}
