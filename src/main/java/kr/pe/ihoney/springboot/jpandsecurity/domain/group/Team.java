package kr.pe.ihoney.springboot.jpandsecurity.domain.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.util.Assert;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

@Entity
public class Team extends AbstractAuditable<User, Long> {
    private static final long serialVersionUID = -5361116245669081863L;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    private String name;

    @OneToMany
    private List<TeamMember> members;

    protected Team() {
        this.members = new ArrayList<>();
    }

    public Team(Project project, String name) {
        this();
        this.project = project;
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        Assert.hasText(name, "Team.require.name");
        this.name = name;
    }

    public List<TeamMember> getMembers() {
        return Collections.unmodifiableList(this.members);
    }

    public Team addMember(User member) {
        if (!hasMember(member)) {
            this.members.add(new TeamMember(this, member));
        }
        return this;
    }

    public Team removeMember(User member) {
        if (hasMember(member)) {
            this.members.remove(member);
        }
        return this;
    }

    private boolean hasMember(User member) {
        Optional<TeamMember> match = members.stream()
                .filter(teamMember -> teamMember.getMember().equals(member))
                .findAny();
        
        return match.isPresent();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((members == null) ? 0 : members.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Team other = (Team) obj;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Team [id=" + getId() + ",name=" + name + ", members=" + members + "]";
    }

}
