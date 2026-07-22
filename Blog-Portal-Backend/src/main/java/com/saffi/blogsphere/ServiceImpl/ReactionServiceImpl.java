package com.saffi.blogsphere.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saffi.blogsphere.DTO.InDTO.ReactionInDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;
import com.saffi.blogsphere.Exception.RecordNotFoundException;
import com.saffi.blogsphere.Model.Post;
import com.saffi.blogsphere.Model.Reaction;
import com.saffi.blogsphere.Model.User;
import com.saffi.blogsphere.Repository.PostRepository;
import com.saffi.blogsphere.Repository.ReactionRepository;
import com.saffi.blogsphere.Repository.UserRepository;
import com.saffi.blogsphere.Service.ReactionService;
import com.saffi.blogsphere.Utilities.ConstantMessages;

/**
 * Service implementation for managing reactions on posts.
 */
@Service
public class ReactionServiceImpl implements ReactionService {
    /**
     * ReactionRepository instance.
     */
    @Autowired
    private ReactionRepository reactionRepository;
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
     * Updates/creates/delete the reaction of a user on a post.
     * @param reactionDTO The input DTO containing user ID, post ID, and the
     *            current reaction status.
     * @return A string indicating whether the reaction was updated
     *         successfully.
     * @throws RecordNotFoundException if the user or post is not found.
     */
    @Override
    public ResponseOutDTO updateReaction(final ReactionInDTO reactionDTO) {
        User user = userRepository.findById(reactionDTO.getUserId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.USER_NOT_FOUND));
        Post post = postRepository.findById(reactionDTO.getPostId())
                .orElseThrow(() -> new RecordNotFoundException(
                        ConstantMessages.POST_NOT_FOUND));
        Reaction existingReaction = reactionRepository.getByUserAndPost(user,
                post);
        if (Objects.isNull(existingReaction)) {
            Reaction reaction = new Reaction();
            reaction.setPost(post);
            reaction.setUser(user);
            reaction.setReaction(reactionDTO.isCurrentReaction());
            reactionRepository.save(reaction);
        } else {
            if (Objects.equals(existingReaction.isReaction(),
                    reactionDTO.isCurrentReaction())) {
                reactionRepository.delete(existingReaction);
            } else {
                existingReaction.setReaction(reactionDTO.isCurrentReaction());
                reactionRepository.save(existingReaction);
            }
        }
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.UPDATE_REACTION);
        return responseOutDTO;
    }
}
