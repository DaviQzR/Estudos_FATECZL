package View;

import java.util.concurrent.Semaphore;

import Controller.Airplanes;

public class Main 
{

	public static void main(String[] args) 
	{
	 int permissoes=2;
	 Semaphore pista=new Semaphore(permissoes);
	 Semaphore sul= new Semaphore(1);
	 Semaphore norte=new Semaphore(1);
	 
	 for (int idAviao=0; idAviao<12; idAviao++) 
	 {
		Thread Aeroporto = new Airplanes(idAviao, pista, sul,norte);
		Aeroporto.start();
		
	 }

	}

}
