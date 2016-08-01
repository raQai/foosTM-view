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
import de.kongfoos.foostm.view.fx.ui.control.textfield.AutoCompleteTextFieldEvent;
import de.kongfoos.foostm.view.fx.ui.control.textfield.PlayerTextField;
import de.kongfoos.foostm.view.fx.ui.dialog.EditPlayerDialog;
import de.kongfoos.foostm.view.fx.ui.panel.FoosTMPanel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationPanel extends FoosTMPanel {

    private final PlayerDB<FXPlayer> playerDB;
    private final FXTournament tournament;

    public RegistrationPanel(PlayerDB<FXPlayer> playerDB, FXTournament tournament) {
        super("Register Players");
        setMinWidth(240);
        this.playerDB = playerDB;
        this.tournament = tournament;
    }

    @Override
    protected List<? extends Node> addComponents() {
        final List<Control> nodes = new ArrayList<>();

        final PlayerTextField player1Field = new PlayerTextField(playerDB);

        final PlayerTextField player2Field = new PlayerTextField(playerDB);

        // TODO only add teams that have no players that are already registered if they play the same discipline
        // TODO add checkboxes for every discipline
        // TODO make checkboxes dependant on current registrations
        final Button addDoublesBtn = ControlUtils.getButton("add doubles",
                e -> {
                    final FXPlayer p1 = player1Field.getLastSelectedEntry();

                    final FXPlayer p2 = player2Field.getLastSelectedEntry();

                    if (p1 != null && p2 != null) {
                        final FXTeam team = FXTeamBuilderFactory.buildDoubles(p1, p2);
                        final FXRegistration registration =
                                FXRegistrationBuilderFactory.create(team,
                                        tournament.getDisciplines().stream()
                                                .filter(d -> d.getType().isDoubles())
                                                .collect(Collectors.toList())).build();
                        tournament.addRegistration(registration);

                        player1Field.clear();
                        player2Field.clear();
                    }
                });
        addDoublesBtn.setDisable(true);

        final Button player1EditBtn = ControlUtils.getButton("edit p1 dialog", e -> {
            final FXPlayer player = player1Field.getLastSelectedEntry();
            if (player != null) {
                final EditPlayerDialog editPlayerDialog = new EditPlayerDialog(player);
                editPlayerDialog.init().showAndWait()
                        .ifPresent(editPlayer -> {
                            if (!player.getForename().equals(editPlayer.getForename())) {
                                player.setForename(editPlayer.getForename());
                            }
                            if (!player.getSurname().equals(editPlayer.getSurname())) {
                                player.setSurname(editPlayer.getSurname());
                            }
                            if (!player.getClub().equals(editPlayer.getClub())) {
                                player.setClub(editPlayer.getClub());
                            }
                            if (!player.getDtfb().equals(editPlayer.getDtfb())) {
                                player.setDtfb(editPlayer.getDtfb());
                            }
                            if (!player.getItsf().equals(editPlayer.getItsf())) {
                                player.setItsf(editPlayer.getItsf());
                            }
                        });
            }
        });
        player1EditBtn.setDisable(true);

        final Button player2EditBtn = ControlUtils.getButton("edit p2 dialog", e -> {
            final FXPlayer player = player2Field.getLastSelectedEntry();
            if (player != null) {
                final EditPlayerDialog editPlayerDialog = new EditPlayerDialog(player);
                editPlayerDialog.init().showAndWait()
                        .ifPresent(editPlayer -> {
                            //TODO move to controller
                            if (!player.getForename().equals(editPlayer.getForename())) {
                                player.setForename(editPlayer.getForename());
                            }
                            if (!player.getSurname().equals(editPlayer.getSurname())) {
                                player.setSurname(editPlayer.getSurname());
                            }
                            if (!player.getClub().equals(editPlayer.getClub())) {
                                player.setClub(editPlayer.getClub());
                            }
                            if (!player.getDtfb().equals(editPlayer.getDtfb())) {
                                player.setDtfb(editPlayer.getDtfb());
                            }
                            if (!player.getItsf().equals(editPlayer.getItsf())) {
                                player.setItsf(editPlayer.getItsf());
                            }
                        });
            }
        });
        player2EditBtn.setDisable(true);

        player1Field.addEventHandler(
                AutoCompleteTextFieldEvent.UPDATE,
                e -> {
                    addDoublesBtn.setDisable(player1Field.getLastSelectedEntry() == null
                            || player2Field.getLastSelectedEntry() == null);
                    player1EditBtn.setDisable(player1Field.getLastSelectedEntry() == null);
                });
        player2Field.addEventHandler(
                AutoCompleteTextFieldEvent.UPDATE,
                e -> {
                    addDoublesBtn.setDisable(player1Field.getLastSelectedEntry() == null
                            || player2Field.getLastSelectedEntry() == null);
                    player2EditBtn.setDisable(player1Field.getLastSelectedEntry() == null);
                });

        final Button allODBtn = ControlUtils.getButton("OD", e -> tournament.getRegistrations().forEach(
                r -> {
                    final FXDiscipline od = tournament.getDisciplines().stream()
                            .filter(d -> d.getShortName().equals("OD"))
                            .findFirst().orElse(null);
                    if (od != null) {
                        r.addDiscipline(od);
                    }
                })
        );

        final Button exitBtn = ControlUtils.getButton("exit", e -> System.exit(0));

        nodes.add(player1Field);
        nodes.add(player1EditBtn);
        nodes.add(player2Field);
        nodes.add(player2EditBtn);
        nodes.add(addDoublesBtn);
        nodes.add(allODBtn);
        nodes.add(exitBtn);
        return nodes;
    }
}
