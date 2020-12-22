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
        if (req.getParameter("add-material") != null) {
            try {
                addTreeMats(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (req.getParameter("replace-material") != null) {
            replaceTreeMats(req, resp);
        }
        if (req.getParameter("add-screw") != null){
            addFittingsAndScrews(req, resp);
        }
        if (req.getParameter("replace-screw") != null){
            replaceFittingsAndScrews(req, resp);
        }
        if (req.getParameter("removeScrew") != null){
            removeFittingsAndScrews(req, resp);
        }

    }

    private void addTreeMats(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("materialId"));
        int amount = Integer.parseInt(req.getParameter("materialAntal"));
        String name = (req.getParameter("materialName"));
        String description = (req.getParameter("materialDescription"));
        int length = Integer.parseInt(req.getParameter("materialLength"));
        int price = Integer.parseInt(req.getParameter("materialPrice"));
        try {
           API.addCarportMaterials(new Materials(id,name,length,amount,description,price),id);
            resp.sendRedirect(req.getContextPath() + "/admin/");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void replaceTreeMats (HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("materialId"));
        int amount = Integer.parseInt(req.getParameter("materialAntal"));
        String name = (req.getParameter("materialName"));
        String description = (req.getParameter("materialDescription"));
        int length = Integer.parseInt(req.getParameter("materialLength"));
        int price = Integer.parseInt(req.getParameter("materialPrice"));
        try {
        API.updateMaterials(new Materials(id,name,length,amount,description,price),id);
        resp.sendRedirect(req.getContextPath() + "/admin/");
        } catch (Exception e) {
            e.printStackTrace();
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
    private void addFittingsAndScrews(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("screwId"));
        int amount = Integer.parseInt(req.getParameter("screwAntal"));
        String name = (req.getParameter("screwName"));
        String description = (req.getParameter("screwDescription"));
        int length = Integer.parseInt(req.getParameter("screwLength"));
        int price = Integer.parseInt(req.getParameter("screwPrice"));
        try {
            API.addFittingsAndScrews(new Materials(id, name, length, amount, description, price), id);
            resp.sendRedirect(req.getContextPath() + "/admin/");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void replaceFittingsAndScrews(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("screwId"));
        int amount = Integer.parseInt(req.getParameter("screwAntal"));
        String name = (req.getParameter("screwName"));
        String description = (req.getParameter("screwDescription"));
        int length = Integer.parseInt(req.getParameter("screwLength"));
        int price = Integer.parseInt(req.getParameter("screwPrice"));
        try {
            API.updateFittingsAndScrews(new Materials(id, name, length, amount, description, price), id);
            resp.sendRedirect(req.getContextPath() + "/admin/");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
    private void removeFittingsAndScrews(HttpServletRequest req, HttpServletResponse resp) {
        int value = Integer.parseInt(req.getParameter("removeScrewById"));
        try {
            API.deleteFittingsAndScrews(value);
            resp.sendRedirect(req.getContextPath() + "/admin/");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
