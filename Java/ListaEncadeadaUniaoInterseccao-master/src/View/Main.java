package View;

import model.Lista;

public class Main {

	public static void main(String[] args) throws Exception
	{
		int[] vetA = {3, 5, 8, 12, 9, 7, 16};
		int[] vetB = {9, 6, 2, 3, 7};
		
		Lista listA = new Lista();
		Lista listB = new Lista();
		Lista U = new Lista();
		Lista I = new Lista();
		
		int tamA = vetA.length;
		int tamB = vetB.length;
		
		
		listA.addFirst(vetA[0]);
		listB.addFirst(vetB[0]);
		

		for(int i = 1; i < tamA; i++)
		{
			listA.addLast(vetA[i]);
		}
		
		for(int i = 1; i < tamB; i++)
		{
			listB.addLast(vetB[i]);
		}
		
		for (int i = 0; i < tamA; i++)
		{
			for(int j = 0; j < tamB; j++)
			{
				if(listA.get(i) == listB.get(j))
				{
					I.addFirst(listA.get(i));							
				}
			}
		}
		int maxSize;
		if (listA.size() > listB.size()) {
		    maxSize = listA.size();
		} else {
		    maxSize = listB.size();
		}

		for (int i = 0; i < maxSize; i++) {
		    if (i < listA.size()) {
		        U.addFirst(listA.get(i));
		    }
		    if (i < listB.size()) {
		        U.addFirst(listB.get(i));
		    }
		}
		
		for (int i = 0; i < U.size(); i++)
		{
		int cont = 0;
			for (int j = 0; j < U.size(); j++)
			{
				if (U.get(i) == U.get(j))
				{
					if (cont != 0)
					{
						U.remove(j);
					}
					cont++;
				}
			}
		}
		
		System.out.print("Interseccao\n");
		
		while(!I.isEmpty())
		{		
			System.out.print(I.get(0) + " ");
			I.removeFirst();
		}
		
		System.out.println("\nUniao");
		
		while(!U.isEmpty())
		{
			System.out.print(U.get(0)+ " ");
			U.removeFirst();
		}
	}

}

