package org.trabalho.config;

import org.trabalho.controller.InitGame;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
            System.out.println(nomeCliente + " Conseguiu conectar");
//            try {
//                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
//                saida.flush();
//                saida.writeObject(new Date());
//                saida.close();
//                cliente.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        } else {
            System.out.println("Foi mal, esperei o que deu...");
        }
    }

    public void game(){
        new InitGame().init();
    }

}
