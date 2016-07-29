package de.kongfoos.foostm.view.fx.db;

import de.kongfoos.foostm.io.db.PlayerDB;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.player.FXPlayerBuilderFactory;

// FIXME this should not be here...
public class FXPlayerDB extends PlayerDB<FXPlayer> {
    public static FXPlayerDB dummyDB() {
        final FXPlayerDB playerDB = new FXPlayerDB();
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Patrick", "Bogdan").itsf("123456789").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Patrick", "Bogdan").itsf("123456789").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Tobias", "Pohlmann").itsf("234567891").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Björn", "Bertz").itsf("345678912").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Matthias", "Wirth").itsf("456789123").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Hans", "Büttner").itsf("567891234").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Matthias", "Buder").itsf("678912345").build());
        playerDB.addPlayer(FXPlayerBuilderFactory.create("Matthias", "ABC").itsf("789123456").build());
        return playerDB;
    }
}
