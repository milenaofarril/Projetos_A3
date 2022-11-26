package org.trabalho.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorClient implements Runnable {

    private ServerSocket servidor;
    private boolean parado = false;
    private Socket conectorRecepcao;
    private HashMap<String, Socket> listaRecepcao = null;

    public ServidorClient(int porta) {
        try {
            this.servidor = new ServerSocket(porta);
            System.out.println("Servidor criado");
            listaRecepcao = new HashMap<String, Socket>();
        } catch (IOException ex) {
            Logger.getLogger(ServidorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aceitarConexoes() {
        try {
            System.out.println("Esperando conexoes");
            conectorRecepcao = servidor.accept();
            InputStream is = conectorRecepcao.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String nome = dis.readUTF();
            System.out.println("Conexao aceita de " + nome);
            listaRecepcao.put(nome, conectorRecepcao);
        } catch (IOException ex) {
            Logger.getLogger(ServidorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parar() {
        parado = false;
    }

    public boolean existemConexoes() {
        return !listaRecepcao.isEmpty();
    }

    public Collection<Socket> listaConexoes() {
        return listaRecepcao.values();
    }

    @Override
    public void run() {
        while (!parado) {
            aceitarConexoes();
        }
    }
}
