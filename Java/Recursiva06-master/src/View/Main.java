package View;

import java.util.Scanner;

import Controller.NumeroController;

public class Main 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite um numero inteiro entre 10 e 999999: ");
		int numero = sc.nextInt();
		while (numero < 10 || numero > 999999)
		{
			 System.out.println("Número inválido. Digite novamente.");
	         System.out.print("Digite um número inteiro entre 10 e 999999: ");
	         numero = sc.nextInt();
		}
		System.out.print("Digite um número inteiro entre 0 e 9: ");
        int digito = sc.nextInt();
        while (digito < 0 || digito > 9) 
        {
            System.out.println("Número inválido. Digite novamente.");
            System.out.print("Digite um número inteiro entre 0 e 9: ");
            digito = sc.nextInt();
        }
        NumeroController control = new NumeroController();
        int quantidade = control.ContDigito(numero,digito);
        System.out.printf("O número %d possui o dígito %d aparecendo %d vezes.", numero, digito, quantidade);
        sc.close();
	}

}
