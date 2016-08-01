package de.kongfoos.foostm.view.fx.ui.control.textfield;

import de.kongfoos.foostm.view.PlayerUtils;
import de.kongfoos.foostm.view.fx.db.FXDatabase;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;

public class PlayerTextField extends AutoCompleteTextField<FXPlayer> {
    public PlayerTextField(FXDatabase db) {
        setPromptText("Player name or ITSF-ID");
        addEntries(db.getPlayerRepository().findAllFXPlayers());
    }

    @Override
    public String printPopupEntry(FXPlayer entry) {
        return PlayerUtils.printWithITSF(entry);
    }
}
