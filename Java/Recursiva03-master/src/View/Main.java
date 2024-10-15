package View;

import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.FatorialController;

public class Main 
{

	public static void main(String[] args)
	{
		int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor"));
		FatorialController fatCont = new FatorialController();
		try {
			int Resultado = fatCont.CalculaFat(numero);
			System.out.println("O fatorial de " + numero + " eh: " + Resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
