package rest;

import rest.dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    static final Connection connect = DatabaseConnector.connector();

    public static void CreateFruitTable() throws SQLException {
        connect.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS fruit(id BIGSERIAL PRIMARY KEY, " +
                "name VARCHAR(80), color VARCHAR(20), price NUMERIC CHECK (price>=0), sel TEXT [])");
    }

    public static void CreateSellersTable() throws SQLException {
        connect.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS sellers(id BIGSERIAL PRIMARY KEY, " +
                "name VARCHAR(80), supplier_id BIGINT, fruits TEXT [], " +
                "CONSTRAINT fk FOREIGN KEY(supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE)");
    }

    public static void CreateSuppliersTable() throws SQLException {
        connect.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS suppliers(id BIGSERIAL PRIMARY KEY, " +
                "name VARCHAR(80), clients TEXT [])");
    }


    public static void DropTable(String table) throws SQLException {
        connect.createStatement().executeUpdate("DROP TABLE IF EXISTS " + table + " CASCADE");
    }

    public static void TruncateTable(String table) throws SQLException {
        connect.createStatement().executeUpdate("TRUNCATE TABLE " + table + " RESTART IDENTITY CASCADE");
    }
}
