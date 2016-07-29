package de.kongfoos.foostm.view.fx.ui.panel.registration;

import com.google.common.base.CaseFormat;
import de.kongfoos.foostm.model.registration.RegistrationStatus;
import de.kongfoos.foostm.model.team.Type;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.registration.FXRegistration;
import de.kongfoos.foostm.view.fx.model.tournament.FXTournament;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Set;
import java.util.stream.Collectors;

public class RegistrationTablePanel extends VBox {

    private final FXTournament tournament;

    public RegistrationTablePanel(FXTournament tournament) {
        this.tournament = tournament;

        // TODO use css
        setupStyle();

        // TODO use css
        final Label registrationLable = new Label("Registrations");
        registrationLable.setFont(new Font(registrationLable.getFont()
                .getName(), 20));
        getChildren().add(registrationLable);

        final Set<Type> types = tournament.getDisciplines().stream().map(FXDiscipline::getType)
                .collect(Collectors.toSet());

        if (types.size() == 1) {
            types.forEach(t -> getChildren().add(createRegistrationTableView(t)));
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
            getChildren().add(tabPane);
        }
    }

    private void setupStyle() {
        setPadding(new Insets(15));
        setSpacing(10);
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
}
