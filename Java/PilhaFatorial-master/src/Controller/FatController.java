package Controller;
 import br.com.DaviQzR.pilhaint.Pilha;

public class FatController 
{
	public void fat (int valor)
	{
		Pilha PilhaA = new Pilha();
		int fat = 1;
		
		while (valor > 0)
		{
			PilhaA.push(valor);
			valor -=1 ;
			
		}
		
		while (!PilhaA.isEmpty())
		{
			try 
			{
				fat *= PilhaA.pop();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println(fat);
		
	}
}
