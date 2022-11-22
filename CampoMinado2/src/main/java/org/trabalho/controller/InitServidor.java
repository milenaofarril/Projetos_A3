package org.trabalho.controller;

import org.trabalho.config.Servidor;
import org.trabalho.config.ServidorClient;

public class InitServidor {
    public static void executar(){
        System.out.println("Executando programa A");
        Servidor serv = new Servidor(3000);
        //serv.aceitarConexoes();
        Thread tServidor = new Thread(serv);
        tServidor.start();

        ServidorClient client = new ServidorClient("Client", "127.0.0.1", 4000);
        client.conectar();
        client.game();
    }

    public static void main(String[] args) {
        InitServidor init = new InitServidor();
        init.executar();
    }
}
