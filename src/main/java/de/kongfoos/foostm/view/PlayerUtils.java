package de.kongfoos.foostm.view;

import com.google.common.base.Strings;
import de.kongfoos.foostm.model.player.PlayerImpl;

public class PlayerUtils {
    private static final String UNSPECIFIED = "<unspecified>";

    public static String printWithDTFB(PlayerImpl player) {
        return printWithID(player, player.getDtfb());
    }

    public static String printWithITSF(PlayerImpl player) {
        return printWithID(player, player.getItsf());
    }

    private static String printWithID(PlayerImpl player, String id) {
        return player.toString() + " (" + (!Strings.isNullOrEmpty(id) ? id : UNSPECIFIED) + ")";
    }
}
