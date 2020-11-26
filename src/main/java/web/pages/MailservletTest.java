package web.pages;

import web.MailService.Mail;
import web.BaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * CREATED BY mathias @ 25-11-2020 - 16:19
 **/
@WebServlet("/EmailSendingServlet")
public class MailservletTest extends BaseServlet {

@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/mailtest.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gets mail info from web.xml
        ServletContext context = getServletContext();
        String host = context.getInitParameter("host");String port = context.getInitParameter("port");String user = context.getInitParameter("user");String pass = context.getInitParameter("pass");
        String recipient = request.getParameter("recipient"); //gets page parameteres
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        content = content + "<h3>VI HAR MODTAGET DIN FORSPØRGELSE!</h3> \n <p>Tak for din interesse i vores carporte 'NAVN'</p> \n <p> En af vores sælgere vil snarest kontakte dem</p> \n <p>Dette er en automatisk mail og kan IKKE besvares</p>  <a href='http://solidcode.xyz/'>FOG</a> " + LocalDateTime.now();

        String resultMessage = "";
        try {
            Mail.sendEmail(host, port, user, pass, recipient, subject, content);
            resultMessage = "Email sent ";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/pages/Result.jsp").forward(request, response);
        }
    }
}