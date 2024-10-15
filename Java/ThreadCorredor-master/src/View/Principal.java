package View;

import java.util.concurrent.Semaphore;

import Controller.Corredores;

public class Principal 
{

	public static void main(String[] args) 
	{
		int permissoes =3;
		Semaphore semaforo = new Semaphore(permissoes);
		for( int idpessoa =1; idpessoa <= 4; idpessoa++)
		{
			Thread Corredor = new Corredores (idpessoa , semaforo);
			Corredor.start();
		}
	}

}
