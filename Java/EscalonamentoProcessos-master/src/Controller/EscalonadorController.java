package Controller;

import br.com.DaviQzR.filaobj.Fila;
import model.Processo;

public class EscalonadorController 
{
	public void escalonador (Fila fila)
	{
		while(!fila.isEmpty())
		{
			try
			{
				Processo proc = (Processo) fila.remove();
				if (proc.QtdRetornos > 1)
				{
					proc.QtdRetornos--;
					fila.insert(proc);
					System.out.println(proc.nome+ "|  " + "Retornos restantes: " + proc.QtdRetornos);
				}else
				 {
					System.out.println(proc.nome + " Finalizado");
				 }
			}catch (Exception e)
			  {
				e.printStackTrace();
			  }	
		}
	}
}
