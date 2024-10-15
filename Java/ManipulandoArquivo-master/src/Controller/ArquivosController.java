package Controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class ArquivosController implements IArquivosController
{
	public ArquivosController()
	{
		super();
	}

	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory())
		{
			File[] files = dir.listFiles();
			for (File f : files)
			{
				if (f.isFile())
				{
					System.out.println("      \t"+f.getName());
				}else
				 {
					System.out.println("<DIR>\t" +f.getName());
				 }
			}
		}else
		 {
			throw new IOException("Diretorio inválido");
		 }
	}

	@Override
	public void createFile(String path, String nome) throws IOException
	{
		File dir = new File(path);
		File arq = new File(path , nome );
		if(dir.exists()&& dir.isDirectory())
		{
			boolean existe = false ;
			if(arq.exists())
			{
				existe = true;
			}
			String conteudo = geraTxt();
			FileWriter fileWriter = new FileWriter(arq,existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		}else
		 {
			throw new IOException ("Diretorio Inválido");
		}
		
	}

	private String geraTxt() 
	{
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while (!linha.equalsIgnoreCase("fim"))
		{
			linha = JOptionPane.showInputDialog(null,"Digite uma frase", "Entrada de teto", JOptionPane.INFORMATION_MESSAGE);
			if(!linha.equalsIgnoreCase("fim"))
			{
			
				buffer.append(linha +"\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void readFile(String path, String nome) throws IOException 
	{
		File arq = new File(path, nome);
		if (arq.exists()&& arq.isFile())
		{
			FileInputStream fluxo =new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) // Procurando EOF
			{
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}else 
		   {
				throw new IOException("Arquivo Invalido");
		   }
	}

	@Override
	public void openFile(String path, String nome) throws IOException 
	{
		File arq = new File(path, nome);
		if (arq.exists() && arq.isFile())
		{
			Desktop desktop = Desktop.getDesktop();
			desktop.open(arq);
		}else
		 {
			throw new IOException("Arquivo Invalido");
		 }
	}
}
