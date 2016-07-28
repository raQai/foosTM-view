package de.kongfoos.foostm.view.fx.ui;

import com.google.common.base.CaseFormat;
import de.kongfoos.foostm.TestClass;
import de.kongfoos.foostm.model.registration.RegistrationStatus;
import de.kongfoos.foostm.model.team.Type;
import de.kongfoos.foostm.tournament.db.PlayerDB;
import de.kongfoos.foostm.view.PlayerUtils;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistration;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistrationBuilderFactory;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;
import de.kongfoos.foostm.view.fx.model.team.FXTeamBuilderFactory;
import de.kongfoos.foostm.view.fx.model.tournament.FXTournament;
import de.kongfoos.foostm.view.fx.ui.controls.AutoCompleteTextField;
import de.kongfoos.foostm.view.fx.ui.controls.AutoCompleteTextFieldEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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
        addTopMenu(root);
        this.setScene(new Scene(root, 700, 400));

        final SplitPane splitPane = new SplitPane();
        root.setCenter(splitPane);
        splitPane.getItems().addAll(createRegistrationPane(),
                createRegistrationTablePane());
        splitPane.setDividerPositions(.0);
    }

    private VBox createRegistrationPane() {
        final VBox vb = new VBox();
        vb.setPadding(new Insets(15));
        vb.setSpacing(10);
        vb.setMinWidth(240);

        final Label registrationLable = new Label("Register Players");
        registrationLable.setFont(new Font(registrationLable.getFont()
                .getName(), 20));
        vb.getChildren().add(registrationLable);

        final AutoCompleteTextField<FXPlayer> player1 = createPlayerTextField(vb);

        final AutoCompleteTextField<FXPlayer> player2 = createPlayerTextField(vb);

        // TODO only add teams that have no players that are already registered
        // if they play the same discipline
        // TODO add checkboxes for every discipline
        // TODO make checkboxes dependant on current registrations
        final Button btn1 = addButton(vb, "add doubles",
                e -> {
                    final FXPlayer p1 = player1.getLastSelectedEntry();

                    final FXPlayer p2 = player2.getLastSelectedEntry();

                    if (p1 != null && p2 != null) {
                        final FXTeam team = FXTeamBuilderFactory.buildDoubles(p1, p2);
                        final FXRegistration registration =
                                FXRegistrationBuilderFactory.create(team, getTournamentDoubleDisciplines()).build();
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

        addButton(vb, "OD", e -> tournament.getRegistrations().forEach(
                r -> {
                    final FXDiscipline od = tournament.getDisciplines().stream()
                            .filter(d -> d.getShortName().equals("OD"))
                            .findFirst().orElse(null);
                    if (od != null) {
                        r.addDiscipline(od);
                    }
                })
        );

        addButton(vb, "exit", e -> System.exit(0));

        addButton(vb, "test", e -> {
            test.setTest("lol");
            System.out.println(test.getTest());
        });

        return vb;
    }

    private VBox createRegistrationTablePane() {
        final VBox vb = new VBox();
        vb.setPadding(new Insets(15));
        vb.setSpacing(10);

        final Label registrationLable = new Label("Registrations");
        registrationLable.setFont(new Font(registrationLable.getFont()
                .getName(), 20));
        vb.getChildren().add(registrationLable);

        final Set<Type> types = tournament.getDisciplines().stream().map(FXDiscipline::getType)
                .collect(Collectors.toSet());

        if (types.size() == 1) {
            types.forEach(t -> vb.getChildren().add(
                    createRegistrationTableView(t)));
        } else {
            final TabPane tabPane = new TabPane();
            types.forEach(t -> {
                final Tab tab = new Tab();
                tab.setClosable(false);
                // TODO use resources for name
                tab.setText(CaseFormat.UPPER_UNDERSCORE.to(
                        CaseFormat.UPPER_CAMEL, t.name()));
                tab.setContent(createRegistrationTableView(t));
                tabPane.getTabs().add(tab);
            });
            vb.getChildren().add(tabPane);
        }

        return vb;
    }

    private TableView<FXRegistration> createRegistrationTableView(Type type) {
        final TableView<FXRegistration> tv = new TableView<>();
        final ObservableList<TableColumn<FXRegistration, ?>> columns = tv
                .getColumns();

        tv.setItems(tournament.registrations().filtered(
                r -> r.getTeam().getType().equals(type)));

        tv.setEditable(false);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // FIXME align column header to the left with css
        // http://stackoverflow.com/questions/23576867/javafx-how-to-align-only-one-column-header-in-tableview
        final TableColumn<FXRegistration, String> teamsCol = new TableColumn<>();
        teamsCol.setText(type.equals(Type.SINGLES) ? "Players" : "Teams");
        teamsCol.setStyle("-fx-alignment:CENTER-LEFT;");
        teamsCol.setCellValueFactory(r -> r.getValue().getTeam().nameProperty());
        columns.add(teamsCol);

        // TODO create tournament update event to update discipline columns if
        // fired
        tournament.getDisciplines().stream().filter(d -> d.getType().equals(type))
                .forEach(d -> columns.add(createDisciplineColumn(d)));

        final TableColumn<FXRegistration, RegistrationStatus> statusCol = new TableColumn<>(
                "Status");
        statusCol.setMinWidth(100);
        statusCol.setPrefWidth(100);
        statusCol.setMaxWidth(100);
        statusCol.setStyle("-fx-alignment:CENTER;");
        statusCol.setCellValueFactory(r -> r.getValue().statusProperty());
        columns.add(statusCol);

        return tv;
    }

    private TableColumn<FXRegistration, Boolean> createDisciplineColumn(
            FXDiscipline discipline) {
        final TableColumn<FXRegistration, Boolean> dCol = new TableColumn<>(
                discipline.getShortName());
        dCol.setMinWidth(40);
        dCol.setPrefWidth(40);
        dCol.setMaxWidth(40);
        dCol.setStyle("-fx-alignment:CENTER;");
        dCol.setCellValueFactory(param -> param.getValue().disciplinesMap().get(discipline));
        dCol.setCellFactory(callback -> {
            final CheckBoxTableCell<FXRegistration, Boolean> cb = new CheckBoxTableCell<>();
            cb.addEventFilter(
                    MouseEvent.MOUSE_CLICKED,
                    mouseEvent -> {
                        final FXRegistration selectedItem = dCol.getTableView()
                                .getSelectionModel().getSelectedItem();
                        if (discipline.allowsParticipation(selectedItem.getTeam())) {
                            selectedItem.flipDiscipline(discipline);
                        }
                    });
            return cb;
        });
        return dCol;
    }

    private void addTopMenu(BorderPane bp) {
        final MenuBar menuBar = new MenuBar();

        final Menu testMenu = new Menu("test");

        final MenuItem testMenuItem = new MenuItem("exit with key");
        testMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        testMenuItem.setOnAction(e -> System.exit(0));

        final MenuItem exitMenuItem = new MenuItem("exit");
        exitMenuItem.setOnAction(e -> System.exit(0));

        testMenu.getItems().addAll(testMenuItem, new SeparatorMenuItem(),
                exitMenuItem);
        menuBar.getMenus().add(testMenu);
        menuBar.setUseSystemMenuBar(true);

        bp.setTop(menuBar);
    }

    private AutoCompleteTextField<FXPlayer> createPlayerTextField(Pane pane) {
        final AutoCompleteTextField<FXPlayer> textField = new AutoCompleteTextField<FXPlayer>() {
            @Override
            public String printPopupEntry(FXPlayer entry) {
                return PlayerUtils.printWithITSF(entry);
            }
        };
        textField.addEntries(playerDB.getPlayers());
        textField.setPromptText("Player name or ITSF-ID");
        pane.getChildren().add(textField);
        return textField;
    }

    private Button addButton(Pane pane, String text, EventHandler<ActionEvent> e) {
        final Button button = new Button();
        button.setText(text);
        button.setOnAction(e);
        button.setMaxWidth(Double.MAX_VALUE);
        pane.getChildren().add(button);
        return button;
    }

    public Collection<FXDiscipline> getTournamentDoubleDisciplines() {
        return this.tournament.getDisciplines().stream()
                .filter(d -> d.getType().equals(Type.DOUBLES))
                .collect(Collectors.toList());
    }
}
