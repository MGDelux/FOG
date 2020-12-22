package web.pages;

import domain.BOM.BomService;
import domain.BOM.Exceptions.BomException;
import domain.Carport.Carport;
import domain.Materials.Materials;
import domain.Queries.Queries;
import domain.Shed.Shed;
import web.BaseServlet;
import web.SVG.SvgFactory;
import web.SVG.svgDraw;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CREATED BY mathias @ 14-12-2020 - 12:54
 **/

@WebServlet({"/details", "/details/*"})
public class QueryDetails extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {
            String carportSVG = "";
            List<Materials> materials = new ArrayList<>();
            req.setAttribute("BOM", materials);

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
        if (req.getParameter("beregnstyk") != null) {
            try {
                calculateBOM(req);
            } catch (SQLException | BomException e) {
                e.getMessage();
            }
        }
        if (req.getParameter("CarportUpdate") != null) {
            updateCarport(req);
        }
        if (req.getParameter("SendTilbud") != null) {
            try {
                createFinalOffer(req);
            } catch (SQLException | MessagingException | BomException throwables) {
                throwables.printStackTrace();
            }
        }
        updateCarport(req);
        render("/WEB-INF/pages/QueryDetails.jsp", resp, req);
    }

    private void createFinalOffer(HttpServletRequest req) throws SQLException, MessagingException, BomException {
        BomService bomService = new BomService();

        HttpSession session = req.getSession();
        double offerSum = Double.parseDouble(req.getParameter("customvalue"));
        int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
        Queries queries = API.getQueryById(id);
        String extraText = "<p>Plukliste: </p>";
        extraText = extraText + bomService.newBom(queries.getCarport(), queries.getShed()).toString();
        API.newMail(queries.getEmail(), "Carport Forespørgelse Tilbud", "<h1>Vi har et tilbud til dig:</h1>" +
                "<h2>Vedr din. forespørgelse #" + id + "</h2> " +
                "<p>Vi sender dig denne mail som svar på din forespørgelse og vi har lavet dette tilbud til dem: </p>" +
                "<p>Vi kan tilbyde dig carporten til:</p> <h5> " + offerSum + "</h5><p>kr</p>" +
                "<h4>Carport Details:</h4>" +
                "<p>" + queries.getCarport() + "</p> <h4> Skur:</h4> <p> " + queries.getShed() + "</p>" +
                "<h4>MVH. - FOG </h4>" + extraText);


    }

    private void calculateBOM(HttpServletRequest req) throws SQLException, BomException {
        ArrayList<Materials> materials = new ArrayList<>();
        HttpSession session = req.getSession();
        int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
        Queries querybyId = API.getQueryById(id);
        BomService bomService = new BomService();
        materials.addAll(bomService.newBom(querybyId.getCarport(), querybyId.getShed()));
        req.setAttribute("BOM", materials);
        System.out.println("set B O M " + materials);
        calculateSum(materials, req);
    }

    private void calculateSum(ArrayList<Materials> materials, HttpServletRequest req) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        double sumWithOutMoms = 0;
        double kostPris = 0;
        double sumWithMoms = 0;

        for (Materials m : materials) {
            sumWithOutMoms = sumWithOutMoms + m.getPrice();
            System.out.println(sumWithOutMoms);

        }
        kostPris = sumWithOutMoms;
        sumWithOutMoms = sumWithOutMoms * 1.65;
        sumWithMoms = sumWithOutMoms * 1.25;
        double fortjeneste = sumWithOutMoms - kostPris;
        req.setAttribute("sumWithOutMoms", numberFormat.format(sumWithOutMoms));
        req.setAttribute("kostPris", numberFormat.format(kostPris));
        req.setAttribute("sumWithMoms", numberFormat.format(sumWithMoms));
        req.setAttribute("fortjeneste", numberFormat.format(fortjeneste));
    }


    private void updateCarport(HttpServletRequest req) {
        HttpSession session = req.getSession();
        final int id = Integer.parseInt(session.getAttribute("selectedQuery").toString());
        req.removeAttribute("svgDraw");
        int l = Integer.parseInt(req.getParameter("CarportLength"));
        int w = Integer.parseInt(req.getParameter("CarportWidth"));
        int sw;
        int sl;
        if (Objects.equals(req.getParameter("includeShed"), "on")) {
            System.out.println((req.getParameter("includeShed")));
            sw = Integer.parseInt(req.getParameter("ShedWidth"));
            sl = Integer.parseInt(req.getParameter("ShedLength"));
        } else {
            sw = 0;
            sl = 0;
        }
        SvgFactory svgFactory = new svgDraw();
        String carportSVG = "";
        if (Integer.parseInt(req.getParameter("tagChoice")) == 1) {
            carportSVG = svgFactory.updateDrawCarport(new Carport(w, l, Carport.roofType.FLAT, 0), new Shed(sw, sl));
            API.updateQuery(id, new Carport(w, l, Carport.roofType.FLAT, 0), new Shed(sw, sl));
        } else {
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
}
