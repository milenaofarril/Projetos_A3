package org.trabalho.config;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable{
    private ServerSocket servidor;
    private Socket conectorRecepcao;

    public Servidor(int porta){
        try {
            this.servidor = new ServerSocket(porta);
            System.out.println("Servidor criado");
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void aceitarConexoes(){
        try {
            System.out.println("Esperando conexoes");
            conectorRecepcao = servidor.accept();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        aceitarConexoes();
    }
}