package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	String cpf;
	String ra;
	String nome;
	String nomeSocial;
	String dataNasc;
	String telefoneCelular;
	String telefoneResidencial;
	String emailPessoal;
	String emailCorporativo;
	String dataSegundoGrau;
	String instituicaoSegundoGrau;
	double pontuacaoVestibular;
	int posicaoVestibular;
	String anoIngresso;
	String semestreIngresso;
	String semestreGraduacao;
	String anoLimite;
	Curso curso;
	String turno;
}
