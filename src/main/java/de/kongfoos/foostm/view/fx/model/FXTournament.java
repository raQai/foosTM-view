package de.kongfoos.foostm.view.fx.model;

import com.google.common.base.Preconditions;
import de.kongfoos.foostm.model.Registration;
import de.kongfoos.foostm.model.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FXTournament extends Tournament {
    private final ObservableList<FXRegistration> fxRegistrations = FXCollections.observableArrayList();

    @Override
    public void addRegistration(@NotNull Registration FXRegistration) {
        Preconditions.checkNotNull(FXRegistration, "Null registration not allowed");
        fxRegistrations.add((FXRegistration) FXRegistration);
    }

    @Override
    public ObservableList<FXRegistration> getRegistrations() {
        return fxRegistrations;
    }
}
