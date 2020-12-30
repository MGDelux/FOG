package web.pages;

import domain.Customers.Customers;
import domain.Queries.Queries;
import web.BaseServlet;
import web.SVG.SvgFactory;
import web.SVG.SvgDraw;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        try {
            Queries q = API.getLatestQuery();
            Customers customers = API.getExistingCustomerInfomation(q.getEmail());
            System.out.println(q.toString());
            req.setAttribute("query", q);
            String carportSVG;
            SvgFactory svgFactory = new SvgDraw();
            carportSVG = svgFactory.drawCarport(q);
            req.setAttribute("svgDraw", carportSVG);
            req.setAttribute("customer", customers); //christians code should be fixed?
            req.setAttribute("style", "/css/forsprøgelse.css");
            smartrender("/WEB-INF/pages/thankyou.jsp", resp, req);
            sendMail(q,customers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500);
        } catch (NumberFormatException | MessagingException e) {
            resp.sendError(404);
        
        }

    }

    private void sendMail(Queries q, Customers customers) throws MessagingException { ;
        API.newMail(customers.getEmail(), "Forspørgelse", "<h1>Tak for din Carport forspørgelse!</h1>\n" +
                "        <p>Din forespørgelse er blevet registreret og vi sender dig denne mail som bekræftelse på din forespørgelse </p>\n" +
                "        <h4><strong>forespørgelse detaljer:</strong></h4>\n" +
                "<p>Carport: " + q.getCarport().toString() + " </p> \n<p> Skur:" + q.getShed().toString() +
                "</p> \n  <h5>Kontakt infomationer:</h5>\n" +
                "<p>TLF NR:" + customers.getPhoneNr() + "</p>\n" +
                "<p>E-mail: " + customers.getEmail() + "</p>" +
                "<h5>Leverings infomationer:</h5>" +
                "<p>Adresse: " + customers.getAddress() +
                "</p><p>By: " + customers.getCity() +
                "</p><p>Post Nummer: " + customers.getZipCode() +
                "</p><p>Du kan bruge dette link til at se din forespørgelses detaljer: http://solidcode.xyz/FOG-1.0/carportDetails/ </p>");
    }
}
