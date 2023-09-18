package repository;

import entity.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    @Getter
    private final Map<String, User> users;

    public UserRepo() {
        this.users = new HashMap<>();
    }

    public User getUserByUserName(String userName) {
        return users.get(userName);
    }

    public void addUser(String userName) {
        users.put(userName, new User(userName));
    }

    public void addUser(String userName, User user) {
        users.put(userName, user);
    }

    public boolean checkUserExists(String userName) {
        return users.containsKey(userName);
    }
}
