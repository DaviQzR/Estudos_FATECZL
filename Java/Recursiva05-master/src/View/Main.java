package View;

import javax.swing.JOptionPane;

import Controller.DigitosController;

public class Main {

	public static void main(String[] args) 
	{
		int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o numero"));
		 DigitosController digitos = new  DigitosController();
		 int cont = digitos.DigitosCont(numero);
		 System.out.println("O numero " + numero + " possui " + cont + " Digitos" );
	}
}
