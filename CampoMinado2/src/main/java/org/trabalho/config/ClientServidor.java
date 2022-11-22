package org.trabalho.config;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientServidor {
    private Socket cliente;
    private String ip;
    private int porta;

    public ClientServidor(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
        try {
            cliente = new Socket(ip, porta);
            System.out.println("Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(ClientServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
