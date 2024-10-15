package View;

import Controller.EscalonadorController;
import br.com.DaviQzR.filaobj.Fila;
import model.Processo;

public class Main 
{

	public static void main(String[] args) 
	{
		EscalonadorController  escala = new EscalonadorController();
		Fila fila = new Fila();
		
		fila.insert(new Processo("notepad.exe", 14));
		fila.insert(new Processo("write.exe", 35));
		fila.insert(new Processo("chrome.exe", 27));
		fila.insert(new Processo("acrobat.exe", 52));
		fila.insert(new Processo("firefox.exe", 18));
		fila.insert(new Processo("word.exe", 25));
		escala.escalonador(fila);
		
	}

}
