package com.saffi.blogsphere.Service;

import com.saffi.blogsphere.DTO.InDTO.ReportInDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;

/**
 * Report Service interface for managing Report operations.
 */
public interface ReportService {
    /**
     * Add/Updates/Delete a report on a post.
     * @param report The report DTO instance to be updated.
     * @return A string indicating whether the report was updated successfully
     *         or not.
     */
    ResponseOutDTO updateReport(ReportInDTO report);
    /**
     * @param postId of the post
     * @return delete post with post ID
     */
    ResponseOutDTO deleteReport(String postId);
}
