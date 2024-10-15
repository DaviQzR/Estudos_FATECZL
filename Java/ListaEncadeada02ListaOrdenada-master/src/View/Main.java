package View;

import model.Lista;

public class Main {

	public static void main(String[] args) throws Exception
	{
		Lista list = new Lista();
		int [] vetor = {10,5,8,1,9,2,4,7,3,6};
		list = vectorInit(vetor);
		list = ordenaLista(list);
		System.out.println("Lista Ordenada: ");
		while(!list.isEmpty()){
	        System.out.print(list.get(0)+ " ");
	        list.removeFirst();
	}
		
}

	public static Lista vectorInit(int[] vetor) throws Exception {
	    Lista aux = new Lista();
	    int tamanho = vetor.length;
	    for (int i = 0; i < tamanho; i++) {
	        if (aux.isEmpty()) {
	            aux.add(vetor[i], i);
	        } else {
	            aux.add(vetor[i], i);
	        }
	    }
	    return aux;
	}

	private static Lista ordenaLista(Lista list) throws Exception
	{
		for (int i = 0; i < list.size(); i++)
		{
			for (int j = i + 1; j < list.size(); j ++)
			{
				if (list.get(i) > list.get(j))
				{
					int aux = list.get(j);
					list.add(list.get(i),j);
					list.remove(j+1);
					list.add(aux, i);
					list.remove(i+1);
				}
			}
		}
		return list;
	}
	
}
