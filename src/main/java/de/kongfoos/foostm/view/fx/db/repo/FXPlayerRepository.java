package de.kongfoos.foostm.view.fx.db.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import de.kongfoos.foostm.io.repo.PlayerRepository;
import de.kongfoos.foostm.model.player.Player;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.player.FXPlayerBuilderFactory;

public class FXPlayerRepository extends PlayerRepository {

	public FXPlayerRepository(EntityManager em) {
		super(em);
	}

	@Override
	public FXPlayer findById(long id) {
		Player p = super.findById(id);
		// TODO add all other attributes to object
		return FXPlayerBuilderFactory.create(p.getForename(), p.getSurname())
				.build();
	}

	public List<FXPlayer> findAllFXPlayers() {
		List<Player> players = super.findAllPlayers();
		List<FXPlayer> list = new ArrayList<FXPlayer>();
		for (Player p : players) {
			// TODO add all other attributes to object
			list.add(FXPlayerBuilderFactory.create(p.getForename(),
					p.getSurname()).build());
		}
		return list;
	}
	
}
