package view;

import java.time.LocalDateTime;

import Controller.CancelaController;
import br.com.DaviQzR.filaobj.Fila;
import model.Veiculo;

public class Principal 
{

	public static void main(String[] args) 
	{
		Fila fila = new Fila();
		Veiculo v1 = new Veiculo();
		v1.placa = "AAA-0000";
		v1.marca = "Fiat";
		v1.modelo = "Palio";
		v1.horaEntrada = LocalDateTime.now();
		
		Veiculo v2 = new Veiculo();
		v2.placa = "AAA-1111";
		v2.marca = "Renault";
		v2.modelo = "Logan";
		v2.horaEntrada = LocalDateTime.now();
		
		Veiculo v3 = new Veiculo();
		v3.placa = "AAA-2222";
		v3.marca = "Volkswagen";
		v3.modelo = "Gol";
		v3.horaEntrada = LocalDateTime.now();
		
		Veiculo v4 = new Veiculo();
		v4.placa = "AAA-3333";
		v4.marca = "Ford";
		v4.modelo = "Ka";
		v4.horaEntrada = LocalDateTime.now();
		 
		fila.insert(v1);
		fila.insert(v2);
		fila.insert(v3);
		fila.insert(v4);
		
		CancelaController cCont = new CancelaController();
		cCont.CancelaEstacionamento(fila);
	}

}
