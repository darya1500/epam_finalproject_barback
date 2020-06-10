package by.epam.learn.daryatarasevich.barback.logic;

import by.epam.learn.daryatarasevich.barback.dao.UserDAO;
import by.epam.learn.daryatarasevich.barback.entity.Status;
import by.epam.learn.daryatarasevich.barback.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountLogicTest {
    User user;

    @Mock
    UserDAO userDAO;

    @InjectMocks
    DeleteAccountLogic deleteAccountLogic;

    @BeforeMethod
    public void setUp() {
        deleteAccountLogic = new DeleteAccountLogic();
        MockitoAnnotations.initMocks(this);
        user = new User(765, "user", "пользователь", "email", "password", "description", Status.USER);
    }

    @Test
    public void testDelete() {
        Mockito.doNothing().when(userDAO).delete(String.valueOf(user.getId()));
        deleteAccountLogic.delete(user);
    }
}