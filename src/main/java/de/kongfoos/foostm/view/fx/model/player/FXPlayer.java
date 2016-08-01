package de.kongfoos.foostm.view.fx.model.player;

import java.util.Calendar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;

import de.kongfoos.foostm.model.player.Gender;
import de.kongfoos.foostm.model.player.Player;

public class FXPlayer extends Player implements Comparable<FXPlayer> {
    private final StringProperty forename = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final StringProperty club = new SimpleStringProperty();
    private final StringProperty itsf = new SimpleStringProperty();
    private final StringProperty dtfb = new SimpleStringProperty();
    private final ObjectProperty<Calendar> birthDate = new SimpleObjectProperty<>(Calendar.getInstance());
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);

    FXPlayer() {
    }

    @Override
    public String getForename() {
        return forename.get();
    }

    @Override
    public void setForename(@NotNull String s) {
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
    public void setSurname(@NotNull String s) {
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
    public void setGender(@NotNull Gender gender) {
        this.gender.set(gender);
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    @Override
    public Calendar getBirthDate() {
        return birthDate.get();
    }

    @Override
    public void setBirthDate(Calendar date) {
        birthDate.setValue(date);
    }

    public ObjectProperty<Calendar> birthDateProperty() {
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
    public int compareTo(FXPlayer o) {
        return getName().toLowerCase().compareTo(o.getName().toLowerCase());
    }

}
