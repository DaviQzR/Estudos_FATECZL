package View;

import java.util.Scanner;
import Model.Contato;

public class GerenciadorDeContatos {

  private static final int MAX_SIZE = 50;
  private final Contato[] contatos = new Contato[MAX_SIZE];
  private int indice = 0;
  private final Scanner input = new Scanner(System.in);

  public void menu() {
    while (true) {
      System.out.println("Gerenciador de Contatos");
      System.out.println("(C)riar contato");
      System.out.println("(E)xibir contato");
      System.out.println("(R)emover contato");
      System.out.println("(A)tualizar contato");
      System.out.println("(L)istar contato");
      System.out.println("(S)air");
      char opcao = input.nextLine().toUpperCase().charAt(0);

      if (opcao == 'C') {
        criar();
      } else if (opcao == 'E') {
        exibir();
      } else if (opcao == 'R') {
        remover();
      } else if (opcao == 'A') {
        atualizar();
      } else if (opcao == 'L') {
        listar();
      } else if (opcao == 'S') {
        System.out.println("Até Breve ");
        break;
      }
    }
  }

  public static void main(String[] args) {
    new GerenciadorDeContatos().menu();
  }

  public void criar() {
    System.out.println("Criando contatos");
    if (indice < MAX_SIZE) {
      Contato cont = new Contato();
      System.out.println("Digite o nome do  contato ");
      cont.setNome(input.nextLine());

      System.out.println("Digite o email do contato");
      cont.setEmail(input.nextLine());

      System.out.println("Digite o telefone do contato");
      long telefone = input.nextLong();
      input.nextLine();
      cont.setTelefone(telefone);

      System.out.println("Digite a nova data ");
      String strDate = input.nextLine();
      cont.setDatinha(strDate);

      cont.setId(indice);
      contatos[indice] = cont;
      indice++;

      System.out.println("Contato criado com Sucesso");
    }
  }

  public void exibir() {
    System.out.println("Exibindo informações do contato");
    System.out.println("Digite o nome a ser exibido");
    String nome = input.nextLine();
    int i = pesquisar(nome);

    if (i >= 0) {
      Contato cont = contatos[i];
      System.out.println("Detalhes do Contato");
      System.out.println("Nome: " + cont.getNome() +  " E-mail: " + cont.getEmail() + " Telefone: " + cont.getTelefone()
          +  " Datinha: " + cont.getDatinha());
    } else {
      System.out.println("Não foi possível exibir o contato com o nome fornecido");
    }
  }

  public int pesquisar(String nome) {
    for (int i = 0; i < MAX_SIZE; i++) {
      Contato cont = contatos[i];
      if (cont != null && nome.equals(cont.getNome())) {
        return i;
      }
    }
    return -1;
  }

  public void remover() {
    System.out.println("Exclusão do Contato ");
    System.out.println("Informe o nome do contato");
    String nome = input.nextLine();
    int i = pesquisar(nome);
    if (i >= 0) {
      contatos[i] = null;

      System.out.println("Contato removido com sucesso");
    }
  }

  public void atualizar() {
    System.out.println("Atualização do Contato");
    System.out.println("Informe o nome do Contato que deseja atualizar");
    String nome = input.nextLine();
    int i = pesquisar(nome);
    if (i >= 0) {
        Contato cont = contatos[i];

        System.out.println("Informe o novo nome do contato");
        cont.setNome(input.nextLine());

        System.out.println("Informe o novo email do contato");
        cont.setEmail(input.nextLine());

        System.out.println("Informe o novo telefone do contato");
        long telefone = input.nextLong();
        input.nextLine(); 
        cont.setTelefone(telefone);

        System.out.println("Informe a nova data do contato");
        String strDate = input.nextLine();
        cont.setDatinha(strDate);

        cont.setId(indice);

        System.out.println("Contato atualizado com Sucesso");

    }else{
      System.out.println("Contato não encontrado com o nome fornecido");
    }
}


  public void listar() {
    System.out.println("Listagem dos Contatos");
    for (int i = 0; i < MAX_SIZE; i++) {
      Contato cont = contatos[i];
      if (cont != null) {
        System.out.println(cont);
      }
    }

  }

}