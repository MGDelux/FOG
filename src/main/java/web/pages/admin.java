package web.pages;

import domain.Materials.Materials;
import web.BaseServlet;
import infrastructure.DatabaseMaterials.DBMaterials;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * CREATED BY Emil @ 26-11-2020 - 11:29
 **/
@WebServlet({"/admin", "/admin/*"})
public class admin extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ArrayList<Materials> mats = new ArrayList<>();
        final ArrayList<Materials> fittingTheScrew = new ArrayList<>();

        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {
            try {
            } catch (Exception e) {
                e.printStackTrace();

            }
            req.setAttribute("MaTsFoRu", mats);
            mats.addAll((Collection<? extends Materials>) API.getAllMaterials());
            System.out.println(mats);

            req.setAttribute("matsScrew", fittingTheScrew);
            fittingTheScrew.addAll((Collection<? extends Materials>) API.findScrews());
            System.out.println(fittingTheScrew);

            System.out.println(mats);
            render("/WEB-INF/pages/admin.jsp", resp, req);


        } else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", "Kun adgang til medarbejdere");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("POST");
        if (req.getParameter("logout") != null) {
            logout(req, resp);
        }
        if (req.getParameter("removeMaterial") != null) {
            removeMaterialById(req, resp);
        }
        if (req.getParameter("add-button") != null) {
            addTreeMats(req, resp);

        }
    }

    private void addTreeMats(HttpServletRequest req, HttpServletResponse resp) {
        int amount = Integer.parseInt(req.getParameter("materialantal"));
        String name = (req.getParameter("materialName"));
        String description = (req.getParameter("materialDescription"));
        int price = Integer.parseInt(req.getParameter("materialPrice"));
        System.out.println(amount + name + description + price);
        try {
            API.addCarportMaterials(new Materials(2,"awe",4,24, "we",23));

        } finally {

        }
    }



    private void removeMaterialById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int value = Integer.parseInt(req.getParameter("removeMaterialById"));
        System.out.println(value);
        try {
            API.deleteCarportMaterial(value);
            resp.sendRedirect(req.getContextPath() + "/admin/");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (getEmployee(req, resp, "error") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("employee", null);
            resp.sendRedirect(req.getContextPath() + " ");

        } else {
            //do something set error msg
        }
    }

}
