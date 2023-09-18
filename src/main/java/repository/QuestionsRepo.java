package repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static entity.Constants.*;

public class QuestionsRepo {

    private static final int START_QUESTION = 1;
    private final Map<Integer, Question> questionRepository;

    public QuestionsRepo() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (InputStream inputStream = getClass().getResourceAsStream(PATH_TO_QUESTIONS_AND_ANSWERS);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            Question[] questions = mapper.readValue(contents, Question[].class);
            /* System.out.println(questions); для дебага*/

            var mapQuestion = new HashMap<Integer, Question>();
            mapQuestion = (HashMap<Integer, Question>) Arrays.stream(questions).collect(Collectors.toMap(Question::getId, Function.identity()));

            questionRepository = Collections.unmodifiableMap(mapQuestion);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Question getQuestionById(int current) {
        return questionRepository.get(current);
    }

    public Question getStartQuestion() {
        return getQuestionById(START_QUESTION);
    }
}
