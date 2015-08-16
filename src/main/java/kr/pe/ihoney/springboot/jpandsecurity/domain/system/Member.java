package kr.pe.ihoney.springboot.jpandsecurity.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String nickName;

    @OneToOne(mappedBy = "member")
    @JoinColumn(name = "USER_ID")
    private User user;

    protected Member() {}
    
    public Member(String name, String email, String nickName) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
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
        Member other = (Member) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + ", email=" + email + ", nickName=" + nickName + "]";
    }
}
