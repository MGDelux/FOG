package web.pages;

import domain.Queries.Queries;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 03-12-2020 - 12:35
 **/
@WebServlet({"/thankyou", "/thankyou/*"})

public class ThankYou extends BaseServlet {

    // private final System.Logger logger = LoggerFactory.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: tjek om user er logget ind her.

        try {
            Queries q = API.getLatestQuery();
            System.out.println(q.toString());
            req.setAttribute("query", q);
            req.setAttribute("customer", API.getExistingUserInfomation(q.getEmail())); //christians code should be fixed?
            req.setAttribute("style", "/css/forsprøgelse.css");
            smartrender("/WEB-INF/pages/thankyou.jsp", resp, req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500);
        } catch (NumberFormatException e) {
            resp.sendError(404);
        }

    }
}
