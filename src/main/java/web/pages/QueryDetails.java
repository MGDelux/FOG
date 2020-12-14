package web.pages;

import domain.Customers.Customers;
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
 * CREATED BY mathias @ 14-12-2020 - 12:54
 **/

@WebServlet({"/details", "/details/*"})
public class QueryDetails extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getEmployee(req,resp,"NEED TO BE LOGGED IN")!=null){
            ArrayList<Queries> queries = new ArrayList<>();
            ArrayList<Customers> customers = new ArrayList<>();
            setUp(req, resp);
            try {
                for (Queries q : API.getAllQueries()){
                    customers.add(API.getExistingCustomerInfomationById(q.getUserId()));
                    queries.add(q);
                }
            }catch (Exception e){
                e.printStackTrace();

            }
            req.setAttribute("Queries",queries);
            req.setAttribute("Customers",customers);
            System.out.println(queries);
            System.out.println(customers);
            render("/WEB-INF/pages/QueryDetails.jsp", resp, req);

        }else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG","only for employees");


        }

    }
}
