package by.epam.learn.daryatarasevich.barback.validation;

import by.epam.learn.daryatarasevich.barback.exception.InvalidEmailException;
import by.epam.learn.daryatarasevich.barback.exception.InvalidPasswordException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegisterValidatorTest {
    RegisterValidator registerValidator;

    @BeforeMethod
    public void setUp() {
        registerValidator=new RegisterValidator();
    }

    @Test
    public void testValidate_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String email="email";
        String name="name";
        String password="password";
        assert(registerValidator.validate(email,password,name));
    }

    @Test
    public void testValidateEmail_WhenDataIsCorrect_ShouldReturnCorrectResult() throws InvalidEmailException {
        String email="email@gmail.com";
        assert(registerValidator.validateEmail(email));
    }

    @Test
    public void testValidatePassword_WhenDataIsCorrect_ShouldReturnCorrectResult() throws InvalidPasswordException {
        String password="password";
        assert(registerValidator.validatePassword(password));
    }
}