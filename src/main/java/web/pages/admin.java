package web.pages;

import domain.Employees.Employee;
import domain.Queries.Queries;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CREATED BY Emil @ 26-11-2020 - 11:29
 **/
@WebServlet({"/admin", "/admin/*"})
public class admin extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {
            ArrayList<Queries> queries = new ArrayList<>();
            setUp(req, resp);
            try {
                for (Queries q : API.getAllQueries()) {
                    queries.add(q);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            req.setAttribute("Queries", queries);
            System.out.println(queries);
            render("/WEB-INF/pages/admin.jsp", resp, req);

        } else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", "only for employees");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        if (req.getParameter("logout") != null) {
            logout(req, resp);
        }

    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (getEmployee(req, resp, "error") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn",false);
            session.setAttribute("employee",null);
            resp.sendRedirect(req.getContextPath() + " ");

        }else {
            //do something set error msg
        }
    }
}
