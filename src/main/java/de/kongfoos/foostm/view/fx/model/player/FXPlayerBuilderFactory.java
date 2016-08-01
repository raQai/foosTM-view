package de.kongfoos.foostm.view.fx.model.player;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.player.APlayerBuilder;

public class FXPlayerBuilderFactory {
    public static Builder create(@NotNull String forname, @NotNull String surname) {
        return new Builder(forname, surname);
    }

    public static class Builder extends APlayerBuilder<FXPlayer> {
        private Builder(String forename, String surname) {
            super(forename, surname);
        }

        @Override
        protected FXPlayer getInstance() {
            return new FXPlayer();
        }
    }
}
