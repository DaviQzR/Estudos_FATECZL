package Controller;

public class NegativosController 
{
	public NegativosController()
	{
		super();
	}
	public int Quantidade(int [] vetor, int tamanho, int cont)
	{
		//CONDIÇÃO DE PARADA
		if(tamanho == 0)
		{
			return cont ;
			//se nao tiver nenhum vai retornar
		}else
		 {
			tamanho--;
			if(vetor[tamanho] < 0)
			{
				cont++;
			}
			return Quantidade (vetor, tamanho, cont); // vai percorrer o vetor e a cada valor negativo encontrado vai ser incrementado no contador para saber o total de numeros negativos)
		 }
	}
}
