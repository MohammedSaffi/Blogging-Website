package com.saffi.blogsphere.Initializers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.saffi.blogsphere.Controller.UserController;
import com.saffi.blogsphere.Model.User;
import com.saffi.blogsphere.Repository.UserRepository;
import com.saffi.blogsphere.Utilities.DateFormatUtility;
import com.saffi.blogsphere.Utilities.Designation;
import com.saffi.blogsphere.Utilities.Gender;
import com.saffi.blogsphere.Utilities.PasswordEncryption;
import com.saffi.blogsphere.Utilities.Role;

/**
 * This component initializes the admin user in the MongoDB database.
 */
@Component
public class AdminUserInitializer implements CommandLineRunner {
    /**
     * Logger for track log reports.
     */
    private Logger logger = LogManager.getLogger(UserController.class);
    /**
     * User Repository Object.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Email address of the admin user. This value is injected from the.
     * environment with a default value of {@code admin@nucleusteq.com}.
     */
    @Value("${ADMIN_EMAIL:admin@blogsphere.com}")
    private String email;
    /**
     * Password of the admin user. This value is injected from the environment.
     * with a default value of {@code Admin@12345}.
     */
    @Value("${ADMIN_PASSWORD:Admin@12345}")
    private String password;
    /**
     * First Name of the admin user. This value is injected from the.
     * environment with a default value of {@code Admin}.
     */
    @Value("${ADMIN_FIRST_NAME:Admin}")
    private String firstName;
    /**
     * Last Name of the admin user. This value is injected from the environment.
     * with a default value of {@code Admin}.
     */
    @Value("${ADMIN_LAST_NAME:Admin}")
    private String lastName;

    /**
     * Create a new admin user if not exit when application stated.
     */
    @Override
    public void run(final String... args) throws Exception {
        logger.info("Request for create admin user with email: {}", email);
        try {
            if (!userRepository.findByEmail(email).isPresent()) {
                User adminUser = new User();
                adminUser.setFirstName(firstName);
                adminUser.setLastName(lastName);
                adminUser.setEmail(email);
                adminUser.setPassword(
                        PasswordEncryption.getEncryptedPassword(password));
                adminUser.setMobile("9407192414");
                adminUser.setDesignation(Designation.OTHER);
                adminUser.setGender(Gender.MALE);
                adminUser.setRole(Role.ADMIN);
                adminUser.setCreatedAt(DateFormatUtility.newDate());
                userRepository.save(adminUser);
                logger.info("admin user created");
            } else {
                logger.error("Admin user already exists");
            }
        } catch (Exception e) {
            logger.error("error while creating new admin user",e);
        }
    }
}
