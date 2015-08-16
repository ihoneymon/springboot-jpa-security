package kr.pe.ihoney.springboot.jpandsecurity.domain.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.util.Assert;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

@Entity
public class Project extends AbstractAuditable<User, Long> {
    private static final long serialVersionUID = -1716871646546927805L;

    private String name;

    @OneToMany(mappedBy = "project")
    private List<Team> teams;

    protected Project() {
        this.teams = new ArrayList<>();
    }

    public Project(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public Project setName(String name) {
        Assert.hasText(name, "project.required.name");
        this.name = name;
        return this;
    }

    public List<Team> getTeams() {
        return Collections.unmodifiableList(this.teams);
    }

    public Project addTeam(String name) {
        Optional<Team> match = teams.stream().filter(team -> team.getName().equals(name)).findFirst();

        if (null != match) {
            throw new IllegalArgumentException("Same team name exist.");
        }
        this.teams.add(new Team(this, name));
        return this;
    }

    public Project removeTeam(Team team) {
        if (this.teams.contains(team)) {
            this.teams.remove(team);
        }
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((teams == null) ? 0 : teams.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (teams == null) {
            if (other.teams != null)
                return false;
        } else if (!teams.equals(other.teams))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [id=" + getId() + ",name=" + name + "]";
    }

}
