package web.pages;

import com.google.protobuf.Api;
import domain.Carport.Carport;
import domain.Customers.Customers;
import domain.Queries.Queries;
import web.BaseServlet;
import web.SVG.SvgFactory;
import web.SVG.svgDraw;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATED BY mathias @ 14-12-2020 - 12:54
 **/

@WebServlet({"/details", "/details/*"})
public class QueryDetails extends BaseServlet {
private Queries querybyId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getEmployee(req,resp,"NEED TO BE LOGGED IN")!=null){
            SvgFactory SVG = new svgDraw();
            HttpSession session = req.getSession();
            int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
            try {
                querybyId =  API.getQueryById(id);
                String carportSVG = SVG.drawCarport(querybyId);
                req.setAttribute("qById",querybyId);
                req.setAttribute("svgDraw",carportSVG);
                System.out.println(querybyId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            render("/WEB-INF/pages/QueryDetails.jsp", resp, req);

        }else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG","only for employees");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
