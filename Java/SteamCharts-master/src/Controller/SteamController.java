package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class SteamController 
{
	public void media(String path, String nome, String ano, String mes, float media)throws IOException
	{
		File arquivo = new File(path, nome);
		if(arquivo.exists()&& arquivo.isFile())
		{
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while(linha!=null)
			{
				String[]lista = linha.split(",");
				float mediaLista = Float.parseFloat(lista[3]);
				if(ano.contains(lista[1])&& mes.contains(lista[2])&& mediaLista >= media)
				{
					System.out.println(lista[0]+ " | "+lista[3]);
					
				}
				linha = buffer.readLine();
			}
			buffer.close();
			fluxo.close();
			leitor.close();
		}else
		 {
			throw new IOException("Arquivo Inválido");
		 }
	}
	public void CriarCsv(String path, String nome, String ano, String mes) throws IOException
	{
		String newPath = JOptionPane.showInputDialog(null,"insira o caminho novo");
		String newNome = JOptionPane.showInputDialog(null,"insira o novo nome");
		File diretorio = new File(newPath);
		File referencia = new File(path,nome);
		if(diretorio.exists()&& diretorio.isDirectory())
		{
			File arquivo = new File(newPath,newNome);
			boolean existe = false;
			if(arquivo.exists())
			{
				existe = true;
			}
			FileInputStream fluxo = new FileInputStream(referencia);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			FileWriter escrita = new FileWriter(arquivo,existe);
			String conteudo = "";
			PrintWriter print = new PrintWriter(escrita);
			while (linha!=null)
			{
				String[]lista = linha.split(",");
				if(ano.contains(lista[1]) && mes.contains(lista[2]))
				{
					conteudo = (lista[0]+ ","+lista[3]+"\n");
					print.write(conteudo);
					print.flush();
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			print.close();
			print.close();
			escrita.close();
		}
	}
}
