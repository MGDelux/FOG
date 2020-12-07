package web.pages;

import api.utils;
import domain.Queries.Queries;
import web.BaseServlet;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CREATED BY mathias @ 23-11-2020 - 14:20
 **/
@WebServlet({"/carport", "/carport/*"})
public class Carport extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/carport.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            getPageInfomation(req);
            resp.sendRedirect(req.getContextPath() + "/thankyou/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the information out of carport page sent with post
     * TODO: Mathias i dont understand this shit
     * fuck you
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
            String zipCode = req.getParameter("postnummer");
            String phoneNR = req.getParameter("phoneNR");
            String carPortLength = req.getParameter("CarportLength");
            String carPortWidth = req.getParameter("CarportWidth");
            String shedLength = req.getParameter("ShedLength");
            String shedWidth = req.getParameter("ShedWidth");
            address = utils.removeHTML(address); //remove html might be redundant
            city = utils.removeHTML(city);
            System.out.println("ADRESSE : " + address);
            log(req, "shed: " + shedLength + " w " + shedWidth);
            log(req, " -> " + carPortLength + " WIDTH " + carPortWidth + " cm  Email: " + eMail + " adress " + address + " city " + city + " zip " + zipCode + " pnr " + phoneNR);
            //convert to string into Intergers
            try {
                int zipCodeToInt = Integer.parseInt(zipCode);
                int phoneNrToInt = Integer.parseInt(phoneNR);
                int carPortWidthToInt = Integer.parseInt(carPortWidth);
                int carPortLengthToInt = Integer.parseInt(carPortLength);
                int shedWidthToInt = Integer.parseInt(shedWidth);
                int shedLengthToInt = Integer.parseInt(shedLength);

                 API.newQuery(API.addCustomer(eMail, zipCodeToInt, city, address, phoneNrToInt), carPortWidthToInt, carPortLengthToInt, "flat", shedWidthToInt, shedLengthToInt);
                API.newMail(eMail, "Forspørgelse", "<h1>Tak for din Carport forspørgelse!</h1>\n" +
                        "        <p>Din forspørgelse er blevet registeret og vi sender dig denne mail som bekræftelse på din forspørgelse </p>\n" +
                        "        <h4><strong>Forspørgelse detailer:</strong></h4>\n" +
                        "<p>Forspørgelse id# "+ "T B D "+" </p>" +
                        "        <h5>Kontakt infomationer:</h5>\n" +
                        "<p>TLF NR:"+ phoneNR + "</p>\n" +
                        "<p>E-mail: "+ eMail + "</p>"+
                        "<p>Du kan bruge dette link til at se din forspørgelse detailjer: *LINK* </p>");
                session.setAttribute("userEmail", eMail);
                session.setAttribute("userTlf", phoneNR);
                session.setAttribute("CarportW",carPortWidth);
                session.setAttribute("CarportL",carPortLength);
                session.setAttribute("TagType","flat"); //update
                session.setAttribute("ShedW",shedWidth);
                session.setAttribute("ShedL",shedLength);



            } catch (NumberFormatException | SQLException | MessagingException e) {
                //TODO: Should we inform the user about this?
                /* YES */
                e.printStackTrace();
            }

        }
    }
}
