package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.QuestionsRepo;
import repository.UserRepo;
import service.QuestionsService;
import service.UserService;

import static entity.Constants.*;


@WebListener
public class AppContextListener implements ServletContextListener {

     Logger logger = LogManager.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        QuestionsService questionsService = new QuestionsService(new QuestionsRepo());

        UserService userService = new UserService(new UserRepo());
        logger.info(AppContextListener.class + " succeed");
        ctx.setAttribute(QUESTION_SERVICE_ATTRIBUTE, questionsService);
        ctx.setAttribute(LOGINS_SERVICE_ATTRIBUTE, userService);

    }
}
