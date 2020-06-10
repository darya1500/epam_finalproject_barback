package by.epam.learn.daryatarasevich.barback.validation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RatingValidatorTest {
    RatingValidator ratingValidator;

    @BeforeMethod
    public void setUp() {
        ratingValidator=new RatingValidator();
    }

    @Test
    public void testValidate_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String star="4";
        String cocktailID="200";
        String authorID="1";
        assert(ratingValidator.validate(star,cocktailID,authorID));
    }
}