package Model;

public class Aviao {
	
	//Atributos
	public int tamanho ;
	public float peso;
	public String cor;
	public float velocidade;
	public int capacidade;
	
	 //Construtor
	//public Aviao () {
	//	this.tamanho = tamanho ;
	//	this.peso = peso ;
	//	this.cor = cor;
	//	this.velocidade = velocidade;
	//	this.capacidade = capacidade;
	//}
	
	//Métodos
	public void voar () {
		System.out.println("Aviao esta voando...");
	}
	
	public void comunicar () {
		System.out.println("O Aviao esta comunicando com a torre de controle...");
	}
	
	public void desacelerar () {
		System.out.println("O Aviao esta desacelerando na pista... ");
	}
	
	public void pousar() {
		System.out.println("O Aviao esta aterrissando... ");
	}
	
	
	
	public String toString() {
		return "Aviao [tamanho=" + tamanho + ", peso=" + peso + ", cor=" + cor + ", velocidade=" + velocidade + ", capacidade="
				+ capacidade + "]";
	}
	
}
