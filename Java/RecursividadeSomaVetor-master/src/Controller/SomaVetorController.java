package Controller;

public class SomaVetorController 
{

	public SomaVetorController() 
	{
		super();
	}
	public int somaVetor(int []vetor, int tamanho)
	{
		//Condição de Parada
		if (tamanho ==0)
		{
			return 0;
		}else 
		 {
			int ultimaPosicao = tamanho - 1;
			int valor = vetor[ultimaPosicao];
			tamanho -= 1;
			return valor + somaVetor(vetor, tamanho);
		 }
	}
}
