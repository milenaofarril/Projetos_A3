package org.trabalho.controller;

import org.trabalho.config.Servidor;

public class InitServidor {
    public static void executar(){
        Servidor serv = new Servidor(3000);
        Thread tServidor = new Thread(serv);
        tServidor.start();

        ServidorClient client = new ServidorClient("Client", "127.0.0.1", 4000);
        client.conectar();
    }

    public static void main(String[] args) {
        InitServidor init = new InitServidor();
        init.executar();
    }
}
