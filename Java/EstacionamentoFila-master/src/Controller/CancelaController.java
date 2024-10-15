package Controller;

import br.com.DaviQzR.filaobj.Fila;
import model.Veiculo;

public class CancelaController 
{
	public CancelaController()
	{
		super();
	}
	public void CancelaEstacionamento(Fila fila)
	{
		while(!fila.isEmpty())
		{
			try 
			{
				Veiculo v =(Veiculo) fila.remove();
				System.out.println(v);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

}
