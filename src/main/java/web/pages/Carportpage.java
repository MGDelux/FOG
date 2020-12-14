package web.pages;

import api.utils;
import domain.Carport.Carport;
import domain.Customers.Customers;
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
public class Carportpage extends BaseServlet {

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
    private synchronized void getPageInfomation(HttpServletRequest req) {
        // TODO: bliver submitQ nogensinde null?
        /* JA DEN ER ALTID NULL VED MINDRE DEN ER TRYKKET PÅ */
        if (req.getParameter("submitQ") != null) {
            HttpSession session = req.getSession();
            log(req, "POST");
            //getting all page infomation (still missig roof type)
            String eMail = req.getParameter("Email");
            String address = req.getParameter("inputAddress");
            String city = req.getParameter("by");
            try {
                int zipCode = Integer.parseInt(req.getParameter("postnummer"));
                int phoneNR = Integer.parseInt(req.getParameter("phoneNR"));
                int carPortLength = Integer.parseInt(req.getParameter("CarportLength"));
                int carPortWidth = Integer.parseInt(req.getParameter("CarportWidth"));
                Shed carportShed = null;
                if (Objects.equals(req.getParameter("includeShed"), "on")) {
                    int shedLength = Integer.parseInt(req.getParameter("ShedLength"));
                    int shedWidth = Integer.parseInt(req.getParameter("ShedWidth"));
                    carportShed = new Shed(shedWidth, shedLength);
                }
                address = utils.removeHTML(address); //remove html might be redundant
                city = utils.removeHTML(city);
                Carport carport = new Carport(carPortWidth, carPortLength, domain.Carport.Carport.roofType.FLAT, 90);
                Customers customers = getUser(eMail, zipCode, city, address, phoneNR);
                API.newQuery(API.addCustomer(eMail, zipCode, city, address, phoneNR), carport, carportShed);
                sendMail(eMail, phoneNR); //TBA
                session.setAttribute("Customer", customers);
                session.setAttribute("Shed", carportShed);
                session.setAttribute("Carport", carport);


            } catch ( NumberFormatException | SQLException | MessagingException e) {
                //TODO: Should we inform the user about this?
                /* YES */
                session.setAttribute("pageError", e.getMessage());
                e.printStackTrace();
            }

        }
    }

    private Customers getUser(String eMail, int zipCode, String city, String address, int phoneNR) {
        int count = 1;
        try {
            for (Customers customers : API.getAllUsers()) {
                count++;
            }
        } catch (NullPointerException | SQLException e) {
            e.getMessage();
        }
        Customers customers;
        return customers = new Customers(count, eMail, zipCode, city, address, phoneNR);
    }

    private void sendMail(String eMail, int phoneNR) throws MessagingException {
        API.newMail(eMail, "Forspørgelse", "<h1>Tak for din Carport forspørgelse!</h1>\n" +
                "        <p>Din forespørgelse er blevet registreret og vi sender dig denne mail som bekræftelse på din forespørgelse </p>\n" +
                "        <h4><strong>forespørgelse detaljer:</strong></h4>\n" +
                "<p>Forspørgelse id# " + "T B D " + " </p>" +
                "        <h5>Kontakt infomationer:</h5>\n" +
                "<p>TLF NR:" + phoneNR + "</p>\n" +
                "<p>E-mail: " + eMail + "</p>" +
                "<p>Du kan bruge dette link til at se din forespørgelses detaljer: *LINK* </p>");
    }
}
