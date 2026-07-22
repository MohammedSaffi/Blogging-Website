package com.saffi.blogsphere.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saffi.blogsphere.DTO.InDTO.LoginInDTO;
import com.saffi.blogsphere.DTO.InDTO.RegisterUserInDTO;
import com.saffi.blogsphere.DTO.OutDTO.LoginUserOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;
import com.saffi.blogsphere.Exception.InvalidCredentialsException;
import com.saffi.blogsphere.Exception.RecordAlreadyExistException;
import com.saffi.blogsphere.Model.User;
import com.saffi.blogsphere.Repository.UserRepository;
import com.saffi.blogsphere.Service.UserService;
import com.saffi.blogsphere.Utilities.ConstantMessages;
import com.saffi.blogsphere.Utilities.DateFormatUtility;

/**
 * Service implementation for managing User operations.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * UserRepository Instance.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * User Instance.
     */
    private User user;

    /**
     * Registers a new user with the provided user details.
     * @param userDTO The input DTO containing user registration details (e.g.,
     *            email, mobile, password).
     * @return A message indicating successful user registration.
     * @throws RecordAlreadyExistException if a user with the same email or mobile
     *             number already exists.
     */
    @Override
    public ResponseOutDTO registerUser(final RegisterUserInDTO userDTO) {
        if (userRepository.existsByEmailIgnoreCaseOrMobile(
                userDTO.getEmail(), userDTO.getMobile())) {
            throw new RecordAlreadyExistException(
                    ConstantMessages.USER_ALREADY_EXIST);
        }
        user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDesignation(userDTO.getDesignation());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setGender(userDTO.getGender());
        user.setRole(userDTO.getRole());
        user.setCreatedAt(DateFormatUtility.newDate());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        ResponseOutDTO responseOutDTO = new ResponseOutDTO();
        responseOutDTO.setMessage(ConstantMessages.REGISTER_USER);
        return responseOutDTO;
    }

    /**
     * Authenticates a user and retrieves user details upon successful login.
     * @param authRequest The input DTO containing user credentials (email and
     *            password).
     * @return A UserOutDTO containing the user's details upon successful login.
     * @throws InvalidCredentialsException if the provided credentials are invalid.
     */
    @Override
    public LoginUserOutDTO loginUser(final LoginInDTO authRequest) {
        user = userRepository.getByEmailIgnoreCaseAndPassword(
                authRequest.getEmail(), authRequest.getPassword());
        if (Objects.isNull(user)) {
            throw new InvalidCredentialsException(
                    ConstantMessages.INVALID_CREDENTIALS);
        }
        LoginUserOutDTO userOutDTO = new LoginUserOutDTO();
        userOutDTO.setUserId(user.getUserId());
        userOutDTO.setFirstName(user.getFirstName());
        userOutDTO.setRole(user.getRole());
        return userOutDTO;
    }
}
