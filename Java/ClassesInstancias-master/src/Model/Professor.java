package Model;

public class Professor {

	 //Atributos
	public String nome;
	public String formacao;
	public int idade;
	public String materia;
	public int  cfep ;
	
	//Construtor 
	//public Professor () {
		//this.nome = nome;
		//this.formacao = formacao;
		//this.idade = idade;
		//this.materia = materia;
		//this.cfep = cfep;
	//}
	
	//Métodos
	
	public void comunicar () {
		System.out.println("Boa Tarde TURMA!!!...");
	}
	
	public void andar () {
		System.out.println("O professor esta circulando pela sala...");
	}
	
	public void aula () {
		System.out.println("O assunto de hoje eh...");
	}
	
	public String toString() {
		return "Professor [nome=" + nome + ", formacao=" + formacao + ", idade=" + idade + ", materia=" + materia + ", cfep="
				+ cfep + "]";
	}
}
