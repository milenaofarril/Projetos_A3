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
	private InterfaceEntradaESaida entradaESaida;
	private DataBase base;
	private Nivel nivel = new Nivel();
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				String resposta = entradaESaida.obterResposta();
                               
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
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
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");


			if(nivel.getNivel().equals("1")){
				Pontuacao p = new Pontuacao(5);
				base.inserePontos(p);
			} else if (nivel.getNivel().equals("2")) {
				Pontuacao p = new Pontuacao(10);
				base.inserePontos(p);
			} else if (nivel.getNivel().equals("3")) {
				Pontuacao p = new Pontuacao(15);
				base.inserePontos(p);
			}

		} catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		Scanner entrada = new Scanner(System.in);
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
