package org.trabalho.config;

import org.trabalho.controller.InitGame;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorClient {
    private Socket cliente;
    private String ip;
    private int porta;
    private DataOutputStream streamSaida;

    private ObjectOutputStream outputStream;
    private String nomeCliente;
    private int nrTentativas = 30;

    public ServidorClient(String nome, String ip, int porta) {
        this.nomeCliente = nome;
        this.ip = ip;
        this.porta = porta;
    }

    private boolean conectaComServidor() {
        try {
            cliente = new Socket(this.ip, this.porta);
            System.out.println("Socket conectado");
            OutputStream os = cliente.getOutputStream();
            streamSaida = new DataOutputStream(os);
            streamSaida.writeUTF(nomeCliente);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void conectar() {
        boolean conectado = false;
        while (!conectado && nrTentativas > 0) {
            conectado = conectaComServidor();
            nrTentativas--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServidorClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conectado) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream());
                objectInputStream.readObject();
                objectInputStream.close();
                cliente.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(nomeCliente + " Conseguiu conectar");
        } else {
            System.out.println("Foi mal, esperei o que deu...");
        }
    }
}
