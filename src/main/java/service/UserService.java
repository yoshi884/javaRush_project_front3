package service;


import entity.User;
import repository.UserRepo;


public class UserService {
    final private UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean checkUserExists(String userName) {
        return userRepo.checkUserExists(userName);
    }

    public void addUser(String userName) {
        userRepo.addUser(userName);
    }

    public User getUserByUserName(String userName) {
        return userRepo.getUserByUserName(userName);
    }

    public void updateGameStatistics(User currentUser) {
        String userName = currentUser.getUserName();
        int currentGamesPlayed = userRepo.getUserByUserName(userName).getTotalGamesPlayed();
        userRepo.addUser(userName, new User(userName, ++currentGamesPlayed));
    }
}
