/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.iniciosesion;
//IMPORT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author María José
 */
public class InicioSesion {
    //ATRIBUTOS
    private static final String SERVER_ADDRESS = "localhost"; 
    private static final int PORT = 12345; 
    private static JFrame mainFrame;
    private static JPanel loginPanel;
    private static JPanel welcomePanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        //OBJETOS
    mainFrame = new JFrame("Binvenido al Inicio de Sesión");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(300, 200);
    mainFrame.setLayout(new CardLayout());

    loginPanel = new JPanel();
    loginPanel.setLayout(new GridLayout(3, 2));

    JLabel usernameLabel = new JLabel("Ingrese su usuario:");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Ingrese su contraseña:");
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Iniciar sesión");

    loginPanel.add(usernameLabel);
    loginPanel.add(usernameField);
    loginPanel.add(passwordLabel);
    loginPanel.add(passwordField);
    loginPanel.add(loginButton);

    welcomePanel = new JPanel();
    welcomePanel.setLayout(new BorderLayout());

    JLabel welcomeLabel = new JLabel("¡Bienvenido!");
    JButton logoutButton = new JButton("Cerrar sesión");
    JButton exitButton = new JButton("Salir");

    welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    buttonPanel.add(logoutButton);
    buttonPanel.add(exitButton);
    welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

    cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
    mainFrame.add(loginPanel, "login");
    mainFrame.add(welcomePanel, "welcome");

    cardLayout.show(mainFrame.getContentPane(), "login");
    mainFrame.setVisible(true);

    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            
        if (username.equals("Majo") && new String(passwordChars).equals("12345678")) { 
            JOptionPane.showMessageDialog(mainFrame, "Login confirmed.");
            cardLayout.show(mainFrame.getContentPane(), "welcome"); 
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

    logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mainFrame.getContentPane(), "login");
        }
    });
}    
}
       
