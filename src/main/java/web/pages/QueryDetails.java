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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {
            String carportSVG = "";
            try {
                HttpSession session = req.getSession();
                 int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
                 Queries querybyId;
                querybyId = API.getQueryById(id);
                if (querybyId.getShed().getWidth() != 0 || querybyId.getShed().getLength() != 0) {
                    req.setAttribute("shedCheckbox", "checked ");
                } else {
                    req.setAttribute("shedCheckbox", "unchecked");
                }
                if (carportSVG.isEmpty() || carportSVG.equals("")) {
                    SvgFactory svgFactory = new svgDraw();
                    carportSVG = svgFactory.drawCarport(querybyId);
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
            session.setAttribute("loggedInMSG", "Kun adgang til medarbejdere");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("CarportUpdate") != null) {
            int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());

            req.removeAttribute("svgDraw");
            int l = Integer.parseInt(req.getParameter("CarportLength"));
            int w = Integer.parseInt(req.getParameter("CarportWidth"));
            int sw = Integer.parseInt(req.getParameter("ShedWidth"));
            int sl = Integer.parseInt(req.getParameter("ShedLength"));
            SvgFactory svgFactory = new svgDraw();
            String carportSVG = "";
            if (Integer.parseInt(req.getParameter("tagChoice")) == 1){
                //If tagChoice == 1 flat // 0 == angled
                carportSVG = svgFactory.updateDrawCarport(new Carport(w, l, Carport.roofType.FLAT, 0), new Shed(sw, sl));
                API.updateQuery(id,new Carport(w, l, Carport.roofType.FLAT, 0), new Shed(sw, sl));
            }else {
                //TODO: CUSTOM ANGLE
                carportSVG = svgFactory.updateDrawCarport(new Carport(w, l, Carport.roofType.ANGLE, 40), new Shed(sw, sl));
                API.updateQuery(id, new Carport(w, l, Carport.roofType.ANGLE, 40), new Shed(sw, sl));
            }
            req.setAttribute("svgDraw", carportSVG);
            Queries querybyId;
            try {
                querybyId = API.getQueryById(id);
                req.setAttribute("qById", querybyId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        render("/WEB-INF/pages/QueryDetails.jsp", resp, req);
    }
}
