package web.pages;

import domain.Materials.Materials;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CREATED BY Emil @ 26-11-2020 - 11:29
 **/
@WebServlet({"/admin", "/admin/*"})
public class admin extends BaseServlet {
    final ArrayList<Materials>  mats = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (getEmployee(req, resp, "NEED TO BE LOGGED IN") != null) {
            try {
                for (Materials m : API.getAllMaterials()) {
                    mats.add(m);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            req.setAttribute("MaTsFoRu", mats);
            System.out.println(mats);
            render("/WEB-INF/pages/admin.jsp", resp, req);

        } else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", false);
            session.setAttribute("loggedInMSG", "only for employees");


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        if (req.getParameter("logout") != null) {
            logout(req, resp);
        }

    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (getEmployee(req, resp, "error") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn",false);
            session.setAttribute("employee",null);
            resp.sendRedirect(req.getContextPath() + " ");

        }else {
            //do something set error msg
        }
    }

}
