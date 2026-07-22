package com.saffi.blogsphere.Service;

import java.util.List;

import com.saffi.blogsphere.DTO.InDTO.CommentInDTO;
import com.saffi.blogsphere.DTO.OutDTO.CommentReportOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;

/**
 * Comment Service interface managing Comment operations.
 */
public interface CommentService {
    /**
     * Get comments for a specific post by postId.
     * @param postId The postId of the post for which comments are requested.
     * @return A list of CommentOutDTO objects containing comments for the post.
     */
    List<CommentReportOutDTO> getCommentsByPostId(String postId);
    /**
     * Add a new comment to a post.
     * @param commentInDTO The CommentInDTO object containing comment details.
     * @return A message indicating the result of adding the comment.
     */
    ResponseOutDTO addComments(CommentInDTO commentInDTO);
}
