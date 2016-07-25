package de.kongfoos.foostm.view.fx.model.discipline;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import de.kongfoos.foostm.view.fx.model.Team;
import de.kongfoos.foostm.view.fx.model.Type;

public class Discipline {
    private final List<Predicate<Team>> participationRules = Lists.newArrayList();
    private final List<Team> teams = Lists.newArrayList();
    private String name;
    private String shortName;
    private Type type;

    private Discipline(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    private void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Predicate<Team>> getParticipationRules() {
        return participationRules;
    }

    private void setParticipationRules(Collection<Predicate<Team>> rules) {
        this.participationRules.addAll(rules);
    }

    private void addParticipationRule(Predicate<Team> rule) {
        participationRules.add(rule);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Team> getTeams(Predicate<Team> filter) {
        return teams.stream().filter(filter).collect(Collectors.toList());
    }

    public boolean addTeam(Team team) {
        if (team != null && team.isAllowedToParticipate(this)) {
            teams.add(team);
            return true;
        }
        return false;
    }

    public Type getType() {
        return type;
    }

    public boolean isSingles() {
        return this.type.equals(Type.SINGLES);
    }
    public boolean isDoubles() {
        return this.type.equals(Type.DOUBLES);
    }
    public boolean isTeam() {
        return this.type.equals(Type.TEAM);
    }

    private void setType(Type type) {
        this.type = type;
    }

    public static class Builder {
        private final String name;
        private final String shortName;
        private final Type type;
        private final List<Predicate<Team>> participationRules = Lists.newArrayList();

        public Builder(String name, String shortName, Type type) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "empty name is not allowed");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(shortName), "empty short name is not allowed");
            Preconditions.checkNotNull(type, "null type discipline not allowed");
            this.name = name;
            this.shortName = shortName;
            this.type = type;
        }

        public Builder addRule(Predicate<Team> rule) {
            Preconditions.checkNotNull(rule, "Null participation rule not allowed");
            this.participationRules.add(rule);
            return this;
        }

        public Discipline build() {
            final Discipline discipline = new Discipline(name);
            discipline.setShortName(shortName);
            discipline.setType(this.type);
            discipline.addParticipationRule(t -> t.getType().equals(this.type));
            discipline.setParticipationRules(participationRules);
            return discipline;
        }
    }
}
