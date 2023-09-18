package repo;

import entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.QuestionsRepo;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRepoTest {
    QuestionsRepo repo;
    Map<Integer, Question> mapQuestion;
    private static final int UNLISTED_QUESTION_ID = 10;
    private static final int FIRST_QUESTION_ID = 1;
    private static final int SECOND_QUESTION_ID = 2;
    private static final int QUESTION_MAP_SIZE = 7;

    @BeforeEach
    void init() throws NoSuchFieldException {
        repo = new QuestionsRepo();
        Field questionMap = QuestionsRepo.class.getDeclaredField("questionRepository");

        questionMap.setAccessible(true);
        try {
            mapQuestion = (Map<Integer, Question>) questionMap.get(repo);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @Test
    void question_map_parsed_successfully_test() {
        assertEquals(QUESTION_MAP_SIZE, mapQuestion.size());
        assertNotNull(mapQuestion);
    }

    @Test
    void get_question_by_id_test() {
        for (int i = 0; mapQuestion.size() < i; i++) {
            Question question = repo.getQuestionById(i);
            assertSame(question, mapQuestion.get(i));
            assertNotNull(question);
        }

    }

    @Test
    void successfully_found_existing_question_test() {

        Question question = repo.getQuestionById(FIRST_QUESTION_ID);
        assertNotNull(question);
        Question question2 = repo.getQuestionById(SECOND_QUESTION_ID);
        assertNotEquals(question, question2);
    }

    @Test
    void unsuccessfully_found_not_existing_question_test() {
        Question question = repo.getQuestionById(UNLISTED_QUESTION_ID);
        assertNull(question);
    }
}
