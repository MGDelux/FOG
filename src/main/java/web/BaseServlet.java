package web;

import api.Fog;
import domain.Employees.Employee;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;
import infrastructure.MailService.MailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * CREATED BY mathias @ 19-11-2020 - 14:22
 **/

public class BaseServlet extends HttpServlet {
    protected static final Fog API;

    static {
        API = initAPI();
    }

    private static Fog initAPI() {
        Database db = new Database();
        return new Fog(new DBEmployee(db),
                new DBUser(db),
                new MailService("O&4#AL+^SF3,"),
                new DBQueries(db),
                new DBMaterials(db));
    }

    protected static Employee getEmployee(HttpServletRequest request, HttpServletResponse response, String errorMsg) throws IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee == null) {
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", errorMsg);
            response.sendRedirect(request.getContextPath() + "/login/");
        }
        return employee;
    }

    public void setUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }


    public void render(String content, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        log(request, "page-loaded");
        request.getRequestDispatcher(content).forward(request, response);
    }

    public void smartrender(String content, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        log(request, "page-loaded");
        request.setAttribute("content", content);
        request.setAttribute("title", "Fog");
        request.getRequestDispatcher("/WEB-INF/base.jsp").forward(request, response);
    }

    protected void log(HttpServletRequest req, String message) {
        System.err.println("[HTTP/SERVER] (" + LocalDateTime.now() + ") " + this.getClass().getCanonicalName() + " \"" + req.getRequestURI() + "\": " + message);
    }
}