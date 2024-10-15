package View;

import Controller.VerThread;

public class Main 
{

	public static void main(String[] args) 
	{
		for( int idthread =0 ; idthread < 5; idthread++)
		{
			Thread T =   new VerThread (idthread);
			T.start();
		}

	}

}
