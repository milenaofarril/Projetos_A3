package org.trabalho.config;

import org.trabalho.modelo.Pontuacao;
import org.trabalho.modelo.Users;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private String URL_DB = "jdbc:mysql://localhost:3306/campoMinado";
    private Connection connect = null;


    public DataBase() {
        try {
            connect = DriverManager.getConnection(URL_DB, "root", "admin");
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

    public void inserePontos(Pontuacao p, String nome) {
        try {
            int pontos = p.getPontos();
            String sql = "SELECT nome, SUM(pontuacao + ?) from jogo  WHERE nome = ? group by nome";


            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, pontos);
            stmt.setString(2, nome);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarJogador(String nome){
        try {
            String sqlNome = "SELECT * FROM jogo WHERE nome = ?";
            PreparedStatement stmt = connect.prepareStatement(sqlNome);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nomeBanco = rs.getString("nome");
                int pontuacao = rs.getInt("pontuacao");
                System.out.println("Nome: "+ nomeBanco +"\nPontuação: "+pontuacao);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
