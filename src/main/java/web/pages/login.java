package web.pages;

import Repoistory.Employee.Exceptions.EmployeeError;
import Repoistory.Employee.Exceptions.loginError;
import domain.Employees.Employee;
import web.BaseServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 07-12-2020 - 13:43
 **/
@WebServlet({"/login", "/login/*"})

public class login extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
           Employee employee = API.login(email,password);
            req.getSession().setAttribute("employee",employee);
            req.getSession().setAttribute("loggedIn",true);
            resp.sendRedirect(req.getContextPath()+"");
        } catch (SQLException | EmployeeError | loginError e) {
            req.setAttribute("loggedInMSG",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/login.jsp", resp, req);
    }
}
