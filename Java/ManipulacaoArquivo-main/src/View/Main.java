package View;

import Controller.lerArquivo;

public class Main {

	public static void main(String[] args)
	{
		String arquivo = "generic_food.csv";
		String path = "C:\\Users\\dti\\AppData\\Local\\Temp";
		lerArquivo ler = new lerArquivo();
		try
		{
			ler.leitura (path, arquivo);
		}catch (Exception e)
		{
			
		}
	}

}
