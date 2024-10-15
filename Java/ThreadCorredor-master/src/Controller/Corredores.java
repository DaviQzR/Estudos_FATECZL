package Controller;

import java.util.concurrent.Semaphore;

public class Corredores extends Thread
{
	private static int posSaida;
	private int idpessoa;
	Semaphore semaforo;
	public Corredores (int idpessoa, Semaphore semaforo)
	{
		this.idpessoa= idpessoa;
		this.semaforo = semaforo;
	}
	public void run ()
	{
		Andando();
		//----> Inicio da Sessão Critica<----
		try {
			semaforo.acquire();
				Porta();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}finally
		 {
			semaforo.release();
		 }
		//---->Fim da Sessãp Critica<----
		LiberaPorta();
	}
	private void Andando() 
	{
		int DistanciaTotal= 200;
		int DistanciaPercorrida = 0;
		int Passo= (int)((Math.random()*3)+4);
		int Tempo = 1000;
		while(DistanciaPercorrida < DistanciaTotal)
		{
			DistanciaPercorrida += Passo; 
			try 
			{
				sleep(Tempo);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			System.out.println("A Pessoa "+idpessoa + " Ja Andou " + DistanciaPercorrida +" M.");
		}
		
	}
	private void Porta() 
	{
		System.out.println("!! A pessoa " +idpessoa + " Chegou a porta ");
		int AbrirPorta = (int)((Math.random()*1000)+1001);
		try 
		{
			sleep(AbrirPorta);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	private void LiberaPorta() 
	{
		posSaida++;
		System.out.println("# A Pessoa " +idpessoa  +" Foi o(a)" + posSaida +"° a Atravessar");
	}
}
