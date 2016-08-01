package de.kongfoos.foostm.view.fx.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.kongfoos.foostm.io.DBManager;
import de.kongfoos.foostm.io.db.ADatabase;
import de.kongfoos.foostm.io.repo.PlayerRepository;
import de.kongfoos.foostm.view.fx.db.repo.FXPlayerRepository;

public class FXDatabase extends ADatabase {

	private FXPlayerRepository playerRepo;

	public FXDatabase(DBManager db, String dbName, String openMode) {
		super(db, dbName, openMode);
	}

	public FXPlayerRepository getPlayerRepository() {
		if (playerRepo == null) {
			playerRepo = new FXPlayerRepository(super.emf.createEntityManager());
		}
		return playerRepo;
	}
	
	@Override
	protected String[] getPackagesToScan() {
		List<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(PlayerRepository.getPackagesToScan()));
		return list.toArray(new String[0]);
	}

}
