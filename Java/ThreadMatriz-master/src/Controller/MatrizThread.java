/*Fazer uma aplicação que insira números
aleatórios em uma matriz 3 x 5 e tenha 3
chamadas de Threads, onde cada uma calcula a
soma dos valores de cada linha, imprimindo a
identificação da linha e o resultado da soma.
*/
package Controller;
public class MatrizThread extends Thread
{
	int i;
	int vt[]= new int [5];
	int soma = 0;
	public MatrizThread(int i)
	{
		this.i =i;
	}
	public void run ()
	{
		matriz();
	}
	public void matriz()
	{
		for (int e = 0 ; e < 5; e++)
		{
			vt[e]= (int) (Math.random()*10);
			System.out.print(vt[e]+ " ");
			soma = soma + vt[e];
		}
		System.out.println(" Soma das linhas : " +soma);
	}
}