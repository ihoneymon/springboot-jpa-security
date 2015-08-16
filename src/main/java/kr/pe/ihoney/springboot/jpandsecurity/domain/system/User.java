package kr.pe.ihoney.springboot.jpandsecurity.domain.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
public class User implements UserDetails {
    private static final long serialVersionUID = -8859922673666271276L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 사용자ID
     */
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private List<Authority> authorities;
    
    protected User() {
        this.authorities = new ArrayList<>();
        this.authorities.add(Authority.USER);
    }

    public User(Member member, String username, String password) {
        this();
        Assert.notNull(member, "user.required.member");
        Assert.hasText(username, "user.required.username");
        Assert.hasText(password, "user.required.password");

        this.member = member;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(this.authorities);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((member == null) ? 0 : member.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (member == null) {
            if (other.member != null)
                return false;
        } else if (!member.equals(other.member))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", member=" + member + "]";
    }

}
