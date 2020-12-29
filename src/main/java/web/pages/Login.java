package web.pages;
import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Employee.Exceptions.loginError;
import domain.Employees.Employee;
import web.BaseServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 07-12-2020 - 13:43
 **/
@WebServlet({"/login", "/login/*"})

public class Login extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
           Employee employee = API.login(email,password);
            req.getSession().setAttribute("employee",employee);
            req.getSession().setAttribute("loggedIn",true);
            resp.sendRedirect(req.getContextPath() + "/salesman/");
        } catch (SQLException | EmployeeError | loginError e) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInMSG",e.getMessage());
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/login/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/login.jsp", resp, req);
    }
}
