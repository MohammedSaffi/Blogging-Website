package com.saffi.blogsphere.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saffi.blogsphere.Model.Comment;
import com.saffi.blogsphere.Model.Post;

import java.util.List;

/**
 * Repository for managing comments.
 */
public interface CommentRepository extends JpaRepository<Comment, String> {
    /**
     * Find comments by the associated post.
     * @param post The post for which comments should be retrieved.
     * @return A list of comments associated with the specified post.
     */
    List<Comment> findByPost(Post post);
    /**
     * Delete comments associated with a specific post.
     * @param post The post for which comments should be deleted.
     */
    void deleteByPost(Post post);
    /**
     * Count the number of comments for a specific post.
     * @param post The post for which the comment count is needed.
     * @return The number of comments for the specified post.
     */
    int countByPost(Post post);
}
