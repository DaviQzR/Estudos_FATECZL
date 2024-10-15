package model;

public class Fila 
{
	No inicio;
	No fim;
	public Fila()
	{
		inicio = null;
		fim = null;
	}
	public boolean isEmpty()
	{
		if (inicio == null && fim == null)
		{
			return true;
		}else
		 {
			return false;
		 }
	}
	public void insert(int valor)
	{
		No elemento = new No();
		elemento.dado = valor;
		
		//Verificar se e o primeiro elemento
		if(inicio ==null)
		{
			inicio = elemento;
			fim = elemento;
			elemento.proximo = null;
		}else
		 {
			//verificar se eh o segundo elemento
			if(inicio == fim && inicio != null)
			{
				fim = elemento;
				inicio.proximo = fim;
				fim.proximo = null;
			}else
			 {
				fim.proximo = elemento;
				elemento.proximo = null;
				fim = elemento;
			 }
		 }
	}
	public int remove() throws Exception
	{
		if (isEmpty())
		{
			throw new Exception("Fila vazia");
		}
		No auxiliar = inicio;
		if (inicio == fim && inicio != null) // Se só TEM 1 elemento
		{
			inicio = null;
			fim = null;
		}else
		 {
			inicio = inicio.proximo;
		 }
		System.out.println("Removido: " +auxiliar.toString());
		System.out.println("Novo valor inicial: " +inicio.dado);
		return auxiliar.dado;
	}
	public void list() throws Exception
	{
		if (isEmpty())
		{
			throw new Exception ("Fila Vazia");
		}
		No auxiliar = inicio;
		System.out.println("===================");
		while (auxiliar != null)
		{
			
			System.out.print(auxiliar.toString()+ "===> ");
			auxiliar = auxiliar.proximo;
		}
		System.out.println("NULL");
		System.out.print("");
		System.out.println("===================");
	}
	public int size()
	{
		int cont = 0;
		if (!isEmpty())
		{
			No auxiliar = inicio;
			while (auxiliar != null)
			{
				cont++;
				auxiliar = auxiliar.proximo;
			}
		}
		return cont;
	}
	@Override
	public String toString() 
	{
		StringBuffer buffer = new StringBuffer();
		No auxiliar = inicio;
		while(auxiliar != null)
		{
			buffer.append(" [");
			buffer.append(auxiliar.dado);
			buffer.append(" ]");
			buffer.append("[] -->");
			auxiliar= auxiliar.proximo;
		}
		buffer.append("NULL");
		return buffer.toString();
	}
	
}
