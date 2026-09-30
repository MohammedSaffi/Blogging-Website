package com.saffi.blogsphere.DTO.InDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.saffi.blogsphere.Utilities.Designation;
import com.saffi.blogsphere.Utilities.Gender;
import com.saffi.blogsphere.Utilities.Role;

public class RegisterUserInDTOTest {
    private static ValidatorFactory validatorFactory;
    private Validator validator;
    private RegisterUserInDTO user1;
    private RegisterUserInDTO user2;
    private RegisterUserInDTO user3;

    @BeforeAll
    public static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    public static void closeValidator() {
        validatorFactory.close();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = validatorFactory.getValidator();
        user1 = new RegisterUserInDTO();
        user1.setFirstName("XXXXX");
        user1.setLastName("YYYYY");
        user1.setEmail("xyz@nucleusteq.com");
        user1.setPassword("Abc@12345");
        user1.setMobile("9000000000");
        user1.setDesignation(Designation.INTERN);
        user1.setGender(Gender.MALE);
        user1.setRole(Role.EMPLOYEE);
        user2 = new RegisterUserInDTO();
        user2.setFirstName("XXXXX");
        user2.setLastName("YYYYY");
        user2.setEmail("xyz@nucleusteq.com");
        user2.setPassword("Abc@12345");
        user2.setMobile("9000000000");
        user2.setDesignation(Designation.INTERN);
        user2.setGender(Gender.MALE);
        user2.setRole(Role.EMPLOYEE);
    }

    @Test
    public void testGetterSetter() {
        assertEquals("XXXXX", user1.getFirstName());
        assertEquals("YYYYY", user1.getLastName());
        assertEquals("xyz@nucleusteq.com", user1.getEmail());
        assertEquals("Abc@12345", user1.getPassword());
        assertEquals("9000000000", user1.getMobile());
        assertEquals(Designation.INTERN, user1.getDesignation());
        assertEquals(Gender.MALE, user1.getGender());
        assertEquals(Role.EMPLOYEE, user1.getRole());
    }

    @Test
    public void testToString() {
        String expected = "RegisterUserInDTO [firstName=XXXXX, lastName=YYYYY, email=xyz@nucleusteq.com, password=Abc@12345, mobile=9000000000, role=EMPLOYEE, gender=MALE, designation=INTERN]";
        assertEquals(expected, user1.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        assertEquals(user1, user1);
        assertEquals(user1.hashCode(), user1.hashCode());
        assertNotEquals(user1, new Object());
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        user2.setFirstName("ZZZZZ");
        user2.setLastName("WWWWW");
        user2.setEmail("abc@nucleusteq.com");
        user2.setPassword("Xyz@98765");
        user2.setMobile("9999999999");
        user2.setDesignation(Designation.SENIOR_ARCHITECT);
        user2.setGender(Gender.FEMALE);
        user2.setRole(Role.ADMIN);
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
        user3 = new RegisterUserInDTO();
        assertFalse(user3.equals(null));
    }

    @Test
    public void testEmailValidationAllowsNormalValidEmails() {
        user1.setEmail("saffi@gmail.com");
        assertTrue(validator.validate(user1).isEmpty());

        user1.setEmail("user@yahoo.com");
        assertTrue(validator.validate(user1).isEmpty());
    }

    @Test
    public void testEmailValidationRejectsInvalidEmails() {
        assertEmailValidationFails("invalid-email");
        assertEmailValidationFails("user@");
        assertEmailValidationFails("@gmail.com");
    }

    private void assertEmailValidationFails(final String email) {
        user1.setEmail(email);
        Set<ConstraintViolation<RegisterUserInDTO>> violations =
                validator.validate(user1);
        long emailViolations = violations.stream()
                .filter(violation -> "email".equals(
                        violation.getPropertyPath().toString()))
                .count();

        assertEquals(1, emailViolations);
    }
}
