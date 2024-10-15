package View;

import java.util.Scanner;
import Controller.FilaController;
import br.com.DaviQzR.filaobj.Fila;

public class Main 
{

	public static void main(String[] args) 
	{
		Fila fila = new Fila();
		Fila filaPrioritarios = new Fila();
		Scanner input = new Scanner(System.in);
		FilaController control = new FilaController(fila, filaPrioritarios);
		
	int escolha = 0;
	while(escolha!=9) 
	{
		System.out.println("1 - adicionar senhas\n2 - chamar senhas\n9 - finalizar programa");
		escolha = input.nextInt();
		switch(escolha) {
			case 1 : control.AdicionarSenha();
			break;
			case 2 : control.ChamarSenha();
			break;
			case 9 : System.out.println("fim");
			break;
			default : System.out.println("opcao invalida");
			break;
			}
		}	
	}
}