package View;

import javax.swing.JOptionPane;

import Controller.DivisaoController;

public class Principal 
{

	public static void main(String[] args) 
	{
		DivisaoController DivisaoController = new DivisaoController();
		int dividendo = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor para o dividendo"));
		int divisor = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor para o divisor"));
		int Resultado = DivisaoController.divisao(dividendo,divisor);
		System.out.println("O resultado eh: " + Resultado);
	}

}
