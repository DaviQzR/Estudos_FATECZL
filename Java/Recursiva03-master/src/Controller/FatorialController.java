package Controller;

import java.io.IOException;

public class FatorialController 
{
	public FatorialController()
	{
		super();
	}
	public int CalculaFat(int numero) throws IOException
	{
		if ( numero < 0 || numero > 12)
		{
			throw new IOException("O numero de entrada deve estar entre 0 e 12! ");
		}else if (numero == 0 || numero == 1)
		{
			//CONDIÇÃO DE PARADA FATORIAL DE 0 E 1 É 1
			return 1;
		}
		//FATORIAL DE N É N * FATORIAL(N - 1)
		return numero *CalculaFat(numero-1);
	}

}
