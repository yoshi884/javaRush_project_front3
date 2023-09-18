package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
public class User {

    private String userName;
    private int totalGamesPlayed;

    public User(String userName, int totalGamesPlayed) {
        this.userName = userName;
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public User(String userName) {
        this.userName = userName;
    }
}
