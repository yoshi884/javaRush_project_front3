package servlets;

import entity.Question;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.QuestionsService;
import service.UserService;


import java.io.IOException;

import static entity.Constants.*;

@WebServlet("/logic")
public class QuestServlet extends HttpServlet {

    Logger logger = LogManager.getLogger(QuestServlet.class);
    private UserService userService;
    private QuestionsService questionsService;

    @Override
    public void init(ServletConfig config)  {
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute(LOGINS_SERVICE_ATTRIBUTE);
        questionsService = (QuestionsService) servletContext.getAttribute(QUESTION_SERVICE_ATTRIBUTE);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // К сожалению , в Java нету GOTO
        doPost(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String requiredQuestion = req.getParameter(QUESTION_ATTRIBUTE_NAME);

        Question currentQuestion = questionsService.getQuestion(requiredQuestion);
        session.setAttribute(CURRENT_QUESTION_ATTRIBUTE, currentQuestion);

        if (currentQuestion.isLost() || currentQuestion.isWon()) {
            User currentUser = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
            userService.updateGameStatistics(currentUser);
            session.setAttribute(USER_ATTRIBUTE_NAME, userService.getUserByUserName(currentUser.getUserName()));
            req.getRequestDispatcher(PATH_TO_GAME_OVER).forward(req, resp);

            return;
        }
        req.getRequestDispatcher(PATH_TO_QUEST).forward(req, resp);
    }
}
