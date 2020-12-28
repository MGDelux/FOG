package web.pages;

import domain.Queries.Queries;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATED BY mathias @ 21-12-2020 - 13:27
 **/
@WebServlet({"/carportDetails", "/carportDetails/*"})

public class carportDetails extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/carportDetails.jsp", resp, req);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        try {
            ArrayList<Queries> queries = new ArrayList<>(API.getQueryByEmail(email));
            req.setAttribute("Query",queries);
            System.out.println(queries);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        render("/WEB-INF/pages/carportDetails.jsp", resp, req);

    }
}
