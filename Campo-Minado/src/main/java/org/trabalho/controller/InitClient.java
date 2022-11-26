package org.trabalho.controller;

import org.trabalho.config.Servidor;

public class InitClient {

    public static void execute(){
        Servidor serv = new Servidor(4000);
        serv.aceitarConexoes();
        Thread tServidor = new Thread(serv);
        tServidor.start();

        Client client = new Client("127.0.0.1", 3000);
    }

    public static void main(String[] args) {
        execute();
    }
}
