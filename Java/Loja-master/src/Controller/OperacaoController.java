package Controller;

import Model.Cliente;
import br.com.DaviQzR.filaobj.Fila;

public class OperacaoController 
{
	public void Caixa (Fila controle)
	{
		Cliente con = null ;
		while(!controle.isEmpty())
		{
			try 
			{
				con = (Cliente)controle.remove();
			} catch (Exception e) 
			   {
					e.printStackTrace();
			   }
			int total = (int) (con.QuantidadePecas * con.ValorPecas);
			System.out.println (con.Nome);
			System.out.println("Valor a ser pago: " +total);
		}
	}
}
