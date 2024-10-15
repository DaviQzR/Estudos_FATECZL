package View;
import java.util.concurrent.Semaphore;

import Controller.Banco;


public class Main 
{
	
	public static void main(String[] args) 
	{
		int permissao =1;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore semaforo2 = new Semaphore(permissao);
		for(int i = 0; i < 20 ; i++)
		{
			int opc = (int)((Math.random()*2)+1);
			int idconta = (int)(Math.random()*101);
			double Saldo = (Math.random()*1001);
			double Valor = (Math.random()*501);
			Thread Sistema = new Banco (opc,idconta,Saldo,Valor,semaforo,semaforo2);
			Sistema.start();
		}
	}
}