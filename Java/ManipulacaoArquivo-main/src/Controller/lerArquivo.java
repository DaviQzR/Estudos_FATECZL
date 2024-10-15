package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class lerArquivo implements ILerArquivo
{
	
	public lerArquivo() 
	{
		super();
	}
	
	@Override
	public void leitura( String path, String nome) throws IOException
	{
		File arquivo = new File(path, nome);
		if (arquivo.exists() && arquivo.isFile () )
		{
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader (fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null)
			{
				boolean contem = false;
				String [] lista = linha.split (",");
				for(String fruta :lista )
				{
					if(lista[2].contains("Fruits"))
					{
						contem = true;
						System.out.print(fruta + "\t");
					}
				}
				if (contem)
				{
					System.out.println();
				}
				linha= buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		
		}else
		 {
			throw new IOException("Arquivo Invalido");
		 }
	
	}
}
