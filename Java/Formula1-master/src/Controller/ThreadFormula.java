package Controller;
import View.Formula1;
import java.util.concurrent.Semaphore;

public class ThreadFormula extends Thread
{
	private int idEscuderia;
	private Semaphore semaforoLargada;
	private Semaphore semaforoEscuderia;
	public static int carrosForaPista = 0;
	//-----------------------------------
	public ThreadFormula(int id, Semaphore semaforoLargada, Semaphore semaforoEscuderia)
	{
		this.idEscuderia = id;
		this.semaforoLargada = semaforoLargada;
		this.semaforoEscuderia = semaforoEscuderia;
	}
	public void run ()
	{
		for(int i = 1; i < 3; i++)
		{
			try
			{
				semaforoLargada.acquire();
				CarroAndando(i);
			}catch (InterruptedException e)
			  {
				e.printStackTrace();
			  }finally
			     {
				   semaforoLargada.release();
				   System.out.println("O carro "+ i + " Da Escuderia "+ idEscuderia + " saiu da pista");
				   carrosForaPista++;
			     }
		}
		if(carrosForaPista ==14)
		{
			OrdenaGrid();
		}
	}
	private void CarroAndando(int carro)
	{
		System.out.println("O carro " + carro + " Da Escuderia " + idEscuderia + " entrou na pista");
		for( int i = 1; i < 4; i++)
		{
			int tempoVolta = (int)((Math.random()*100)+60);
			try
			{ 
				sleep(tempoVolta * 30);
				
			}catch (InterruptedException e)
		 	 {
				e.printStackTrace();
		 	 }
			System.out.println("Escuderia: " + idEscuderia + " Carro: " + carro + " Volta: " + i + " Tempo: "
					+ tempoVolta + " segundos");
			try 
			{
				
				semaforoEscuderia.acquire();
				if (tempoVolta < Formula1.valorVoltas[(2 * idEscuderia) - carro]
						|| Formula1.valorVoltas[(2 * idEscuderia) - carro] == 0) 
				{
					Formula1.valorVoltas[(2 * idEscuderia - 2 + carro) - 1] = tempoVolta;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally 
			  {
				semaforoEscuderia.release();
			 }
		}
//		System.out.println(Formula1.valorVoltas[(2 * idEscuderia - 2 + carro) - 1])
	}
	public void OrdenaGrid()
	{
		int aux;
		String auxiliar;
		for(int i = 0; i <13; i++)
		{
			for(int j = i + 1; j < 14; j++)
			{
				if(Formula1.valorVoltas[i] > Formula1.valorVoltas[j])
				{
					aux = Formula1.valorVoltas[i];
					Formula1.valorVoltas[i]= Formula1.valorVoltas[j];
					Formula1.valorVoltas[j] = aux;
					auxiliar = Formula1.textoVoltas[i];
					Formula1.textoVoltas[i]= Formula1.textoVoltas[j];
					Formula1.textoVoltas[j]= auxiliar;
				}
			}
		}
		for(int i = 0; i < 14; i++)
		{
			System.out.println("Posição " + (i + 1) + ": " + Formula1.textoVoltas[i] + Formula1.valorVoltas[i] + " segundos");
		}
	}
}