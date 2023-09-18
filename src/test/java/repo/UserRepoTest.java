package repo;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepo;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserRepoTest {
    private UserRepo repo;
    private final User testUser = new User("Test User");
    private final User testUser2 = new User("Test User", 1);

    @BeforeEach
    void init() {
        repo = new UserRepo();
        repo.addUser("Test User", testUser);
        repo.addUser("Test User2", testUser2);
    }

    @Test
    void repo_should_not_be_null() {
        assertNotNull(repo);
    }

    @Test
    void get_existing_User_By_User_Name_test() {
        assertSame(testUser, repo.getUserByUserName("Test User"));
    }

    @Test
    void get_un_existing_User_By_User_Name_test() {
        assertNull(repo.getUserByUserName("Bad Name"));
    }

    @Test
    void add_new_user_test() {
        Map<String, User> users = repo.getUsers();
        int beforeAddingUser = users.size();
        repo.addUser("Some User", new User("Some User"));
        assertEquals(++beforeAddingUser, users.size());
    }

    @Test
    void add_existing_user_test() {
        Map<String, User> users = repo.getUsers();
        int beforeAddingUser = users.size();
        repo.addUser("Test User", testUser);
        assertEquals(beforeAddingUser, users.size());
    }

    @Test
    void check_User_Exists_test() {
        assertTrue(repo.checkUserExists("Test User"));
    }

    @Test
    void check_User_not_Exists_test() {
        assertFalse(repo.checkUserExists("java"));
    }
}
