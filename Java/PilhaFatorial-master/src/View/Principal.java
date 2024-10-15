package View;

import java.util.Scanner;

import Controller.FatController;

public class Principal 
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		FatController  fatorial = new FatController();
		int valor = 0;
		System.out.println("Escolha um valor entre 0 a 10");
		do
		{
			valor = input.nextInt();
			if(!(valor >= 0 && valor <= 10 ))
			{
				System.out.println("!! Digite um valor dentro do intervalo ");
			}
		}while(!(valor >= 0 && valor <= 10));
		 {
			fatorial.fat(valor);
			input.close();
		 }
		
	}

}
