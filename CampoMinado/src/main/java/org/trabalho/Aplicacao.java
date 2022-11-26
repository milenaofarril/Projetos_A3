package org.trabalho;

import org.trabalho.controller.ServerInit;

public class Aplicacao {

	public static void main(String[] args) {
		ServerInit serverInit = new ServerInit();
		serverInit.name();
		serverInit.init();
	}
}
