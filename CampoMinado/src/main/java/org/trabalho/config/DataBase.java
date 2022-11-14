package org.trabalho.config;

import org.trabalho.models.Nivel;
import org.trabalho.models.Pontuacao;
import org.trabalho.models.Users;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private String URL_DB = "jdbc:mysql://localhost:3306/campoMinado";
    private Connection connect = null;


    public DataBase() {
        try {
            connect = DriverManager.getConnection(URL_DB, "root", "admin");
            System.out.println("Conexão bem sucedida com o banco.");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insereJogador(Users u) {
        try {
            String nome = u.getName();

            String sql = "INSERT INTO jogo(nome, pontuacao) VALUES (?, ?)";

            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, 0);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inserePontos(Pontuacao p) {
        try {
            int pontos = p.getPontos();

            String sql = "SELECT jogo(pontucao) SUM(?) as pontuacao";

            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, pontos);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
