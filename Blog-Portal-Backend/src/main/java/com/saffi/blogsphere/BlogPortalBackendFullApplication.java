package com.saffi.blogsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BlogPortalBackendFullApplication {
    protected BlogPortalBackendFullApplication() {
    }

    /**
     * Main method to start the Spring Boot application.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(BlogPortalBackendFullApplication.class, args);
    }
}
