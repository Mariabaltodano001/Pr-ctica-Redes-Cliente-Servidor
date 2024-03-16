/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iniciosesion;
//IMPORT
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
/**
 *
 * @author María José
 */
//THREATS
class Manejador implements Runnable {

    private final Socket clientSocket; //Representa cliente
    private final Map<String, String> userDatabase; //Representa la base de datos de usuarios 
    
//Constructor  
    public Manejador(Socket socket, Map<String, String> userDatabase) {
        this.clientSocket = socket;
        this.userDatabase = userDatabase;
    }
//Metodo run asociado a la instancia de manejador
    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String username = in.readLine();
            String password = in.readLine();

            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                out.println("Login confirmed.");
            } else {
                out.println("Login failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}