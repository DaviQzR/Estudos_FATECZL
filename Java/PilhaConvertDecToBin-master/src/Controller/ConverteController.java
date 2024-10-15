package Controller;

import br.com.DaviQzR.pilhaint.Pilha;

public class ConverteController 
{

	public void DecTobin(int var) 
	{
		
		Pilha pilha=new Pilha(); 
		
		while((double)var>0) 
		{ 
					pilha.push(var % 2); 
			var /= 2; 
		}
		System.out.println("Valor convertido com SUCESSO ");
		while(!pilha.isEmpty()) 
		{
			try 
			{
				System.out.println(String.valueOf(pilha.pop()));
			} catch (Exception e) 
			   {
				e.printStackTrace();
			  }
		}
	}
}