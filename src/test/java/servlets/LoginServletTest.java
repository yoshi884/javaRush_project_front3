package servlets;

import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.UserService;

import java.io.IOException;

import static entity.Constants.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServletTest {
    @Mock
    private HttpServletRequest req;
    private final HttpServletResponse resp = mock();

    private final ServletConfig config = mock();
    private final ServletContext context = mock();
    private final UserService userService = mock();

    private final HttpSession session = mock();
    private LoginServlet servlet;



    @BeforeEach
    void setup()  {
        when(context.getAttribute(LOGINS_SERVICE_ATTRIBUTE)).thenReturn(userService);

        when(req.getSession()).thenReturn(session);

        when(config.getServletContext())
                .thenReturn(context);
        servlet = new LoginServlet();
        servlet.init(config);

    }

    @Test
    @Disabled
    void doPost_when_user_is_new_test() throws IOException {
        User user = new User("Test User");
        servlet.doPost(req, resp);

        Mockito.when(req.getParameter(USER_NAME_ATTRIBUTE)).thenReturn("Test User");
        Mockito.when(userService.getUserByUserName("Test User")).thenReturn(user);
        Mockito.when(userService.checkUserExists("Test User")).thenReturn(false);

        servlet.doPost(req, resp);

        Mockito.verify(userService , times(1)).addUser("Test User");
        Mockito.verify(session , times(1)).setAttribute(USER_ATTRIBUTE_NAME, user);
        Mockito.verify(resp , times(1)).sendRedirect(LOGIC_SERVLET);
    }

    @Test
    void doPost_when_user_is_not_new_test() throws IOException {
        User user = new User("Test User");
        servlet.doPost(req, resp);

        Mockito.when(req.getParameter(USER_NAME_ATTRIBUTE)).thenReturn("Test User");
        Mockito.when(userService.getUserByUserName("Test User")).thenReturn(user);
        Mockito.when(userService.checkUserExists("Test User")).thenReturn(true);


        servlet.doPost(req, resp);

        Mockito.verify(userService , times(0)).addUser("Test User");
        Mockito.verify(session , times(1)).setAttribute(USER_ATTRIBUTE_NAME, user);
        Mockito.verify(resp , times(1)).sendRedirect(LOGIC_SERVLET);
    }
}
