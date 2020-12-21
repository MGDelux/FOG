package web.pages;

import api.utils;
import domain.Carport.Carport;
import domain.Customers.Customers;
import domain.Queries.Queries;
import domain.Shed.Shed;
import web.BaseServlet;

import javax.mail.MessagingException;
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
public class carportpage extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/carport.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            getPageInfomation(req);
            resp.sendRedirect(req.getContextPath() + "/thankyou/1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the information out of carport page sent with post
     *
     * @param req
     */
    private void getPageInfomation(HttpServletRequest req) {
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
                int zipCode = Integer.parseInt(req.getParameter("postnummer"));
                int phoneNR = Integer.parseInt(req.getParameter("phoneNR"));
                int carPortLength = Integer.parseInt(req.getParameter("CarportLength"));
                int carPortWidth = Integer.parseInt(req.getParameter("CarportWidth"));
                if (Objects.equals(req.getParameter("includeShed"), "on")) {
                    int shedWidth = Integer.parseInt(req.getParameter("ShedWidth"));
                    int shedLength = Integer.parseInt(req.getParameter("ShedLength"));
                    carportShed = new Shed(shedWidth, shedLength);
                } else {
                    carportShed = new Shed(0,0);
                }

                if (Objects.equals(req.getParameter("radio"), "on")) {
                    Carport carport = new Carport(carPortWidth, carPortLength, domain.Carport.Carport.roofType.FLAT, 0);
                    API.newQuery(API.addCustomer(eMail, zipCode, city, address, phoneNR), carport, carportShed);
                    session.setAttribute("Shed", carportShed);
                    session.setAttribute("Carport", carport);
                    Customers customers = getUser(eMail, zipCode, city, address, phoneNR);
                    session.setAttribute("customer", customers);
                    sendMail(customers, getQueryID(), carport, carportShed);
                } else {
                    Carport carport = new Carport(carPortWidth, carPortLength, Carport.roofType.ANGLE, 25);
                    API.newQuery(API.addCustomer(eMail, zipCode, city, address, phoneNR), carport, carportShed);
                    session.setAttribute("Carport", carport);
                    session.setAttribute("Shed", carportShed);
                    Customers customers = getUser(eMail, zipCode, city, address, phoneNR);
                    session.setAttribute("customer", customers);
                    sendMail(customers, getQueryID(), carport, carportShed);
                }


            } catch (NumberFormatException | SQLException | MessagingException e) {
                session.setAttribute("pageError", e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private int getQueryID() { // THIS IS FOR TESTING  ADD TO API
        int count = 0;
        try {
            for (Queries q : API.getAllQueries()) {
                count++;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return count;
    }

    private Customers getUser(String eMail, int zipCode, String city, String address, int phoneNR) {
        int count = 1; //bad way to do it kekw will fix later
        try {
            for (Customers customers : API.getAllUsers()) {
                count++;
            }
        } catch (NullPointerException | SQLException e) {
            e.getMessage();
        }
        return new Customers(count, eMail, zipCode, city, address, phoneNR);
    }

    private void sendMail(Customers customers, int id, Carport carport, Shed shed) throws MessagingException {
        API.newMail(customers.getEmail(), "Forspørgelse", "<h1>Tak for din Carport forspørgelse!</h1>\n" +
                "        <p>Din forespørgelse er blevet registreret og vi sender dig denne mail som bekræftelse på din forespørgelse </p>\n" +
                "        <h4><strong>forespørgelse detaljer:</strong></h4>\n" +
                "<p>Forspørgelse ID #" + id + " </p>" +
                "<p>Carport: " + carport.toString() + " </p> \n<p> Skur:" + shed.toString() +
                "</p> \n  <h5>Kontakt infomationer:</h5>\n" +
                "<p>TLF NR:" + customers.getPhoneNr() + "</p>\n" +
                "<p>E-mail: " + customers.getEmail() + "</p>" +
                "<h5>Leverings infomationer:</h5>" +
                "<p>Adresse: " + customers.getAddress() +
                "</p><p>By: " + customers.getCity() +
                "</p><p>Post Nummer: " + customers.getZipCode() +
                "</p><p>Du kan bruge dette link til at se din forespørgelses detaljer: *LINK* </p>");
    }
}
