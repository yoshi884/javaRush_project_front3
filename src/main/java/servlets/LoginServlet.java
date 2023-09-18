package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import java.io.IOException;

import static entity.Constants.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Logger logger = LogManager.getLogger(LoginServlet.class);

    UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute(LOGINS_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String userName = req.getParameter(USER_NAME_ATTRIBUTE);
        if (!userService.checkUserExists(userName)) {
            userService.addUser(userName);
            logger.info(NEW_USER_CREATED_MSG + userName);
        }
        logger.info(userName + USER_SIGNED_IN_MSG);
        session.setAttribute(USER_ATTRIBUTE_NAME, userService.getUserByUserName(userName));
        resp.sendRedirect(LOGIC_SERVLET);
    }
}
