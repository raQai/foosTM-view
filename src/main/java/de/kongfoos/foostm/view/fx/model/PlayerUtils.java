package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Strings;
import de.kongfoos.foostm.model.player.Player;

public class PlayerUtils {

    private static final String UNSPECIFIED = "<unspecified>";

    public static String printWithDTFB(Player player) {
        return printWithID(player, player.getDtfb());
    }
    public static String printWithITSF(Player player) {
        return printWithID(player, player.getItsf());
    }
    private static String printWithID(Player player, String id) {
        return player.toString() + " (" + (!Strings.isNullOrEmpty(id) ? id : UNSPECIFIED) + ")";
    }
}
