package web.pages;
import domain.Carport.Carport;
import domain.Queries.Queries;
import domain.Shed.Shed;
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

/**
 * CREATED BY mathias @ 14-12-2020 - 12:54
 **/

@WebServlet({"/details", "/details/*"})
public class QueryDetails extends BaseServlet {
    String carportSVG = "";
    Queries querybyId;
    SvgFactory SVG = new svgDraw();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {

            HttpSession session = req.getSession();
            int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
            try {
                querybyId = API.getQueryById(id);
                if (querybyId.getShed().getWidth() != 0 || querybyId.getShed().getLength() != 0) {
                    req.setAttribute("shedCheckbox", "checked ");
                } else {
                    req.setAttribute("shedCheckbox", "unchecked");
                }
                if (carportSVG.isEmpty() || carportSVG.equals("")){
                    carportSVG = SVG.drawCarport(querybyId);
                }
                req.setAttribute("svgDraw", carportSVG);
                req.setAttribute("qById", querybyId);

                System.out.println(querybyId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            render("/WEB-INF/pages/QueryDetails.jsp", resp, req);

        } else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", "only for employees");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("GenererSVG") != null) {
            int l = Integer.parseInt(req.getParameter("CarportLength"));
            int w = Integer.parseInt(req.getParameter("CarportWidth"));
            int sw = Integer.parseInt(req.getParameter("ShedWidth"));
            int sl = Integer.parseInt(req.getParameter("ShedLength"));

            carportSVG = null;
            carportSVG = SVG.updateDrawCarport(new Carport(w, l, Carport.roofType.FLAT, 90), new Shed(sw, sl));
        }
        resp.sendRedirect(req.getContextPath() + "/details/");
    }
}
