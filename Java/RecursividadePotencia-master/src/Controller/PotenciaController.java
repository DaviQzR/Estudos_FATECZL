package Controller;

public class PotenciaController 
{
	public PotenciaController()
	{
		super();
	}
	public int Potencia (int base, int expoente)
	{
		//Condição de Parada
		if (expoente == 0)
		{
			return 1;
		}else
		 {
			expoente -= 1;
			//Chamada da Função
			return base * Potencia(base,expoente);
		 }
	}
}
