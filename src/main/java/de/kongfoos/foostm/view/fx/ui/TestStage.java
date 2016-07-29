package de.kongfoos.foostm.view.fx.ui;

import de.kongfoos.foostm.io.db.PlayerDB;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.tournament.FXTournament;
import de.kongfoos.foostm.view.fx.ui.control.ControlUtils;
import de.kongfoos.foostm.view.fx.ui.control.menu.FoosTMMenuBar;
import de.kongfoos.foostm.view.fx.ui.panel.registration.RegistrationPanel;
import de.kongfoos.foostm.view.fx.ui.panel.registration.RegistrationTablePanel;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class TestStage extends Stage {

    private final PlayerDB playerDB;
    private final FXTournament tournament;
    @Autowired
    private TestClass test;

    public TestStage(String title, PlayerDB playerDB, FXTournament tournament) {
        this.playerDB = playerDB;
        this.tournament = tournament;

        this.setTitle(title);
        this.setMaximized(false);

        final BorderPane root = new BorderPane();
        this.setScene(new Scene(root, 700, 400));

        root.setTop(new FoosTMMenuBar());

        final SplitPane splitPane = new SplitPane();
        root.setCenter(splitPane);
        final RegistrationPanel registrationPanel = new RegistrationPanel(playerDB, tournament);
        ControlUtils.addButton(registrationPanel, "test", e -> {
            test.setTest("lol");
            System.out.println(test.getTest());
        });

        final RegistrationTablePanel registrationTablePanel = new RegistrationTablePanel(tournament);

        splitPane.getItems().addAll(registrationPanel, registrationTablePanel);
        splitPane.setDividerPositions(.0);
    }


}
