package View;

import Controller.SomaVetorController;

public class Principal 
{

	public static void main(String[] args) 
	{
		int[] vetor = {1, 7, 4, 8, 2, 3};
		SomaVetorController SomaVetorController = new SomaVetorController();
		int tamanho = vetor.length;
		int soma = SomaVetorController.somaVetor(vetor, tamanho);
		System.out.println("A soma do vetor eh: " +soma);
	}

}
