package Controller;

public class VTthread extends Thread
{
	int num;
	int vet[];
	
	public VTthread(int num, int vet [])
	{
		this.num=num;
		this.vet= vet;
	}
	public void run() {
		Start();
	}

	private void Start() {
		
		if(num % 2 == 0) 
		{
			double TempoInicial = System.nanoTime();
			for(int i = 0; i < vet.length; i++) 
			{
				
			}
			double TempoFinal = System.nanoTime();
			double TempoTotal = TempoFinal - TempoInicial;
			TempoTotal = TempoTotal / Math.pow(10, 9);
			System.out.println("Par ==> " + TempoTotal + "s.");
		} else 
		{
			double TempoInicial = System.nanoTime();
			
			for(int var: vet) {
				
			}
			double TempoFinal = System.nanoTime();
			double TempoTotal = TempoFinal - TempoInicial;
			TempoTotal = TempoTotal / Math.pow(10, 9);
			
			System.out.println("Impar ==> " + TempoTotal + "s.");
		}
	}

}