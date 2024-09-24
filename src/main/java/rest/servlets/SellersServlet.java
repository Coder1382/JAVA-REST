package rest.servlets;//package servlets;

import com.google.gson.Gson;
import rest.model.Seller;
import rest.services.SellersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellersServlet extends HttpServlet {
    private final SellersService sellersService = new SellersService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Gson jsn = new Gson();
        String[] uri = req.getRequestURI().split("/");
        if (uri[2].equals("sellers")) {
            PrintWriter pw = res.getWriter();
            pw.write("successfully added under id: " + sellersService.save(jsn.fromJson(req.getReader(), Seller.class)));
            pw.close();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String[] uri = req.getRequestURI().split("/");
        if (uri[2].equals("sellers")) {
            long id = -1;
            if (req.getQueryString() != null)
                id = Long.parseLong(req.getQueryString().split("=")[1]);
            PrintWriter pw = res.getWriter();
            sellersService.show(id).forEach(e -> {
                pw.write(e.toString() + "\n\n");
            });
            pw.close();
        }
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Gson jsn = new Gson();
        String[] uri = req.getRequestURI().split("/");
        if (uri[2].equals("sellers")) {
            try {
                sellersService.update(jsn.fromJson(req.getReader(), Seller.class));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            PrintWriter pw = res.getWriter();
            pw.write("fruit successfully added");
            pw.close();
        }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Gson jsn = new Gson();
        String[] uri = req.getRequestURI().split("/");
        if (uri[2].equals("sellers")) {
            sellersService.delete(jsn.fromJson(req.getReader(), Seller.class));
            PrintWriter pw = res.getWriter();
            pw.write("successfully deleted");
            pw.close();
        }
    }
}
