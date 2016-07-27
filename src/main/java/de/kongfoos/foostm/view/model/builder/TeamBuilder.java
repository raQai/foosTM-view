package de.kongfoos.foostm.view.model.builder;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.player.PlayerImpl;
import de.kongfoos.foostm.model.team.TeamImpl;
import de.kongfoos.foostm.model.team.Type;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public abstract class TeamBuilder<T extends TeamImpl, P extends PlayerImpl> {

    private final List<P> players = Lists.newArrayList();
    private final Type type;
    private String name;

    protected TeamBuilder(@NotNull Collection<P> players, @NotNull Type type) {
        this.players.addAll(players);
        this.type = type;
    }

    public TeamBuilder<T, P> name(String name) {
        this.name = name;
        return this;
    }

    protected abstract T getInstance();

    public T build() {
        final T team = getInstance();
        players.forEach(team::addPlayer);
        team.setType(type);
        if (!type.equals(Type.TEAM)) {
            team.setName(Joiner.on(" / ").join(players));
        } else {
            team.setName(name);
        }
        return team;
    }
}
