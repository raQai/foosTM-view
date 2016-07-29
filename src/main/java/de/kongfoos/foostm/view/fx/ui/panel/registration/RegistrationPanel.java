package de.kongfoos.foostm.view.fx.ui.panel.registration;

import de.kongfoos.foostm.io.db.PlayerDB;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistration;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistrationBuilderFactory;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;
import de.kongfoos.foostm.view.fx.model.team.FXTeamBuilderFactory;
import de.kongfoos.foostm.view.fx.model.tournament.FXTournament;
import de.kongfoos.foostm.view.fx.ui.control.ControlUtils;
import de.kongfoos.foostm.view.fx.ui.control.textfield.AutoCompleteTextField;
import de.kongfoos.foostm.view.fx.ui.control.textfield.AutoCompleteTextFieldEvent;
import de.kongfoos.foostm.view.fx.ui.control.textfield.PlayerTextField;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.stream.Collectors;

public class RegistrationPanel extends VBox {

    private final PlayerDB playerDB;

    public RegistrationPanel(PlayerDB playerDB, FXTournament tournament) {
        this.playerDB = playerDB;

        // TODO use css instead
        setupStyle();

        // TODO use css instead
        final Label registrationLable = new Label("Register Players");
        registrationLable.setFont(new Font(registrationLable.getFont().getName(), 20));
        getChildren().add(registrationLable);


        final AutoCompleteTextField<FXPlayer> player1 = new PlayerTextField(playerDB);

        final AutoCompleteTextField<FXPlayer> player2 = new PlayerTextField(playerDB);

        // TODO only add teams that have no players that are already registered
        // if they play the same discipline
        // TODO add checkboxes for every discipline
        // TODO make checkboxes dependant on current registrations
        final Button btn1 = ControlUtils.addButton(this, "add doubles",
                e -> {
                    final FXPlayer p1 = player1.getLastSelectedEntry();

                    final FXPlayer p2 = player2.getLastSelectedEntry();

                    if (p1 != null && p2 != null) {
                        final FXTeam team = FXTeamBuilderFactory.buildDoubles(p1, p2);
                        final FXRegistration registration =
                                FXRegistrationBuilderFactory.create(team,
                                        tournament.getDisciplines().stream()
                                                .filter(d -> d.getType().isDoubles())
                                                .collect(Collectors.toList())).build();
                        tournament.addRegistration(registration);

                        player1.clear();
                        player2.clear();
                    }
                });
        btn1.setDisable(true);

        player1.addEventHandler(
                AutoCompleteTextFieldEvent.UPDATE,
                e -> btn1.setDisable(player1.getLastSelectedEntry() == null
                        || player2.getLastSelectedEntry() == null));
        player2.addEventHandler(
                AutoCompleteTextFieldEvent.UPDATE,
                e -> btn1.setDisable(player1.getLastSelectedEntry() == null
                        || player2.getLastSelectedEntry() == null));

        ControlUtils.addButton(this, "OD", e -> tournament.getRegistrations().forEach(
                r -> {
                    final FXDiscipline od = tournament.getDisciplines().stream()
                            .filter(d -> d.getShortName().equals("OD"))
                            .findFirst().orElse(null);
                    if (od != null) {
                        r.addDiscipline(od);
                    }
                })
        );

        ControlUtils.addButton(this, "exit", e -> System.exit(0));
    }

    private void setupStyle() {
        setPadding(new Insets(15));
        setSpacing(10);
        setMinWidth(240);
    }
}
