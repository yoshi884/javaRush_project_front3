package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepo;
import service.UserService;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    private final UserRepo userRepo = mock();
    private UserService service;

    @BeforeEach
    void init() {
        service = new UserService(userRepo);
    }

    @Test
    void not_null_repository_test() {
        assertNotNull(userRepo);
    }

    @Test
    void check_User_Exists_test() {
        service.checkUserExists("Test User");

        Mockito.verify(userRepo, Mockito.times(1)).checkUserExists("Test User");
    }

    @Test
    void add_user_test() {
        service.addUser("Test User");
        Mockito.verify(userRepo, times(1)).addUser("Test User");
    }

    @Test
    void get_user_by_user_name_test() {
        service.getUserByUserName("Test User");
        Mockito.verify(userRepo, times(1)).getUserByUserName("Test User");
    }

    @Test
    @Disabled
    void update_Game_Statistics_test() {
        User[] users = new User[10];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User("Test User" + 1, i);
            service.updateGameStatistics(users[i]);
            assertEquals(++i, users[i].getTotalGamesPlayed());
        }

    }

}
