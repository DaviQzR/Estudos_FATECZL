package View;

import Controller.CorridaSapo;

public class Main 
{
	 static int NumeroSapos=5;
	 static int Distancia =250;
	public static CorridaSapo corridaSapo;
	

	public static void main(String[] args) 
	{
		
		System.out.println("CORRE SAPINHO CORREEEEEEE ");
		for(int i=1; i<=NumeroSapos; i++) 
		{ 
			 corridaSapo = new CorridaSapo("sapo "+ i, Distancia);
			 corridaSapo.start();
			 
			
		}

	}

}