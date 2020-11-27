package web;

import api.FOGAPI;
import infrastructure.DatabaseCarport.DBCarport;
import infrastructure.DatabaseConnector.Database;
import infrastructure.DatabaseEmployees.DBEmployee;
import infrastructure.DatabaseMaterials.DBMaterials;
import infrastructure.DatabaseQuries.DBQueries;
import infrastructure.DatabaseUser.DBUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * CREATED BY mathi @ 19-11-2020 - 14:22
 **/

public class BaseServlet extends HttpServlet {
    protected static final FOGAPI API;

    static {
        API = initAPI();
    }

    private static FOGAPI initAPI() {
        Database db = new Database();
        return new FOGAPI(new DBEmployee(db), new DBUser(db), new DBCarport(db), new DBMaterials(db), new DBQueries(db));
    }


    public void setUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }


    public void render(String content, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        log(request, "page-loaded");
        request.getRequestDispatcher(content).forward(request, response);
    }

    protected void log(HttpServletRequest req, String message) {
        System.err.println("(" + LocalDateTime.now() + ") " + this.getClass().getCanonicalName() + " \"" + req.getRequestURI() + "\": " + message);
    }
}
