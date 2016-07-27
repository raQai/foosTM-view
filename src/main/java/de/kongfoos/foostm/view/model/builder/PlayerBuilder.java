package de.kongfoos.foostm.view.model.builder;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import de.kongfoos.foostm.model.player.Gender;
import de.kongfoos.foostm.model.player.PlayerImpl;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

public abstract class PlayerBuilder<T extends PlayerImpl> {

    private String forename;
    private String surname;
    private String club = "";
    private String itsf = "";
    private String dtfb = "";
    private Date birthDate = Date.from(Instant.now());
    private Gender gender = Gender.MALE;

    protected PlayerBuilder(String forename, String surname) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(forename), "empty forename is not allowed");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(surname), "empty surname is not allowed");
        this.forename = forename;
        this.surname = surname;
    }

    public PlayerBuilder<T> club(@NotNull String club) {
        this.club = club;
        return this;
    }

    public PlayerBuilder<T> itsf(@NotNull String itsf) {
        this.itsf = itsf;
        return this;
    }

    public PlayerBuilder<T> dtfb(@NotNull String dtfb) {
        this.dtfb = dtfb;
        return this;
    }

    public PlayerBuilder<T> birthDate(@NotNull Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PlayerBuilder<T> gender(@NotNull Gender gender) {
        this.gender = gender;
        return this;
    }

    protected abstract T getInstance();

    public T build() {
        final T instance = getInstance();
        instance.setForename(forename);
        instance.setSurname(surname);
        instance.setClub(club);
        instance.setItsf(itsf);
        instance.setDtfb(dtfb);
        instance.setBirthDate(birthDate);
        instance.setGender(gender);

        return instance;
    }
}
