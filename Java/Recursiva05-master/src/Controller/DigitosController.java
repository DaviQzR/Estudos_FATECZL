package Controller;

public class DigitosController 
{
	public DigitosController()
	{
		super();
	}
	public int DigitosCont(int numero)
	{
		//CONDIÇÃO DE PARADA QUANDO O NUMERO TER APENAS UM DIGITO
		if(numero < 10)
		{
			return 1;
		}
		//REMOVE O ULTIMO DIGITO E SOMA 1 AO RESULTADO DA CHAMADA RECURSIVA
		return 1 + DigitosCont(numero/10);
	}
}

