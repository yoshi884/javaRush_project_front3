package servlets;

import entity.Question;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.QuestionsService;
import service.UserService;

import java.io.IOException;

import static entity.Constants.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestServletTest {

    @Mock
    private HttpServletRequest req;
    private final HttpServletResponse resp = mock();

    private final ServletConfig config = mock();
    private final ServletContext context = mock();
    private final RequestDispatcher dispatcher = mock();
    private final QuestionsService questionsService = mock();
    private final UserService userService = mock();


    private final HttpSession session = mock();
    private QuestServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        when(context.getAttribute(LOGINS_SERVICE_ATTRIBUTE)).thenReturn(userService);
        when(context.getAttribute(QUESTION_SERVICE_ATTRIBUTE)).thenReturn(questionsService);

        when(req.getSession()).thenReturn(session);

        when(config.getServletContext())
                .thenReturn(context);
        servlet = new QuestServlet();
        servlet.init(config);

    }

    @Test
    void question_is_not_game_ending_test() throws ServletException, IOException {
        Question question = mock();
        Mockito.when(req.getParameter(QUESTION_ATTRIBUTE_NAME)).thenReturn("2");
        Mockito.when(questionsService.getQuestion("2")).thenReturn(question);
        Mockito.when(question.isWon()).thenReturn(false);
        Mockito.when(question.isLost()).thenReturn(false);
        Mockito.when(req.getRequestDispatcher(eq(PATH_TO_QUEST))).thenReturn(dispatcher);

        servlet.doPost(req, resp);

        Mockito.verify(session, times(1)).setAttribute(CURRENT_QUESTION_ATTRIBUTE, question);
        Mockito.verify(dispatcher, times(1)).forward(req, resp);
        Mockito.verify(userService, times(0)).updateGameStatistics(any(User.class));
        Mockito.verify(session, times(0)).setAttribute(eq(USER_NAME_ATTRIBUTE), any(User.class));
    }

    @Test
    void question_is_game_won_test() throws ServletException, IOException {
        Question question = mock();
        User user = mock();
        Mockito.when(req.getParameter(QUESTION_ATTRIBUTE_NAME)).thenReturn("2");
        Mockito.when(questionsService.getQuestion("2")).thenReturn(question);
        Mockito.when(user.getUserName()).thenReturn("Test User");
        Mockito.when(userService.getUserByUserName("Test User")).thenReturn(user);
        Mockito.when(question.isWon()).thenReturn(true);
        Mockito.when(question.isLost()).thenReturn(false);
        Mockito.when(req.getRequestDispatcher(eq(PATH_TO_GAME_OVER))).thenReturn(dispatcher);
        Mockito.when(session.getAttribute(USER_ATTRIBUTE_NAME)).thenReturn(user);

        servlet.doPost(req, resp);

        Mockito.verify(session, times(1)).setAttribute(CURRENT_QUESTION_ATTRIBUTE, question);
        Mockito.verify(userService, times(1)).updateGameStatistics(any(User.class));
        Mockito.verify(session, times(1)).setAttribute(eq(USER_ATTRIBUTE_NAME), eq(user));
        Mockito.verify(dispatcher, times(1)).forward(req, resp);
    }
}
