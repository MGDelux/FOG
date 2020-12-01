package web.pages;

import domain.Users.User;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * CREATED BY mathi @ 23-11-2020 - 14:20
 **/
@WebServlet({"/carport", "/carport/*"})
public class Carport extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/WEB-INF/pages/carport.jsp", resp, req);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        getPageInfomation(req);
    }

    /**
     * get the information out of carport page sent with post
     * TODO: Mathias i dont understand this shit
     *
     * @param req
     */
    private synchronized void getPageInfomation(HttpServletRequest req) {
        // TODO: bliver submitQ nogensinde null?
        if (req.getParameter("submitQ") != null) {

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
                }catch (NumberFormatException e){
                    //TODO: Should we inform the user about this?
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
//add possiblle Customer via API to db ect
                //and added query to system + db


        }
    }
}
