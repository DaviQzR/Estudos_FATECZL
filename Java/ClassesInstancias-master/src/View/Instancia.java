package View;
import Model.Aviao;
import Model.Bola;
import Model.Professor;

public class Instancia {

	public static void main(String[] args) {
		
		Aviao Viao1 = new Aviao();
		Aviao Viao2 = new Aviao();
		
		Professor Teacher = new Professor ();
		Professor Teacher2 = new Professor ();
		
		Bola b1 = new Bola ();
		Bola b2 = new Bola ();
		
		Viao1.tamanho = 72;
		Viao1.peso = 90;
		Viao1.cor = "Azul";
		Viao1.velocidade = 850;
		Viao1.capacidade = 500;
		
		Viao2.tamanho = 85;
		Viao2.peso = 95;
		Viao2.cor = "Vermelho";
		Viao2.velocidade = 700;
		Viao2.capacidade = 250;
		
		Teacher.nome = "Carlito";
        Teacher.formacao = "Engenharia Eletrica";
        Teacher.idade = 53;
        Teacher.materia = "Banco de Dados";
        Teacher.cfep = 52472791;
       
        Teacher2.nome = "Davisinho";
        Teacher2.formacao = "Analise e Desenvolvimento de Sistemas";
        Teacher2.idade = 20;
        Teacher2.materia = "Algoritmo";
        Teacher2.cfep = 10078539;
        
        b1.cor = "Preta";
        b1.formato = "Esferica";
        b1.tamanho =  68;
        b1.material = "Borracha";
        b1.peso = 410;
        
        b2.cor = "Branca";
        b2.formato = "Esferica";
        b2.tamanho =  78;
        b2.material = "Couro";
        b2.peso =  450;
        
//--------------------------------MÉTODOS-------------------------------------------------
        System.out.println(Viao1.toString());
        Viao1.voar();
		Viao1.comunicar();
		Viao1.pousar();
		Viao1.desacelerar();
		
		
		
		System.out.println("\n");
		
		System.out.println(Viao2.toString());
		Viao2.voar();
		Viao2.comunicar();
		Viao2.pousar();
		Viao2.desacelerar();
		
		
		
		System.out.println("\n");
		
		System.out.println(Teacher.toString());
		 Teacher.comunicar();
		 Teacher.aula();
		 Teacher.andar();
		 
		 System.out.println("\n");
		 
		 System.out.println(Teacher2.toString());
		 Teacher2.comunicar();
		 Teacher2.aula();
		 Teacher2.andar();
		 
		 System.out.println("\n");
		 
		 System.out.println(b1.toString());
		    b1.encher();
		    b1.pular();
			b1.chutar();
			
		  System.out.println("\n");
		  
		  System.out.println(b2.toString());
		    b2.encher();
		    b2.pular();
			b2.chutar();
	}

}
