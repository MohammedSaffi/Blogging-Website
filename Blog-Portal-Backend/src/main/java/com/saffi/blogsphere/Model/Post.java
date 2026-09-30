package com.saffi.blogsphere.Model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.UuidGenerator;

import com.saffi.blogsphere.Utilities.Status;
import com.saffi.blogsphere.Utilities.Technology;

@Entity
@Table(name = "posts", indexes = {
    @Index(name = "idx_posts_user", columnList = "user_id"),
    @Index(name = "idx_posts_status_updated", columnList = "status, updated_at") })
public class Post {
    /**
     * This is post ID.
     */
    @Id
    @GeneratedValue
    @UuidGenerator
        @Column(name = "post_id", nullable = false, updatable = false)
    private String postId;
    /**
     * This is User Reference.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * This is Technology field.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Technology technology;
    /**
     * This is heading field.
     */
    private String heading;
    /**
     * This is paragraph field.
     */
    @Column(columnDefinition = "TEXT")
    private String paragraph;
    /**
     * This is created date field.
     */
    @Column(name = "created_at")
    private String createdAt;
    /**
     * This is updated date field.
     */
    @Column(name = "updated_at")
    private String updatedAt;
    /**
     * This is Status field.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(createdAt, heading, paragraph, postId, status,
                technology, updatedAt, user);
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
        Post other = (Post) obj;
        return Objects.equals(createdAt, other.createdAt)
                && Objects.equals(heading, other.heading)
                && Objects.equals(paragraph, other.paragraph)
                && Objects.equals(postId, other.postId)
                && status == other.status && technology == other.technology
                && Objects.equals(updatedAt, other.updatedAt)
                && Objects.equals(user, other.user);
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Post [postId=" + postId + ", user=" + user + ", technology="
                + technology + ", heading=" + heading + ", paragraph="
                + paragraph + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", status=" + status + "]";
    }

    /**
     * @return the postId
     */
    public String getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(final String postId) {
        this.postId = postId;
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
     * @return the technology
     */
    public Technology getTechnology() {
        return technology;
    }

    /**
     * @param technology the technology to set
     */
    public void setTechnology(final Technology technology) {
        this.technology = technology;
    }

    /**
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(final String heading) {
        this.heading = heading;
    }

    /**
     * @return the paragraph
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * @param paragraph the paragraph to set
     */
    public void setParagraph(final String paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final Status status) {
        this.status = status;
    }
}
