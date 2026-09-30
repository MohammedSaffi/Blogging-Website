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
@Table(name = "reports", indexes = {
    @Index(name = "idx_reports_post", columnList = "post_id") },
    uniqueConstraints = @UniqueConstraint(name = "uk_reports_user_post",
        columnNames = { "user_id", "post_id" }))
public class Report {
    /**
     * This is Report ID Field.
     */
    @Id
    @GeneratedValue
    @UuidGenerator
        @Column(name = "report_id", nullable = false, updatable = false)
    private String reportId;
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
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(post, reportId, user);
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
        Report other = (Report) obj;
        return Objects.equals(post, other.post)
                && Objects.equals(reportId, other.reportId)
                && Objects.equals(user, other.user);
    }

    /**
     * @return the reportId
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(final String reportId) {
        this.reportId = reportId;
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "Report [reportId=" + reportId + ", user=" + user + ", post="
                + post + "]";
    }
}
