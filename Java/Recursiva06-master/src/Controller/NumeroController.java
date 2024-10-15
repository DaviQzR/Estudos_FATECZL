package Controller;

public class NumeroController 
{
	public NumeroController()
	{
		super();
	}
	public int ContDigito(int numero, int digito)
	{
		//CONDIÇÃO DE PARADA QUANDO O NÚMERO É ZERO
		if(numero == 0)
		{
			return 0;
		}
		else 
		{
			int ultimoDigito = numero % 10;
			int resto = numero /10;
			int quantidade = ContDigito(resto,digito); // CHAMADA DA RECURSIVA
			if (ultimoDigito == digito)
			{
				quantidade ++;
			}
				return quantidade;
		}
	}

}
