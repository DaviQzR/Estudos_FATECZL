package Model;

public class Bola {
	
	//Atributos
	public String cor ;
	public String formato;
	public float tamanho;
	public String material;
	public float peso;
	
	//Construtor
	//public Bola() {
		//this.cor = cor;
		//this.formato = formato;
		//this.tamanho = tamanho;
		//this.material = material;
		//this.peso = peso;
	//}
	
	//Metodos
	public void pular () {
		System.out.println("A bola esta pulando...");
	}
	
	public void encher () {
		System.out.println("A bola esta enchendo...");
	}
	
	public void chutar () {
		System.out.println("A bola esta sendo chutada...");
	}
	
	public String toString() {
		return "Bola [cor=" + cor + ", formato=" + formato + ", tamanho=" + tamanho + ", material=" + cor + ", peso="
				+ peso + "]";
	}
}
