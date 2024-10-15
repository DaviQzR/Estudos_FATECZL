package View;

import java.util.concurrent.Semaphore;

import Controller.ServidorBD;

public class Main 
{

	public static void main(String[] args)
	{
		int permissoes=1;
		Semaphore semaforo = new Semaphore(permissoes);
		for (int i=0 ; i <= 21; i++)
		{
			Thread calc = new ServidorBD(i, semaforo);
			calc.start();
		}
	}

}
