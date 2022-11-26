package org.trabalho.visao;

import org.trabalho.modelo.Nivel;
import org.trabalho.modelo.Users;

import java.util.Scanner;

public class InterfaceEntradaESaida {

    private Scanner scn = null;

    public InterfaceEntradaESaida() {
        scn = new Scanner(System.in);
    }

    private Nivel nivel = new Nivel();

    public Users leUsuarios() {

        System.out.println("Qual é seu nome?");
        String name = scn.nextLine();

        Users usuarios = new Users(name);
        return usuarios;
    }

    public Nivel leNivel(){
       
        System.out.println("Nivel 1: 3x3\nNivel 2: 6x6\nNivel 3: 10x10");

        System.out.println("Qual nivel do jogo?");
        String nivelSelecionado = scn.nextLine();
        nivel.setNivel(nivelSelecionado);

        return nivel;
    }
    
    public String obterResposta(){
        System.out.println("Outra partida? (S/n) ");
        return scn.nextLine();
    }
}
