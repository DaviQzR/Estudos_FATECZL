package Controller;

public class CorridaSapo extends Thread
{
	private String nome; 
	 private int DistanciaPercorrida=0;
	 private int DistanciaTotal; 
	private int Pulo=0; 
	private int QuantidadePulo=0; 
	private static int Colocacao=0; 
	private final static int PuloMaximo=35;
	
	public CorridaSapo(String nome, int distanciaTot) 
	{
		super(nome);
		this.nome=nome;
		this.DistanciaTotal=distanciaTot;
	}
	
	
	
	public void CorreSapo() 
	{
		System.out.println(" O "+ nome + " Pulou "+ Pulo +" cm e percorreu " + DistanciaPercorrida +" cm ");
	}
	
	
	public void Sapopula() 
	{
		QuantidadePulo++;
		Pulo=(int) (Math.random()*PuloMaximo);
		DistanciaPercorrida=DistanciaPercorrida+Pulo;
		if(DistanciaPercorrida > DistanciaTotal) 
		{
			DistanciaPercorrida = DistanciaTotal;
			
		}	
	}
	
	
	public void SapoDescansa() 
	{
		yield();
	
	}
	
	
	public void Tabela() 
	{
		Colocacao++;
		System.out.println("\n"+ nome + " Foi o °"+ Colocacao+" colocado com "+ QuantidadePulo+ "pulos" );
	}
	
	
	
	
	@Override
	public void run() 
	{
		while(DistanciaPercorrida <DistanciaTotal)
		{
			Sapopula();
			CorreSapo();
			SapoDescansa() ;
			
		try 
		{
			sleep(200);
			
		}catch (InterruptedException e) {}
		}		
			try 
			{
				sleep(2000);
			}catch(InterruptedException e) {}
			
		Tabela();
		
	}
}
