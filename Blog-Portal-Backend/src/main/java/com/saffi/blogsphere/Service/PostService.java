package com.saffi.blogsphere.Service;

import java.util.List;

import com.saffi.blogsphere.DTO.InDTO.AddPostInDTO;
import com.saffi.blogsphere.DTO.InDTO.GetPostInDTO;
import com.saffi.blogsphere.DTO.InDTO.MyPostInDTO;
import com.saffi.blogsphere.DTO.InDTO.PostApprovalInDTO;
import com.saffi.blogsphere.DTO.InDTO.UpdatePostInDTO;
import com.saffi.blogsphere.DTO.OutDTO.GetAllMyPostOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.GetAllPostOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.ReportedPostOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.UpdatePostOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.UserPostOutDTO;

/**
 * Post Service interface for managing Post operations.
 */
public interface PostService {
    /**
     * Retrieves all approved posts based on the given criteria in GetPostInDTO.
     * @param getPostInDTO The criteria for retrieving posts.
     * @return A list of approved posts.
     */
    List<GetAllPostOutDTO> getAllApprovePosts(GetPostInDTO getPostInDTO);
    /**
     * Retrieves all UnApproved posts.
     * @return A list of UnApproved posts.
     */
    List<UserPostOutDTO> getAllUnapprovedPost();
    /**
     * Retrieves all reported posts.
     * @return A list of reported posts.
     */
    List<ReportedPostOutDTO> getAllReportedPost();
    /**
     * @param myPostInDTO The criteria for retrieving specific user posts.
     * @return A list of posts created by the user.
     */
    List<GetAllMyPostOutDTO> getAllPostByUserId(MyPostInDTO myPostInDTO);
    /**
     * Retrieves a post by its ID.
     * @param postId The ID of the post.
     * @return The post by postId.
     */
    UpdatePostOutDTO getPostByPostId(String postId);
    /**
     * Adds a new post to the system.
     * @param postInDTO The post DTO instance to be added.
     * @return A string indicating whether the post was added successfully or
     *         not.
     */
    ResponseOutDTO addPost(AddPostInDTO postInDTO);
    /**
     * Updates an existing post.
     * @param postInDTO The updated post DTO instance.
     * @return A string indicating whether the post was updated successfully or
     *         not.
     */
    ResponseOutDTO updatePost(UpdatePostInDTO postInDTO);
    /**
     * Deletes a post by its ID.
     * @param postId The ID of the post to be deleted.
     * @return A string indicating whether the post was deleted successfully or
     *         not.
     */
    ResponseOutDTO deletePost(String postId);
    /**
     * Approves or Reject a post.
     * @param postApprovalInDTO The input data for post approval.
     * @return A string indicating whether the post was approved or disapproved
     *         successfully.
     */
    ResponseOutDTO postApproval(PostApprovalInDTO postApprovalInDTO);
}
