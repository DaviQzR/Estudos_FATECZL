package Controller;
import java.util.concurrent.Semaphore;
public class Airplanes extends Thread
{
	private int idAviao;
	private  Semaphore Pista;
	private Semaphore PistaSul;
	private Semaphore PistaNorte;
	
	public Airplanes (int idAviao, Semaphore Pista,Semaphore PistaSul, Semaphore PistaNorte )
	{
		this.idAviao = idAviao;
		this.Pista = Pista;
		this.PistaSul = PistaSul;
		this.PistaNorte = PistaNorte;
		
	}
	public void run ()
	{
		try
		{
			Pista.acquire();
			Manobrando();
			Taxiando();
			int pista = (int)((Math.random()*2)+1);
			if(pista == 1)
			{
				PistaNorte.acquire();
				Decolando(pista);
			}else
			  {
				
			     PistaSul.acquire();
				Decolando(pista);
			  }
				Afastamento();
		}catch (InterruptedException e)
		  {
			e.printStackTrace();
		  }finally 
		     {
			  	System.out.println("#O Avião "+idAviao+" se afastou da área");
				Pista.release();
				PistaSul.release();
				PistaNorte.release();
		     }
		
	}
	private void Manobrando() 
	{
		System.out.println("#O Avião " + idAviao + " Esta manobrando ");
		int tempo = (int)((Math.random()*3)+5);
		try 
		{
			sleep(tempo);
		} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
	}
	private void Taxiando() 
	{
		System.out.println("#O Avião " + idAviao + " Esta Taxiando");
		int tempo = (int)((Math.random()*6)+5);
		try 
		{
			sleep(tempo );
		} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		
	}
	private void Decolando(int Pista) 
	{
		if(Pista == 1 )
		{ 
			System.out.println("--->O Avião " +idAviao + " Esta decolando pela pista Norte");
		}else
		{
			System.out.println("--->O Avião "+idAviao+" está decolando pela pista Sul");
		}
		int tempo = (int)((Math.random()*4)+1);
		try {
			sleep(tempo*1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	private void Afastamento() 
	{
		int tempo = (int)((Math.random()*6)+3);
		try 
		{
			sleep(tempo);
		} catch (InterruptedException e) 
		  {
			e.printStackTrace();
		  }
	}
}
