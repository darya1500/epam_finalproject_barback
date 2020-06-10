package by.epam.learn.daryatarasevich.barback.validation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginValidatorTest {
    LoginValidator loginValidator;

    @BeforeMethod
    public void setUp() {
        loginValidator=new LoginValidator();
    }

    @Test
    public void testValidate_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String email="email";
        String password="password";
        assert(loginValidator.validate(email,password));
    }
}