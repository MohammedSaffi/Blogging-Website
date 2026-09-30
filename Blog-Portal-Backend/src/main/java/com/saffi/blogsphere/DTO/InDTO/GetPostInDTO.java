package com.saffi.blogsphere.DTO.InDTO;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

import com.saffi.blogsphere.Utilities.Status;
import com.saffi.blogsphere.Utilities.Technology;

public class GetPostInDTO {
    /**
     * This is user ID field.
     */
    @NotBlank(message = "UserID must not be empty or blank")
    private String userId;
    /**
     * This is Technology field.
     */
    private Technology technology = null;
    /**
     * This is Status field.
     */
    private Status status = null;
    /**
     * This is heading field.
     */
    private String heading = null;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(heading, status, technology);
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
        GetPostInDTO other = (GetPostInDTO) obj;
        return Objects.equals(heading, other.heading)
            && status == other.status && technology == other.technology;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
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
     * ToString Method.
     */
    @Override
    public String toString() {
        return "GetPostInDTO [userId=" + userId + ", technology=" + technology
                + ", heading=" + heading + "]";
    }
}
