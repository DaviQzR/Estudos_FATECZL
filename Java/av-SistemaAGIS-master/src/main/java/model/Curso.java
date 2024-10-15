package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
	int codigo;
	String nome;
	int cargaHoraria;
	String sigla;
	int notaEnade;
}
