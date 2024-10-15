package model;

import java.time.LocalDateTime;

public class Veiculo 
{
	public String placa;
	public String marca;
	public String modelo;
	public LocalDateTime horaEntrada;
	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", horaEntrada=" + horaEntrada
				+ "]";
	}
	
	
	
}
