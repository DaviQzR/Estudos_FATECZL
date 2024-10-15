package View;

import javax.swing.JOptionPane;

import Controller.SomaNaturais;

public class Main {

	public static void main(String[] args) 
	{
		 int n = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor"));
		 SomaNaturais SomaRec = new SomaNaturais();
		 //Metodo para calcular o somatório
		 int soma = SomaRec.somaNaturais(n);
		 System.out.println("O somatório dos " + n + " primeiros números é " + soma);
	}
}
