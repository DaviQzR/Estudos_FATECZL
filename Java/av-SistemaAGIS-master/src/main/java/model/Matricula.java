package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matricula {
	int codigo;
	String alunoRa;
	String turno;
	String dataMatricula;
	int codigoCurso;
}
