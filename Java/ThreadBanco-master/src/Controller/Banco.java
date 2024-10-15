package Controller;
import java.util.concurrent.Semaphore;
public class Banco extends Thread {
	private int idconta;
	int opc;
	private double Saldo;
	private double Valor;
	Semaphore semaforoSaque;
	Semaphore semaforoDesposito;
	public Banco(int opc, int idconta, double Saldo, double Valor, Semaphore semaforoSaque,Semaphore semaforoDeposito  ) {
	this.opc = opc;
	this.idconta=idconta;
	this.Saldo = Saldo;
	this.Valor = Valor;
	this.semaforoDesposito = semaforoDeposito;
	this.semaforoSaque = semaforoSaque;
	}
	@Override
	public void run() 
	{
		if(opc==1) 
		{
			try 
			{
				semaforoSaque.acquire();
				Saque();
			
			} catch (InterruptedException e) 
				{
					e.printStackTrace();
				} finally 
					{
						semaforoSaque.release();
					}
		}
		else
		{
			try 
			{
				semaforoDesposito.acquire();
				Deposito();
			} catch (InterruptedException e) 
			   {
				e.printStackTrace();
			   }finally
			     {
				   semaforoDesposito.release();
			     }
					
	
		}
	}
	

	private void Saque() 
	{
		try 
		{
			sleep(1000);
		} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		if(Saldo<Valor) 
		{
			System.out.println("!! "+"Operacao que deseja realizar  eh "+opc+" Codigo da sua Conta Saque eh "+idconta+" Seu saldo eh "+ String.format("%,.2f", Saldo)+ " O valor Necessario para ser retirado eh "+  String.format("%,.2f", Valor)+" Voce nao pode realizar esta transeferencia");
		}else
		 {
			double NovoValor;
			NovoValor=(Saldo-Valor);
			System.out.println("# "+ "Operacao que deseja realizar  eh "+opc+ " Codigo da sua Conta Saque eh "+idconta+" Seu saldo eh "+ String.format("%,.2f", Saldo)+ " O valor requerido para ser retirado eh "+  String.format("%,.2f", Valor)+" Ao realizar esta transeferencia seu novo valor e de "+String.format("%,.2f", NovoValor));
		}
		
	}
	private void Deposito() 
	{
		try
		{
			sleep(1000);
		} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		double NovoValor;
		 NovoValor=(Saldo+Valor);
		System.out.println("# " + "Operacao que deseja realizar  eh "+opc+ " codigo da sua conta deposito "+idconta + " Seu saldo eh "+ String.format("%,.2f", Saldo)+" O valor requerito para ser retirado eh "+  String.format("%,.2f", Valor)+ " ao realizar esta transeferencia seu novo valor eh de "+  String.format("%,.2f", NovoValor));	
	}
}