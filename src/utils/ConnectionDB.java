package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Daniel Pérez & Valeria Bastidas
 */
public class ConnectionDB {


    /**
     * 
     * @return
     */
    public static Connection getConnection() {
        JSONParser parser = new JSONParser();
        Connection conn = null;
        // conectar
        try {
            String credentials_path = System.getProperty("user.dir") + "/src/utils/db_credentials.json";
            System.out.println(credentials_path);
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(credentials_path));

            String host = (String) jsonObject.get("db_ip");
            String port = (String) jsonObject.get("db_port");
            String username = (String) jsonObject.get("db_user");
            String password = (String) jsonObject.get("db_password");
            String dbURL    = "jdbc:mysql://"+host+":"+port+"/reto";

            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null)
                System.out.println("Conectado");
        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}