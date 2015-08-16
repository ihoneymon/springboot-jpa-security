package kr.pe.ihoney.springboot.jpandsecurity.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractAuditable;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

@Entity
public class Comment extends AbstractAuditable<User, Long> {
    private static final long serialVersionUID = 4392671688059740159L;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Lob
    private String content;

    @ManyToOne(optional = true)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    protected Comment() {
        this.comments = new ArrayList<>();
    }

    public Comment(Comment parent, Post post, String content) {
        this();
        this.parent = parent;
        this.post = post;
        this.content = content;
    }

    public Post getPost() {
        return this.post;
    }

    public String getContent() {
        return this.content;
    }

    public Comment getParent() {
        return this.parent;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(this.comments);
    }

    public Comment addComment(String content) {
        this.comments.add(new Comment(this, this.post, content));
        return this;
    }

    public Comment remove(Comment comment) {
        if (this.comments.contains(comment)) {
            this.comments.remove(comment);
        }
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((post == null) ? 0 : post.hashCode());
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
        Comment other = (Comment) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (post == null) {
            if (other.post != null)
                return false;
        } else if (!post.equals(other.post))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [id=" + getId() + ",content=" + content + ", comments=" + comments + "]";
    }

}
