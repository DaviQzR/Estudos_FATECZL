package Controller;

public class SomaNaturais 
{
	public int somaNaturais(int n)
	{
		//CONDIÇÃO DE PARADA PARA RETORNAR 0 PARA N NEGATIVOS
		if( n < 0)
		{
			return 0;
		}
		//CONDIÇÃO DE PARADA PARA RETORNAR 0 PARA 0 
		else
			if(n ==0)
			{
				return 0;
			}
		//CHAMA A FUNÇÃO RECURSIVAMENTE COM N-1
		return n + somaNaturais(n-1);
	}

}
