package rest.services;

import com.google.gson.Gson;
import rest.dao.DatabaseConnector;
import rest.dao.FruitDao;
import rest.dao.SuppliersDao;
import rest.model.Fruit;
import rest.model.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SuppliersService {
    final SuppliersDao sudao = new SuppliersDao();

    public void showData(HttpServletRequest req, long id, HttpServletResponse res) throws IOException {
        List<Object> obj = new ArrayList<>();
        if (id > 0)
            obj = sudao.showData("SELECT * FROM suppliers WHERE id=?", id);
        else obj = sudao.showData("SELECT * FROM suppliers", -1);
        PrintWriter pw = res.getWriter();
        if (pw != null) {
            obj.forEach(e -> {
                pw.write(e.toString() + "\n\n");
            });
            pw.close();
        }
    }

    public void addData(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson jsn = new Gson();
        String[] arr = (jsn.fromJson(req.getReader(), Supplier.class).toString()).split(",");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        long id = sudao.addData(arr[1].split(" ")[2]);
        PrintWriter pw = res.getWriter();
        if (pw != null) {
            pw.write("successfully added under id: " + id);
            pw.close();
        }
    }

    public void deleteData(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson jsn = new Gson();
        String[] arr = (jsn.fromJson(req.getReader(), Supplier.class).toString()).split(",");
        long id = Long.parseLong((arr[0].split(" "))[1]);
        sudao.deleteData(id);
        PrintWriter pw = res.getWriter();
        if (pw != null) {
            pw.write("successfully deleted");
            pw.close();
        }
    }
}
