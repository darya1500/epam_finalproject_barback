package by.epam.learn.daryatarasevich.barback.validation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HelpValidatorTest {
    HelpValidator helpValidator;

    @BeforeMethod
    public void setUp() {
        helpValidator=new HelpValidator();
    }

    @Test
    public void testValidate_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String email="email";
        String name="name";
        String message="message";
        assert(helpValidator.validate(email,name,message));
    }
}