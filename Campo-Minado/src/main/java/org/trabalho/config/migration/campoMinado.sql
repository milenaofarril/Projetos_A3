CREATE DATABASE campoMinado;

use campoMinado;

CREATE TABLE jogo(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    pontuacao INT NOT NULL
);