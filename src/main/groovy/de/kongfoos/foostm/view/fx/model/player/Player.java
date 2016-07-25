package de.kongfoos.foostm.view.fx.model.player;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Date;

/**
 * Created by patrick on 17/06/16.
 */
public class Player implements Comparable<Player> {
    private String forename;
    private String surname;
    private Gender gender;
    private Date birthDate;
    private String itsf;
    private String dtfb;
    private String club;

    private Player(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getItsf() {
        return itsf;
    }

    private void setItsf(String itsf) {
        this.itsf = itsf;
    }

    public String getDtfb() {
        return dtfb;
    }

    private void setDtfb(String dtfb) {
        this.dtfb = dtfb;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return surname.toUpperCase() + " " + forename;
    }

    @Override
    public int compareTo(Player o) {
        return this.toString().compareTo(o.toString());
    }

    public static class Builder {
        private final String forename;
        private final String surname;
        private Gender gender = Gender.MALE;
        private Date birthDate;
        private String itsf;
        private String dtfb;
        private String club;

        public Builder(String forename, String surname) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(forename), "Empty forename not allowed");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(surname), "Empty surname not allowed");
            this.forename = forename;
            this.surname = surname;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthDate(Date date) {
            this.birthDate = date;
            return this;
        }

        public Builder itsf(String itsf) {
            this.itsf = itsf;
            return this;
        }

        public Builder dtfb(String dtfb) {
            this.dtfb = dtfb;
            return this;
        }

        public Builder club(String club) {
            this.club = club;
            return this;
        }

        public Player build() {
            final Player player = new Player(forename, surname);
            player.setGender(gender);
            player.setBirthDate(birthDate);
            player.setItsf(itsf);
            player.setDtfb(dtfb);
            player.setClub(club);
            return player;
        }
    }
}
