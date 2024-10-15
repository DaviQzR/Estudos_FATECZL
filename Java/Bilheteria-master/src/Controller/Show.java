package Controller;

import java.util.concurrent.Semaphore;

public class Show  extends Thread
{
	private int idPessoa;
	Semaphore semaforo;
	private int Ingressos = 100;
	
	
	public Show(int idPessoa, Semaphore semaforo)
	{
	 this.idPessoa = idPessoa;
	 this.semaforo = semaforo;
	}
	public void run ()
	{
		LoginSistema();

	}
	private void LoginSistema() 
	{
		int tempo = (int) ((Math.random() * 1951) + 50);
		try {
			sleep(tempo);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		if (tempo > 1000) 
		{
		
			System.out.println("!!O Usuario de ID " +idPessoa + " Recebeu um TIMEOUT ao fazer login após " + tempo/1000 +"s. e nao podera fazer a compra");
		}else
		 {
		 	System.out.println("##O Usuario de ID " +idPessoa + " Conseguiu fazer o login no sistema após "+tempo +"ms.");
		 	ProcessoCompra();
		 } 
	}
	
	private void ProcessoCompra() 
	{
		int tempo = (int)((Math.random()*2001)+1000);
		try 
		{
			sleep(tempo);
		} catch (InterruptedException e) 
		  {
			e.printStackTrace();
		  }
		if(tempo >= 2500)
		{
			System.out.println("!!O Usuario " +idPessoa + " Teve sua sessão finalizada por ultrapassar o tempo de " +tempo /1000 + "s.");
		}else
		 {
			System.out.println("O Processo de compra do Usuario " +idPessoa + " esta a caminho da validação");
			try 
			{
				semaforo.acquire();
				Validacao();
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}finally
			 {
				semaforo.release();
			 }
		 }
	}
	private void Validacao() 
	{
		int compra = (int) ((Math.random() * 4) + 1);
		Ingressos -= Ingressos ;
		if(Ingressos >= 0)
		{
			System.out.println("##O Usuario " +idPessoa + " Conseguiu comprar "+ compra + " Ingressos ");
		}else
		 {
			System.out.println("!!O Usuario " +idPessoa + " Não conseguiu comprar " + compra +" Ingressos");
		 }	
	}
}
