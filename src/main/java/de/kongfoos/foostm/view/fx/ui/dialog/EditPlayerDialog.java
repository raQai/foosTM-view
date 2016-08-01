package de.kongfoos.foostm.view.fx.ui.dialog;

import de.kongfoos.foostm.model.player.Gender;
import de.kongfoos.foostm.view.fx.model.player.FXPlayer;
import de.kongfoos.foostm.view.fx.model.player.FXPlayerBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.validation.constraints.NotNull;

// TODO use simpleplayer isntead of FXPlayer after jpa merge
public class EditPlayerDialog extends ConfirmSaveDialog<FXPlayer> {
    private final FXPlayer player;

    private TextField forename;
    private TextField surname;
    private Gender gender;
    private DatePicker birthDate;
    private TextField club;
    private TextField itsf;
    private TextField dtfb;

    public EditPlayerDialog(@NotNull FXPlayer player) {
        super("Edit :: " + player.getName());
        setConfirmationTitle("Confirm changes");
        setConfirmationText("Do you really want to save changes to " + player.getName() + "?");
        this.player = player;
    }

    @Override
    FXPlayer createResult() {
        return FXPlayerBuilderFactory.create(forename.getText(), surname.getText())
                .gender(gender)
                .club(club.getText()).itsf(itsf.getText()).dtfb(dtfb.getText())
                .build();
    }

    @Override
    Node createContent() {
        final GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        int rowIndex = 0;
        forename = addLabeledTextField(grid, rowIndex++, "forename", player.getForename());
        surname = addLabeledTextField(grid, rowIndex++, "surname", player.getSurname());
        addLabeledNode(grid, rowIndex++, "gender", createGenderRBHBox());
        club = addLabeledTextField(grid, rowIndex++, "club", player.getClub());
        itsf = addLabeledTextField(grid, rowIndex++, "itsf", player.getItsf());
        dtfb = addLabeledTextField(grid, rowIndex++, "dtfb", player.getDtfb());

        return grid;
    }

    private HBox createGenderRBHBox() {
        final ToggleGroup group = new ToggleGroup();

        final RadioButton maleRB = new RadioButton("male");
        maleRB.setToggleGroup(group);
        maleRB.setSelected(player.isMale());
        maleRB.setUserData(Gender.MALE);

        final RadioButton femaleRB = new RadioButton("female");
        femaleRB.setToggleGroup(group);
        femaleRB.setSelected(player.isFemale());
        femaleRB.setUserData(Gender.FEMALE);

        group.selectedToggleProperty().addListener((o, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                gender = (Gender) group.getSelectedToggle().getUserData();
            }
        });

        final HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(maleRB, femaleRB);

        return hBox;
    }

    private TextField addLabeledTextField(GridPane grid, int rowIndex, String labelText, String fieldText) {
        final TextField field = new TextField(fieldText);
        addLabeledNode(grid, rowIndex, labelText, field);
        return field;
    }

    private void addLabeledNode(GridPane grid, int rowIndex, String labelText, Node c) {
        final Label label = new Label(labelText);
        grid.add(label, 0, rowIndex);
        grid.add(c, 1, rowIndex);
    }
}
