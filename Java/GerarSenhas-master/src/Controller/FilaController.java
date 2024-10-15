package Controller;

import java.util.Scanner;
import br.com.DaviQzR.filaobj.Fila;

public class FilaController 
{
	Scanner input = new Scanner(System.in);
	Fila fila;
	Fila filaPrioridade;
	private static int id = 0;
	private static int senha = 1;
	private static int senhaP = 1;
	
public FilaController(Fila fila, Fila filaPrioridade) 
{
	this.fila = fila;
	this.filaPrioridade = filaPrioridade;
}

public void AdicionarSenha() 
{
	int escolha = 0;
	while(escolha!=9) 
	{
		System.out.println("1 - adicionar fila normal\n2 - adicionar fila prioritaria\n9 - voltar");			
		escolha = input.nextInt();
		switch(escolha)
		{
			case 1: fila.insert(senha);
				System.out.println(senha+" foi adicionado");
				senha++;
			break;
			case 2: fila.insert(senhaP);
				System.out.println(senhaP+"(prioritario) foi adicionado");
				senhaP++;
			break;
			case 9: System.out.println("voltar");
			break;
			default : System.out.println("opcao invalida");
			break;
		}
	}
}

public void ChamarSenha()
{
	try 
	{
		if(!filaPrioridade.isEmpty()) 
		{
			id++;
			if(id%4==0) 
			{
				System.out.println(fila.remove()+" foi chamado");
			}else 
			 {
				System.out.println(filaPrioridade.remove()+"(prioritario) foi chamado");
			 }
		}else 
		{
			if(!fila.isEmpty()) 
			{
				System.out.println(fila.remove()+" foi chamado");
			}else 
			 {
				System.out.println("Filas vazias ");
			 }
		}
	} catch (Exception e) 
	   {
		// TODO: handle exception
	   }	
}
}

