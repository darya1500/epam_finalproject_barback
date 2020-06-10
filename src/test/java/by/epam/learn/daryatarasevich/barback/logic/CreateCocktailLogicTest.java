package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.CocktailDAO;
import by.epam.learn.daryatarasevich.barback.entity.Cocktail;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateCocktailLogicTest {
    Cocktail cocktail;

    @Mock
    CocktailDAO cocktailDAO;

    @InjectMocks
    CreateCocktailLogic createCocktailLogic;

    @BeforeMethod
    public void setUp() {
        createCocktailLogic = new CreateCocktailLogic();
        MockitoAnnotations.initMocks(this);
        cocktail = new Cocktail();

    }

    @Test
    public void testAddCocktail() {
        Mockito.doNothing().when(cocktailDAO).add(cocktail);
        createCocktailLogic.addCocktail(cocktail);
    }

}