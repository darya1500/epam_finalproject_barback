package by.epam.learn.daryatarasevich.barback.validation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommonValidatorTest {
    CommonValidator commonValidator;
    boolean expected;
    boolean actual;

    @BeforeMethod
    public void setUp() {
        commonValidator = new CommonValidator();
        expected = true;
    }

    @Test
    public void testValidateID_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String id = "226";
        actual = commonValidator.validateID(id);
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateCocktail_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String name = "Cocktail";
        actual = commonValidator.validateCocktail(name);
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateUser_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String name = "User";
        actual = commonValidator.validateUser(name);
        assertEquals(actual, expected);
    }

    @Test
    public void testValidateIngredient_WhenDataIsCorrect_ShouldReturnCorrectResult() {
        String name = "Ingredient";
        actual = commonValidator.validateIngredient(name);
        assertEquals(actual, expected);
    }
}