/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.iniciosesion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author María José
 */
public class Server {
    private static final Map<String, String> userDatabase = new HashMap<>();
    private static final int port = 12345;
    private static final String baseDeDatos = "usuario.txt";
    
    public static void main(String[] args) {
        cargarBaseDeDatos();
        iniciarServidor();
    }

    private static void cargarBaseDeDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(baseDeDatos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    userDatabase.put(username, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
        }
    }

    private static void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("El servidor está activo, adelante");
            ExecutorService executor = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new Manejador(clientSocket, userDatabase));
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}