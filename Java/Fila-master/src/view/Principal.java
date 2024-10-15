package view;

import model.Fila;

public class Principal 
{

	public static void main(String[] args) 
	{
		Fila f = new Fila();
		boolean vazia = f.isEmpty();
		System.out.println("Vazia: " +vazia);
		
		f.insert(1);
		f.insert(2);
		f.insert(3);
		f.insert(4);
		
		//vazia = f.isEmpty();
		//System.out.println("Vazia: " +vazia);
		//int tamanho = f.size();
		//System.out.println("Tamanho da Fila: " + tamanho);
		//try 
		//{
		//	f.list();
		//} catch (Exception e1) 
		//{
		//	e1.printStackTrace();
		//}
		//try 
		//{
		//	int removido = f.remove();
		//	removido = f.remove();
			
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
		System.out.println(f.toString());
	}

}
