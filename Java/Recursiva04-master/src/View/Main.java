package View;

import Controller.NegativosController;

public class Main 
{

	public static void main(String[] args) 
	{
		NegativosController Negativo = new NegativosController();
		int cont = 0;
		int tamanho = (int)(Math.random()* 5) + 2;
		int vetor [] = new int [tamanho];
		System.out.println ("O tamanho do vetor: " + tamanho);
		System.out.print("Vetor: ");
		for (int i = 0 ; i < tamanho; i++)
		{
			int valor = (int)(Math.random()* 21) + (-9);
			vetor[i] = valor ;
			System.out.print(vetor[i]+ " ");
		}
		int Resultado = Negativo.Quantidade(vetor, tamanho, cont);
		System.out.println(" ");
		System.out.println("A somatoria de numeros negativos eh: " +Resultado + " Negativos");
	}

}
