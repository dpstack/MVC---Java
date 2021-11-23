package access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.ClientModel;
import utils.ConnectionDB;

public class ClientDAO {
    private Connection conn = null;

    public void createClient(ClientModel client) {
        try {
            if (conn == null) {
                conn = ConnectionDB.getConnection();
            }
            String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getName());
            statement.setString(3, client.getLastname());
            statement.setString(4, client.getEmail());
            statement.setLong(5, client.getCellphone());
            statement.setString(6, client.getPassword());
            statement.setString(7, client.getDate());
            
            int result = statement.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Cliente agregado en la base de datos");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode()
                    + "Algo salio mal, asegurese de agregar los datos en su forma correcta");
        }

    }

    public ClientModel getClientByLogin(String login) {
        ClientModel client = null;

        try {
            // 1. Get connection
            if (conn == null)
                conn = ConnectionDB.getConnection();
            // 2. create statement
            String statement = "SELECT * FROM cliente where cli_login = ?";
            // 3. prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, login);
            // 4. execute query
            ResultSet result = preparedStatement.executeQuery();
            // 5. loop the result set
            while (result.next()) {
                String name = result.getString(2);
                String lastname = result.getString(3);
                String email = result.getString(4);
                long cellphone = result.getLong(5);
                String password = result.getString(6);
                String date = result.getString(7);
                client = new ClientModel(login, name, lastname, email, cellphone, password, date);
                break;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
        return client;
    }

    public void updateClient(ClientModel client) {
        try {
            if (conn == null) {
                conn = ConnectionDB.getConnection();
            }
            String sql = "UPDATE cliente SET cli_name = ?, cli_lastname = ?, cli_email = ?, cli_cellphone = ?, cli_password = ?, cli_date = ? where cli_login = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, client.getName());
            statement.setString(2, client.getLastname());
            statement.setString(3, client.getEmail());
            statement.setLong(4, client.getCellphone());
            statement.setString(5, client.getPassword());
            statement.setString(6, client.getDate());
            statement.setString(7, client.getLogin());
            int result = statement.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado en la base de datos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
    }

    public void deleteClient(String login) throws Exception {
        try {
            if (conn == null) {
                conn = ConnectionDB.getConnection();
            }
            String sql = "DELETE FROM cliente WHERE cli_login = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Eliminado");
            } else {
                throw new Exception("El usuario no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        ClientModel result = clientDAO.getClientByLogin("ninja");
        ClientModel client = new ClientModel("edward", "Edward", "Ramirez", "asdf@gmail.com", 331231232, "testing",
                "2020-08-09");
        clientDAO.createClient(client);
        client.setLastname("Gonzalez");
        clientDAO.updateClient(client);
        clientDAO.deleteClient("edward");
        ArrayList<ClientModel> clients = clientDAO.getClients();
        for (ClientModel item : clients) {
            System.out.println(item);
        }

    }

    public ArrayList<ClientModel> getClients() {
        ArrayList<ClientModel> clients = new ArrayList<ClientModel>();

        try {
            // 1. Get connection
            if (conn == null)
                conn = ConnectionDB.getConnection();
            // 2. create statement
            String statement = "SELECT * FROM cliente";
            // 3. prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            // 4. execute query
            ResultSet result = preparedStatement.executeQuery();
            // 5. loop the result set
            while (result.next()) {
                String login = result.getString(1);
                String name = result.getString(2);
                String lastname = result.getString(3);
                String email = result.getString(4);
                long cellphone = result.getLong(5);
                String password = result.getString(6);
                String date = result.getString(7);
                ClientModel client = new ClientModel(login, name, lastname, email, cellphone, password, date);
                clients.add(client);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
        return clients;
    }
}