package web.pages;

import domain.Carport.Carport;
import domain.Customers.Customers;
import domain.Shed.Shed;
import infrastructure.DatabaseUser.Execptions.CustomerExecption;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * CREATED BY mathias @ 23-11-2020 - 14:20
 **/
@WebServlet({"/carport", "/carport/*"})
public class CarportPage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/carport.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            getPageInfomation(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the information out of carport page sent with post
     *
     * @param req current session request scope
     */
    private synchronized void getPageInfomation(HttpServletRequest req, HttpServletResponse response) {
        /* JA DEN ER ALTID NULL VED MINDRE DEN ER TRYKKET PÅ */
        if (req.getParameter("submitQ") != null) {
            HttpSession session = req.getSession();
            log(req, "POST");
            //getting all page infomation (still missig roof type)
            String eMail = req.getParameter("Email");
            String address = req.getParameter("inputAddress");
            String city = req.getParameter("by");
            try {
                Shed carportShed;
                Carport.roofType roofType;
                int zipCode = Integer.parseInt(req.getParameter("postnummer"));
                int phoneNR = Integer.parseInt(req.getParameter("phoneNR"));
                int carPortLength = Integer.parseInt(req.getParameter("CarportLength"));
                int carPortWidth = Integer.parseInt(req.getParameter("CarportWidth"));
                System.out.println(req.getParameter("includeShed"));
                if (Objects.equals(req.getParameter("includeShed"), "on")) {
                    System.out.println("SHED ON");
                    int shedWidth = Integer.parseInt(req.getParameter("ShedWidth"));
                    int shedLength = Integer.parseInt(req.getParameter("ShedLength"));
                    carportShed = new Shed(shedWidth, shedLength);
                } else {
                    System.out.println("SHED OFF");

                    carportShed = new Shed(0, 0);
                }

                if (Objects.equals(req.getParameter("radio"), "on")) {
                    roofType = Carport.roofType.FLAT;
                    System.out.println("FLAT TAG");

                } else {
                    roofType = Carport.roofType.ANGLE;
                    System.out.println("ANGLE TAG");

                }
                if (carportShed.getLength() > carPortLength || carportShed.getWidth() > carPortWidth){
                    session.setAttribute("pageError","Skuret kan ikke være størrer end selve carporten.");
                    render("/WEB-INF/pages/carport.jsp", response, req);
                }else {
                    Carport carport = new Carport(carPortWidth, carPortLength, roofType, 0);
                    API.newQuery(API.addCustomer(eMail, zipCode, city, address, phoneNR), carport, carportShed);
                    Customers customers = getUser(eMail, zipCode, city, address, phoneNR);
                    session.setAttribute("Shed", carportShed);
                    session.setAttribute("Carport", carport);
                    session.setAttribute("customer", customers);
                    response.sendRedirect(req.getContextPath() + "/thankyou/1");
                }
            } catch (NumberFormatException | SQLException | IOException | ServletException e) {
                session.setAttribute("pageError", e.getMessage());
                e.printStackTrace();
            }

        }
    }


    private Customers getUser(String eMail, int zipCode, String city, String address, int phoneNR) throws CustomerExecption {
        int count = 1; //bad way to do it kekw will fix later
        try {
            for (Customers ignored : API.getAllUsers()) {
                count++;
            }
        } catch (NullPointerException | SQLException e) {
            throw new CustomerExecption(e.getMessage());
        }
        return new Customers(count, eMail, zipCode, city, address, phoneNR);
    }
}
