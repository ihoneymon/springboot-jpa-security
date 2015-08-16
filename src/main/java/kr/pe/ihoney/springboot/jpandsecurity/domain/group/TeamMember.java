package kr.pe.ihoney.springboot.jpandsecurity.domain.group;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.util.Assert;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

@Entity
public class TeamMember extends AbstractAuditable<User, Long> {

    private static final long serialVersionUID = 1698981585162456322L;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private User member;

    protected TeamMember() {
    }

    public TeamMember(Team team, User member) {
        this();
        Assert.notNull(team, "teamMember.require.team");
        Assert.notNull(member, "teamMember.require.member");

        this.team = team;
        this.member = member;
    }

    public Team getTeam() {
        return this.team;
    }

    public User getMember() {
        return this.member;
    }
}
