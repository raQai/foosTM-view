package de.kongfoos.foostm.view.fx.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by patrick on 23/06/16.
 */
public enum RegistrationStatus {
    COMPLETE("Complete"), OPEN("Open"), PREREGISTERED("Preregistered"), REGISTERED("Registered");

    private final String status;

    RegistrationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
