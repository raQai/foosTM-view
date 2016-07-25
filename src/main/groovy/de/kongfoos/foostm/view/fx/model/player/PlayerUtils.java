package de.kongfoos.foostm.view.fx.model.player;

import com.google.common.base.Strings;

/**
 * Created by patrick on 17/06/16.
 */
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
