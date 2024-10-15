/*Fazer uma aplicação que rode 5 Threads que
cada uma delas imprima no console o seu número.
* 
*/
package Controller;
public class VerThread extends Thread 
{
	private int IDThread;
	public VerThread (int IDThread)
	{
		// Serve para apontar a variavel que vai receber um valor
		this.IDThread = IDThread;
	}
	public void run()
	{
		//Executa o que estiver aqui
		System.out.println("TID = " + getId() + "----> " + IDThread);
	}
}
