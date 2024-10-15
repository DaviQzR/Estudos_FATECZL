package Controller;

public class DivisaoController 
{

	public DivisaoController() 
	{
		super();
	}

	public int divisao(int dividendo, int divisor)
	{
		//Condição de Parada
		if(dividendo < divisor)
		{
			return 0;
		}else
		 {
			dividendo -= divisor;
			return 1 + divisao(dividendo, divisor);
		 }
	}

}
