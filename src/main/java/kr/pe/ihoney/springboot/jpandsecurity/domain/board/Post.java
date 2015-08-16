package kr.pe.ihoney.springboot.jpandsecurity.domain.board;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractAuditable;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

@Entity
public class Post extends AbstractAuditable<User, Long> {
    private static final long serialVersionUID = 4363108571233541881L;

    private String title;

    @Lob
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    protected Post() {
        this.comments = new ArrayList<>();
    }

    public Post(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return super.getId();
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Post other = (Post) obj;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post [id=" + super.getId() + ", title=" + title + ", content=" + content + ", comments=" + comments
                + "]";
    }

}
