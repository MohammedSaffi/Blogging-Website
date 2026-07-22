package com.saffi.blogsphere.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saffi.blogsphere.DTO.InDTO.ReportInDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;
import com.saffi.blogsphere.Exception.InvalidRecordException;
import com.saffi.blogsphere.Exception.RecordNotFoundException;
import com.saffi.blogsphere.Model.Post;
import com.saffi.blogsphere.Model.Report;
import com.saffi.blogsphere.Model.User;
import com.saffi.blogsphere.Repository.PostRepository;
import com.saffi.blogsphere.Repository.ReportRepository;
import com.saffi.blogsphere.Repository.UserRepository;
import com.saffi.blogsphere.Service.ReportService;
import com.saffi.blogsphere.Utilities.ConstantMessages;

/**
 * Service implementation for managing reports on posts.
 */
@Service
public class ReportServiceImpl implements ReportService {
    /**
     * ReportRepository instance.
     */
    @Autowired
    private ReportRepository reportRepository;
    /**
     * UserRepository instance.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * PostRepository instance.
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Updates/creates/delete a report on a post by a user.
     * @param reportInDTO The input DTO containing user ID, post ID, and the
     *            report message.
     * @return A string indicating whether the report was updated or created
     *         successfully.
     * @throws RecordNotFoundException if the user or post is not found.
     */
    @Override
    public ResponseOutDTO updateReport(final ReportInDTO reportInDTO) {
        User user = userRepository.findById(reportInDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = postRepository.findById(reportInDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        Report existingReport = reportRepository.getByUserAndPost(user, post);
        if (Objects.isNull(existingReport)) {
            Report report = new Report();
            report.setPost(post);
            report.setUser(user);
            reportRepository.save(report);
        } else {
            reportRepository.delete(existingReport);
        }
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REPORT);
        return responseOutDTO;
    }

    /**
     * delete post with post ID.
     */
    @Override
    public ResponseOutDTO deleteReport(final String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        if (reportRepository.existsByPost(post)) {
            reportRepository.deleteByPost(post);
            ResponseOutDTO responseOutDTO = new ResponseOutDTO();
            responseOutDTO.setMessage(ConstantMessages.DELETE_REPORT);
            return responseOutDTO;
        } else {
            throw new InvalidRecordException(ConstantMessages.REPORT_NOT_EXIT);
        }
    }
}
