package View;

import javax.swing.JOptionPane;

import Controller.PotenciaController;

public class Principal 
{

	public static void main(String[] args) 
	{
		PotenciaController potenciaController = new PotenciaController();
		int base = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor para a base"));
		int expoente = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor para o expoente"));
		int Resultado = potenciaController.Potencia(base,expoente);
		System.out.println("O resultado eh: " + Resultado);
	}

}
