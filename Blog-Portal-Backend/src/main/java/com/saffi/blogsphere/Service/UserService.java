package com.saffi.blogsphere.Service;

import com.saffi.blogsphere.DTO.InDTO.LoginInDTO;
import com.saffi.blogsphere.DTO.InDTO.RegisterUserInDTO;
import com.saffi.blogsphere.DTO.OutDTO.LoginUserOutDTO;
import com.saffi.blogsphere.DTO.OutDTO.ResponseOutDTO;

/**
 * Service interface for managing User operations.
 */
public interface UserService {
    /**
     * Registers a new user.
     * @param userDTO The user DTO instance to be registered.
     * @return A string indicating whether the user was registered successfully
     *         or not.
     */
    ResponseOutDTO registerUser(RegisterUserInDTO userDTO);
    /**
     * LogIn a user with the provided credentials.
     * @param authRequest The login credentials DTO instance.
     * @return The user DTO representing the details of loggedIn user.
     */
    LoginUserOutDTO loginUser(LoginInDTO authRequest);
}
