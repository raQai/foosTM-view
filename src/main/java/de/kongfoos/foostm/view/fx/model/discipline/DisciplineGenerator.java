package de.kongfoos.foostm.view.fx.model.discipline;

import de.kongfoos.foostm.model.player.APlayer;

public class DisciplineGenerator {
    public static FXDiscipline od() {
        return FXDisciplineBuilderFactory.buildDoubles("Offenes Doppel", "OD");
    }

    public static FXDiscipline oe() {
        return FXDisciplineBuilderFactory.buildSingles("Offenes Einzel", "OE");
    }

    public static FXDiscipline dd() {
        final FXDiscipline dd = FXDisciplineBuilderFactory.buildDoubles("Damen Doppel", "DD");
        dd.addRule(t -> t.getPlayers().stream().allMatch(APlayer::isFemale));
        return dd;
    }
}
