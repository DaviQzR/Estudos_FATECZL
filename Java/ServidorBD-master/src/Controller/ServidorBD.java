package Controller;

import java.util.concurrent.Semaphore;

public class ServidorBD extends Thread
{
	private int Idthread;
	private Semaphore semaforo;

	public ServidorBD (int Idthread, Semaphore semaforo) 
	{
		this.Idthread = Idthread;
		this.semaforo = semaforo;
		
	}
 @Override
  public void run() 
  {

	 try 
	 {
		 semaforo.acquire();
		
		 TransacaoBD();
	 }catch (Exception e) 
	  {
		 e.printStackTrace();
	  }finally 
	    {
		 semaforo.release();
	   } 
	
  }
 private void TransacaoBD() 
 {
	for (int i=0;i<2;i++) 
	{
	  if(Idthread % 3 ==1 ) 
	  {
		Calculo();
			
		int tempo =  gerar(1001,0);
		
		System.out.println("Thread = "+getId()+ " Transacao de BD ");
	  }
	}if(Idthread % 3 ==2) 
	{
		for (int i=0;i<3;i++) 
		{
			Calculo();
		 int tempo =  gerar(1500,0);;
		 System.out.println("Thread = "+getId()+ " Transacao de BD ");
		}
	}else 
		if(Idthread % 3 ==0)
		{
		  for (int i=0;i<2;i++) 
		  {
			Calculo();
			int tempo =  gerar(1500,0);;
			System.out.println("Thread = "+getId()+ " Transacao de BD ");
		  }
		}
  } 

  private void Calculo() 
  {
	
	if(Idthread % 3 ==1 ) 
	{
		
		int tempo = gerar(200,800);
		System.out.println("Thread = "+getId()+ " Calculos ");
	
	}if(Idthread % 3 ==2) 
	  {
		
		int tempo =  gerar(500,1000);
		System.out.println("Thread = "+getId()+ " Calculos ");
		
	  }else 
		  if(Idthread % 3 ==0) 
		  {
		
			  int tempo =  gerar(1001,2000);
			  System.out.println("Thread = "+getId()+ " Calculos ");
		  }
   }
	private int gerar(int min, int max) 
	{
		int gerar = min + (int)(Math.random()*(max -min));
		return gerar;
	}
}