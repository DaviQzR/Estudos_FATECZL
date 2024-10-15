package View;

import java.util.concurrent.Semaphore;

import Controller.CozinhaGame;

public class Main {

	public static void main(String[] args) 
	{
		Semaphore semaforo = new Semaphore (1);
		for(int Pratos = 1; Pratos <=5; Pratos ++)
		{
			Thread Game = new CozinhaGame(Pratos,semaforo);
			Game.start();
		}
	}

}
