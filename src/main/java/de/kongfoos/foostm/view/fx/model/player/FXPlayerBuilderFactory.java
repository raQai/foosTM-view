package de.kongfoos.foostm.view.fx.model.player;

import de.kongfoos.foostm.model.player.PlayerBuilder;

import javax.validation.constraints.NotNull;

public class FXPlayerBuilderFactory {
    public static Builder create(@NotNull String forname, @NotNull String surname) {
        return new Builder(forname, surname);
    }

    public static class Builder extends PlayerBuilder<FXPlayer> {
        private Builder(String forename, String surname) {
            super(forename, surname);
        }

        @Override
        protected FXPlayer getInstance() {
            return new FXPlayer();
        }
    }
}
