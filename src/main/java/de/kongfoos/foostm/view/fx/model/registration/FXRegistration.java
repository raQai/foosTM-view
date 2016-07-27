package de.kongfoos.foostm.view.fx.model.registration;

import com.google.common.collect.Lists;
import de.kongfoos.foostm.model.registration.RegistrationImpl;
import de.kongfoos.foostm.model.registration.RegistrationStatus;
import de.kongfoos.foostm.view.fx.model.discipline.FXDiscipline;
import de.kongfoos.foostm.view.fx.model.team.FXTeam;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FXRegistration extends RegistrationImpl<FXTeam, FXDiscipline> {
    private final ObjectProperty<FXTeam> team = new SimpleObjectProperty<>();
    private final ObservableList<FXDiscipline> disciplines = FXCollections.observableArrayList();
    private final ObservableMap<FXDiscipline, BooleanProperty> disciplinesMap = FXCollections.observableHashMap();
    private final ObjectProperty<RegistrationStatus> status = new SimpleObjectProperty<>(RegistrationStatus.OPEN);

    FXRegistration() {
    }

    /**
     * @deprecated do not use constructor with parameters and use package local constructor with builder instead
     */
    @Deprecated
    public FXRegistration(@NotNull FXTeam team, @NotNull Collection<FXDiscipline> allDisciplines) {
        this.team.set(team);
        allDisciplines.forEach(d -> disciplinesMap.put(d, new SimpleBooleanProperty()));
    }

    @Override
    public FXTeam getTeam() {
        return this.team.get();
    }

    @Override
    public void setTeam(@NotNull FXTeam team) {
        this.team.set(team);
    }

    public ObjectProperty<FXTeam> teamProperty() {
        return team;
    }

    @Override
    public RegistrationStatus getStatus() {
        return status.get();
    }

    public void setStatus(RegistrationStatus status) {
        if (!this.status.get().equals(status)) {
            this.status.set(status);
        }
    }

    public ObjectProperty<RegistrationStatus> statusProperty() {
        return status;
    }

    @Override
    public List<FXDiscipline> getDisciplines() {
        List<FXDiscipline> disciplines = Lists.newArrayList();
        disciplinesMap.forEach((k, v) -> {
            if (v.get()) {
                disciplines.add(k);
            }
        });
        return Collections.unmodifiableList(disciplines);
    }

    protected void setAllDisciplines(@NotNull Collection<FXDiscipline> disciplines) {
        disciplines.forEach(d -> disciplinesMap.put(d, new SimpleBooleanProperty()));
        disciplinesMap.addListener(new MapChangeListener<FXDiscipline, BooleanProperty>() {
            @Override
            public void onChanged(Change<? extends FXDiscipline, ? extends BooleanProperty> change) {
                if (change.getValueAdded().get()) {
                    disciplines.add(change.getKey());
                } else {
                    disciplines.remove(change.getKey());
                }
//                disciplinesMap.forEach((k, v) -> {
//                    if (disciplines.contains(k) && !v.get()) {
//                        disciplines.remove(k);
//                    } else if (!disciplines.contains(k) && v.get()) {
//                        disciplines.add(k);
//                    }
//                });
                if (disciplinesMap.values().stream().anyMatch(ObservableBooleanValue::get)) {
                    setStatus(RegistrationStatus.OPEN);
                } else {
                    setStatus(RegistrationStatus.REGISTERED);
                }
            }
        });
    }

    @Override
    public boolean addDiscipline(@NotNull FXDiscipline discipline) {
        try {
            if (!disciplinesMap.get(discipline).get()) {
                disciplinesMap.get(discipline).set(true);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Selected discipline not available", e);
        }
    }

    @Override
    public boolean removeDiscipline(@NotNull FXDiscipline discipline) {
        try {
            if (disciplinesMap.get(discipline).get()) {
                disciplinesMap.get(discipline).set(false);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Selected discipline not available", e);
        }
    }

    public ObservableMap<FXDiscipline, BooleanProperty> disciplinesMap() {
        return disciplinesMap;
    }
}
