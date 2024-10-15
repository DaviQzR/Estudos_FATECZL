package Controller;

import java.util.concurrent.Semaphore;

public class CozinhaGame extends Thread
{
	int Pratos;
	Semaphore semaforo;
	public CozinhaGame(int Pratos, Semaphore semaforo)
	{
		this.Pratos= Pratos;
		this.semaforo= semaforo;
	}
	public void run()
	{
		NomePrato();
		Entrega();
	}
	private void NomePrato()
	{
		if(Pratos %2 ==0)
		{
			LasanhaBolonhesa();
		}else
		 {
			SopaCebola();
			
		}
	}
	private void SopaCebola()
	{
		double total=0;
		String Prato1 = "Sopa de Cebola";
		double tempoTotal= (double)(Math.random()*301.0)+500.0;
		System.out.println("O " + Pratos + " ° prato com sabor " + Prato1 + " Foi iniciado");
		double tempo= (tempoTotal /100);
		double Quant = 100 / tempo;
		for (int i = 1; i <= (int)tempo; i++)
		{
			try 
			{
				sleep(100);
				System.out.printf("O " + Pratos + "  ° Prato com sabor " + Prato1 + " Esta em: %.2f %n",(total = total + Quant));
			}catch(InterruptedException e)
			 {
				e.printStackTrace();
			 } 
		}
		System.out.println("O " + Pratos +"° Prato com sabor " + Prato1 + " Esta Pronto");
	}
	private void LasanhaBolonhesa() 
	{
		double total = 0;
		String prato2 = "Lasanha a Bolonhesa";
		int tempototal = (int) (Math.random() * 601 ) + 600;
		System.out.println("O " + Pratos + "º Prato com sabor " + prato2 + " Foi Iniciado" );
		double tempo = (tempototal / 100);
		double quant = 100 / tempo;
		for(int i = 1; i <= (int)tempo; i++) 
		{
			try 
			{
				sleep(100);
				System.out.printf("O " + Pratos + "º Prato com sabor " + prato2 + " esta em: %.2f %n", (total = total + quant ));
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("O " + Pratos + "º Prato com sabor " + prato2 + " esta PRONTO");
	}
	private void Entrega()
	{
		try
		{
			semaforo.acquire();
			sleep(500);
			System.out.println("O " +Pratos + "º Foi Entregue");
		}catch (Exception e)
		 {
			e.printStackTrace();
		 }finally
		   {
			 semaforo.release();
		   }
	} 
}
