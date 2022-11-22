package org.trabalho.controller;

import org.trabalho.config.ClientServidor;
import org.trabalho.config.Servidor;

public class InitClient {

    public static void execute(){
        System.out.println("Executando programa B");
        Servidor serv = new Servidor(4000);
        serv.aceitarConexoes();
        Thread tServidor = new Thread(serv);
        tServidor.start();

        ClientServidor client = new ClientServidor("127.0.0.1", 3000);
    }

    public static void main(String[] args) {
        execute();
    }
}
