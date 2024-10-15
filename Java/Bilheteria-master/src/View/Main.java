package View;

import java.util.concurrent.Semaphore;

import Controller.Show;

public class Main 
{

	public static void main(String[] args)
	{
		Semaphore semaforo = new Semaphore(1);
		for(int i = 1; i <= 300; i++)
		{
			Thread bilheteria = new Show (i, semaforo);
			bilheteria.start();
		}
	}

}
