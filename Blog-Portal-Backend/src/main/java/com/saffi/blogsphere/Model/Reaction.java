package com.saffi.blogsphere.Model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "reactions", indexes = {
    @Index(name = "idx_reactions_post", columnList = "post_id") },
    uniqueConstraints = @UniqueConstraint(name = "uk_reactions_user_post",
        columnNames = { "user_id", "post_id" }))
public class Reaction {
    /**
     * This is Reaction ID Field.
     */
    @Id
    @GeneratedValue
    @UuidGenerator
        @Column(name = "reaction_id", nullable = false, updatable = false)
    private String reactionId;
    /**
     * This is User Reference Field.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * This is Post Reference Field.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    /**
     * This is Reaction Field.
     */
    private boolean reaction;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(post, reaction, reactionId, user);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Reaction other = (Reaction) obj;
        return Objects.equals(post, other.post) && reaction == other.reaction
                && Objects.equals(reactionId, other.reactionId)
                && Objects.equals(user, other.user);
    }

    /**
     * @return the reactionId
     */
    public String getReactionId() {
        return reactionId;
    }

    /**
     * @param reactionId the reactionId to set
     */
    public void setReactionId(final String reactionId) {
        this.reactionId = reactionId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * @return the post
     */
    public Post getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(final Post post) {
        this.post = post;
    }

    /**
     * @return the reaction
     */
    public boolean isReaction() {
        return reaction;
    }

    /**
     * @param reaction the reaction to set
     */
    public void setReaction(final boolean reaction) {
        this.reaction = reaction;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Reaction [reactionId=" + reactionId + ", user=" + user
                + ", post=" + post + ", reaction=" + reaction + "]";
    }
}
