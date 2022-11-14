package org.trabalho.visao;

import org.trabalho.config.DataBase;
import org.trabalho.models.Nivel;
import org.trabalho.models.Users;

import java.util.Scanner;

public class InterfaceEntradaESaida {



    public Users leUsuarios() {

        Scanner scn = new Scanner(System.in);

        System.out.println("Qual é seu nome?");
        String name = scn.nextLine();

        Users usuarios = new Users(name);
        return usuarios;
    }

    public Nivel leNivel(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Nivel 1: 3x3\nNivel 2: 6x6\nNivel 3: 10x10");

        System.out.println("Qual nivel do jogo?");
        String nivelSelecionado = scn.nextLine();

        Nivel nivel = new Nivel(nivelSelecionado);
        return nivel;
    }
}
