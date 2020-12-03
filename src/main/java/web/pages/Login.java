package web.pages;

import Repository.User.LoginError;
import domain.User.LoginFacade;
import domain.User.User;
import domain.Users.User;
import web.BaseServlet;
import web.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/login", "/login/*"})
public class Login extends BaseServlet {
    private final LoginFacade loginFacade = new LoginFacade();

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)
            throws ServletException, IOException {
        setUp(req,resp);
        log(req,"Login page");
        render("login page","/WEB-INF/pages/login.jsp",req,resp);
    }
    @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws IOException, ServletException {
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      try {
        User user = loginFacade.login(email, password);
          req.getSession().setAttribute("user",user);
          req.getSession().setAttribute("role",user.getRole());
          req.getSession().setAttribute("loggedIn",true);
          resp.sendRedirect(req.getContextPath()+"");

          return;
      } catch (LoginError | SQLException loginError) {
          req.setAttribute("error", loginError.getMessage());
          System.out.println("error "+ loginError.getMessage());
          doGet(req,resp);
          return;
      }
  }
}
