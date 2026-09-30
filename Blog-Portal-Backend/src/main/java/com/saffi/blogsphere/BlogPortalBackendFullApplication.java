package com.saffi.blogsphere;

import java.util.TimeZone;

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
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(BlogPortalBackendFullApplication.class, args);
    }
}
