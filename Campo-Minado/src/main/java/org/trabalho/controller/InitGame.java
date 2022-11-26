package org.trabalho.controller;

import org.trabalho.config.DataBase;
import org.trabalho.modelo.Tabuleiro;
import org.trabalho.modelo.Nivel;
import org.trabalho.modelo.Users;
import org.trabalho.visao.InterfaceEntradaESaida;
import org.trabalho.visao.TabuleiroConsole;

public class InitGame {

    private DataBase dataBase;
    private Nivel nivel;

    public Object init() {

        InterfaceEntradaESaida entradaESaida = new InterfaceEntradaESaida();
        String name = name().getName();
        this.nivel = entradaESaida.leNivel();

            switch (nivel.getNivel()) {
                case "1":
                    Tabuleiro tabuleiro = new Tabuleiro(3, 3, 1);
                    new TabuleiroConsole(tabuleiro, nivel, name);
                    break;
                case "2":
                    Tabuleiro tabuleiroMedio = new Tabuleiro(6, 6, 3);
                    new TabuleiroConsole(tabuleiroMedio, nivel, name);
                    break;
                case "3":
                    Tabuleiro tabuleiroDificil = new Tabuleiro(10, 10, 6);
                    new TabuleiroConsole(tabuleiroDificil, nivel, name);
                    break;
                default:
                    System.out.println("Nivel invalido");
            }

        return null;
    }

    public Users name(){
        dataBase = new DataBase();
        InterfaceEntradaESaida entradaESaida;
        entradaESaida = new InterfaceEntradaESaida();
        Users usuario = entradaESaida.leUsuarios();
        dataBase.insereJogador(usuario);
        return usuario;
    }
}
