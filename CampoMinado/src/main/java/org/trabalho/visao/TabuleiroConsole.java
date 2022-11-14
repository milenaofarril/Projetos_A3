package org.trabalho.visao;

import org.trabalho.config.DataBase;
import org.trabalho.excecao.ExplosaoException;
import org.trabalho.excecao.SairException;
import org.trabalho.modelo.Tabuleiro;
import org.trabalho.models.Nivel;
import org.trabalho.models.Pontuacao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	private InterfaceEntradaESaida entradaESaida;
	private DataBase base;
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();


				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			entradaESaida = new InterfaceEntradaESaida();
			base = new DataBase();
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
			}

			Nivel nivel = entradaESaida.leNivel();
			switch (nivel.getNivel()) {
				case "1":
					Pontuacao p = new Pontuacao(5);
					base.inserePontos(p);
					break;
				case "2":
					Pontuacao p2 = new Pontuacao(10);
					base.inserePontos(p2);
					break;
				case "3":
					Pontuacao p3 = new Pontuacao(15);
					base.inserePontos(p3);
					break;
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
