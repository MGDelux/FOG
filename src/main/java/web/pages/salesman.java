package web.pages;

import domain.Employees.Employee;
import domain.Queries.Queries;
import infrastructure.DatabaseConnector.Database;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATED BY Emil @ 26-11-2020 - 11:29
 **/
@WebServlet({"/salesman", "/salesman/*"})
public class salesman extends BaseServlet {

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
            render("/WEB-INF/pages/salesman.jsp", resp, req);

        } else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", "Kun adgang til medarbejdere");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        if (req.getParameter("logout") != null) {
            logout(req, resp);
        }
        if (req.getParameter("assigSellButton") != null) {
            Employee employee = getEmployee(req, resp, "error in getting employe info");
            int getQueryValue = Integer.parseInt(req.getParameter("assignSell"));
            try {
                API.deleteQurey(getQueryValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(getQueryValue);
            API.assignSellerToQuery(getQueryValue, employee);
            resp.sendRedirect(req.getContextPath() + "/salesman/");
        }
        if (req.getParameter("details") != null) {
            int value = Integer.parseInt(req.getParameter("selectOrder"));
            System.out.println(value);
            HttpSession session = req.getSession();
            session.setAttribute("selectedQuery", value);
            resp.sendRedirect(req.getContextPath() + "/details/");
        }

        if (req.getParameter("deleteOrderButton") != null) {
            int value = Integer.parseInt(req.getParameter("deleteOrder"));
            System.out.println(value);
            try {
                API.deleteQuery(value);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/salesman/");
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (getEmployee(req, resp, "error") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("employee", null);
            resp.sendRedirect(req.getContextPath() + " ");

        } else {
            //do something set error msg
        }
    }
}
