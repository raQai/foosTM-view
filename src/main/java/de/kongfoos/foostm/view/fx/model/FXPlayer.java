package de.kongfoos.foostm.view.fx.model;

import de.kongfoos.foostm.model.player.Gender;
import de.kongfoos.foostm.model.player.IPlayer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.Instant;
import java.util.Date;

public class FXPlayer implements IPlayer, Comparable<FXPlayer> {
    private final StringProperty forename = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final StringProperty club = new SimpleStringProperty();
    private final StringProperty itsf = new SimpleStringProperty();
    private final StringProperty dtfb = new SimpleStringProperty();
    private final ObjectProperty<Date> birthDate = new SimpleObjectProperty<>(Date.from(Instant.now()));
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);

    public FXPlayer(String forename, String surname) {
        setForename(forename);
        setSurname(surname);
    }

    @Override
    public String getForename() {
        return forename.get();
    }

    @Override
    public void setForename(String s) {
        forename.set(s);
    }

    public StringProperty forenameProperty() {
        return forename;
    }

    @Override
    public String getSurname() {
        return surname.get();
    }

    @Override
    public void setSurname(String s) {
        surname.set(s);
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    @Override
    public Gender getGender() {
        return gender.get();
    }

    @Override
    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    @Override
    public Date getBirthDate() {
        return birthDate.get();
    }

    @Override
    public void setBirthDate(Date date) {
        birthDate.setValue(date);
    }

    public ObjectProperty<Date> birthDateProperty() {
        return birthDate;
    }

    @Override
    public String getItsf() {
        return itsf.get();
    }

    @Override
    public void setItsf(String s) {
        itsf.set(s);
    }

    public StringProperty itsfProperty() {
        return itsf;
    }

    @Override
    public String getDtfb() {
        return dtfb.get();
    }

    @Override
    public void setDtfb(String s) {
        dtfb.set(s);
    }

    public StringProperty dtfbProperty() {
        return dtfb;
    }

    @Override
    public String getClub() {
        return club.get();
    }

    @Override
    public void setClub(String s) {
        club.set(s);
    }

    public StringProperty clubProperty() {
        return club;
    }

    @Override
    public String getName() {
        return getSurname().toUpperCase() + " " + getForename();
    }

    @Override
    public int compareTo(FXPlayer o) {
        return getName().toLowerCase().compareTo(o.getName().toLowerCase());
    }

    @Override
    public String toString() {
        return getName();
    }
}
