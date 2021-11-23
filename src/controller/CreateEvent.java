package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import access.ClientDAO;
import model.ClientModel;
import view.clienteMVC;

public class CreateEvent implements ActionListener {
    private clienteMVC view;
    private ClientDAO clientDAO;

    public CreateEvent(clienteMVC view) {
        this.view = view;
        clientDAO = new ClientDAO();

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.view.getjButton1()) {
            try {
                String login = this.view.getTxtLogin().getText();
                String name = this.view.getjTextField1().getText();
                String lastname = this.view.getjTextField2().getText();
                String email = this.view.getjTextField3().getText();
                long cellphone = Long.parseLong(this.view.getjTextField4().getText());
                String password = this.view.getjPasswordField2().getText();
                String date = this.view.getjTextField5().getText();
                ClientModel client = new ClientModel(login, name, lastname, email, cellphone, password, date);
                clientDAO.createClient(client);
                this.view.getTxtLogin().setText("");
                this.view.getjTextField1().setText("");
                this.view.getjTextField2().setText("");
                this.view.getjTextField3().setText("");
                this.view.getjTextField4().setText("");
                this.view.getjTextField5().setText("");
                this.view.getjPasswordField2().setText("");
            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
            }
        }

        if (event.getSource() == this.view.getjButton2()) {
            try {
                String login = this.view.getjTextField6().getText();
                ClientModel client = clientDAO.getClientByLogin(login);
                this.view.getjTextField6().setText("");
                String name = client.getName();
                String lastname = client.getLastname();
                String email = client.getEmail();
                long cellphone = client.getCellphone();
                String date = client.getDate();
                String message = "Los datos del usuario son:\n" + "Nombre: " + name + "\n" + "Apellidos: " + lastname
                        + "\nEmail: " + email + "\nCelular: " + cellphone + "\nFecha: " + date;
                JOptionPane.showMessageDialog(null, message);
            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto.");
            }
        }
        if (event.getSource() == this.view.getjButton4()) {
            try {
                System.out.println("update");
                String login = this.view.getjTextField7().getText();
                String name = this.view.getjTextField8().getText();
                String lastname = this.view.getjTextField9().getText();
                String email = this.view.getjTextField11().getText();
                long cellphone = Long.parseLong(this.view.getjTextField12().getText());
                String password = this.view.getjPasswordField3().getText();
                String date = this.view.getjTextField14().getText();
                ClientModel client = new ClientModel(login, name, lastname, email, cellphone, password, date);
                clientDAO.updateClient(client);
                this.view.getjTextField7().setText("");
                this.view.getjTextField8().setText("");
                this.view.getjTextField9().setText("");
                this.view.getjTextField11().setText("");
                this.view.getjTextField12().setText("");
                this.view.getjTextField14().setText("");
                this.view.getjPasswordField3().setText("");
            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, "Ingrese los datos correctamente");
            }
        }
        if (event.getSource() == this.view.getjButton3()) {
            try {
                String login = this.view.getjTextField10().getText();
                clientDAO.deleteClient(login);
                this.view.getjTextField10().setText("");
                JOptionPane.showMessageDialog(null, "El usuario " + login + " se ha eliminado");
            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto.");
            }
        }
    }
}