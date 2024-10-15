package View;

import Controller.VTthread;

public class Main 
{

	public static void main(String[] args) 
	{
int vet[] = new int [1000];
		
		for(int i = 0; i < 1000; i++ ) {
			vet[i] = (int) (Math.random() * 91) + 1;
		}
		
		for(int valor = 1; valor <= 2; valor++) {
			VTthread thread = new VTthread (valor, vet);
			thread.start();
		}
	}

}