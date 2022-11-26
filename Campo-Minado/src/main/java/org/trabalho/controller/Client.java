package org.trabalho.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private Socket cliente;
    private String ip;
    private int porta;

    public Client(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
        try {
            cliente = new Socket(ip, porta);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
            objectOutputStream.flush();
            objectOutputStream.writeObject(new InitGame().init());
            objectOutputStream.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
