package de.kongfoos.foostm.view.fx.ui;

import java.io.File;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.kongfoos.foostm.io.DBManager;
import de.kongfoos.foostm.view.fx.db.FXDatabase;
import de.kongfoos.foostm.view.fx.model.tournament.FXTournament;
import de.kongfoos.foostm.view.fx.ui.control.menu.FoosTMMenuBar;
import de.kongfoos.foostm.view.fx.ui.panel.FoosTMPanel;
import de.kongfoos.foostm.view.fx.ui.panel.registration.RegistrationPanel;
import de.kongfoos.foostm.view.fx.ui.panel.registration.RegistrationTablePanel;

@Component
public class TestStage extends Stage {

	@Autowired
	private DBManager dbManager;
	
    private final FXTournament tournament;
    private FXDatabase playerDB;

    public TestStage(String title, FXTournament tournament) {
        this.tournament = tournament;
        this.setTitle(title);
        this.setMaximized(false);
    }
    
    @PostConstruct
    private void postConstruct(){
    	this.playerDB = new FXDatabase(dbManager, "player", DBManager.CREATE_DROP);
    	// TODO extract this code
    	try {
			playerDB.getPlayerRepository().loadPlayers(new File("src/main/resources/Spieler.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	init();
    }
    
    private void init(){
    	final BorderPane root = new BorderPane();
        this.setScene(new Scene(root, 700, 400));

        root.setTop(new FoosTMMenuBar());

        final SplitPane splitPane = new SplitPane();
        root.setCenter(splitPane);
        final FoosTMPanel registrationPanel = new RegistrationPanel(playerDB, tournament).init();
        final FoosTMPanel registrationTablePanel = new RegistrationTablePanel(tournament).init();

        splitPane.getItems().addAll(registrationPanel, registrationTablePanel);
        splitPane.setDividerPositions(.0);
    }

}
