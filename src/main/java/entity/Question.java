package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Question {
    private int id;
    private String text;
    private List<Answer> answers;
    @JsonProperty
    private boolean isLost;
    @JsonProperty
    private boolean isWon;
}
