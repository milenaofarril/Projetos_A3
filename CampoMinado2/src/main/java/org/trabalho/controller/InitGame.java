package org.trabalho.controller;

import org.trabalho.config.DataBase;
import org.trabalho.modelo.Tabuleiro;
import org.trabalho.models.Nivel;
import org.trabalho.models.Users;
import org.trabalho.visao.InterfaceEntradaESaida;
import org.trabalho.visao.TabuleiroConsole;

public class InitGame {

    private InterfaceEntradaESaida entradaESaida;
    private DataBase dataBase;

    public Object init() {
        entradaESaida = new InterfaceEntradaESaida();

        Nivel nivel = entradaESaida.leNivel();

            switch (nivel.getNivel()) {
                case "1":
                    Tabuleiro tabuleiro = new Tabuleiro(3, 3, 1);
                    new TabuleiroConsole(tabuleiro);
                    break;
                case "2":
                    Tabuleiro tabuleiroMedio = new Tabuleiro(6, 6, 3);
                    new TabuleiroConsole(tabuleiroMedio);
                    break;
                case "3":
                    Tabuleiro tabuleiroDificil = new Tabuleiro(10, 10, 6);
                    new TabuleiroConsole(tabuleiroDificil);
                    break;
                default:
                    System.out.println("Nivel invalido");
            }

        return null;
    }

    public void name(){
        dataBase = new DataBase();
        entradaESaida = new InterfaceEntradaESaida();
        Users usuario = entradaESaida.leUsuarios();
        dataBase.insereJogador(usuario);
    }
}
