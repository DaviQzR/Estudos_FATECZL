package View;

import model.Lista;

public class Principal 
{

	public static void main(String[] args) 
	{
		Lista list = new Lista();
		
		list.addFirst(3);
		
		try 
		{
			list.addLast(5);
			list.addLast(18);
			list.addLast(12);
			list.addLast(9);
			list.addLast(7);
			list.addLast(6);
			list.addLast(2);
			list.addLast(13);
			list.addLast(4);
			list.addLast(16);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		int tamanho = list.size();
		while(tamanho > 0)
		{
			try 
			{
				int valor = list.get(tamanho - 1);
				System.out.println(valor);
				tamanho --;
			} catch (Exception e) 
				{
					e.printStackTrace();
				}
		}
		
	}

}
