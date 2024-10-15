package View;

import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.SteamController;

public class Main 
{

	public static void main(String[] args) 
	{
		SteamController chart = new SteamController();
		String arquivo ="SteamCharts.csv";
		String path = "C:\\Users\\Davi\\AppData\\Local\\Temp";
		String ano = JOptionPane.showInputDialog(null, "Informe o ano");
		String mes = JOptionPane.showInputDialog(null, "Informe o mes");
		float media = Float.parseFloat(JOptionPane.showInputDialog(null, "Informe a media"));
		try {
			chart.CriarCsv(path, arquivo, ano , mes);
			chart.media(path, arquivo, ano, mes, media);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
