package View;
import Controller.MatrizThread;
public class Main 
{

	public static void main(String [] args) 
	{
		for( int i=0; i<3;i++)
		{
			Thread t = new MatrizThread(i);
			t.start();
		}
	}
}



