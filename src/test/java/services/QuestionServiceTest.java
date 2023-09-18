package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.QuestionsRepo;
import service.QuestionsService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    QuestionsRepo repo = Mockito.mock();
    QuestionsService questionsService;

    @BeforeEach
    void init() {
        questionsService = new QuestionsService(repo);
    }

    @Test
    void not_null_repository_test() {
        assertNotNull(repo);
    }

    @Test
    void required_question_is_null_test() {
        questionsService.getQuestion(null);
        Mockito.verify(repo, times(1)).getStartQuestion();
    }

    @Test
    void required_question_isnt_null() {
        questionsService.getQuestion("1");
        Mockito.verify(repo, times(1)).getQuestionById(1);
    }

}
