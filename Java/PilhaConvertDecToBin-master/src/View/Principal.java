package View;

import java.util.Scanner;

import Controller.ConverteController;

public class Principal 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		ConverteController c = new ConverteController();
		int num;
		System.out.println("Insira um valor decimal");
		num=input.nextInt();
		while(num>1000);
		c.DecTobin(num);
		input.close();
	}
}