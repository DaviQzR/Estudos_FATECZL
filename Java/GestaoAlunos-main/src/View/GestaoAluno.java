package View;

import java.util.Scanner;

import Model.Aluno;

public class GestaoAluno {

  public static final int MAX_SIZE = 50;
  private int indice = 0;
  private final Aluno[] alunos = new Aluno[MAX_SIZE];
  private final Scanner input = new Scanner(System.in);

  /**
   * 
   */
  public void menu() {
    while( true ) {
        System.out.println("Gestão de Alunos");
        System.out.println("(C)adastrar novo aluno");
        System.out.println("(L)istar alunos");
        System.out.println("(E)xibir detalhes de um aluno");
        System.out.println("(R)remover aluno");
        System.out.println("(A)tualizar aluno");
        System.out.println("(S)air");
        char opcao = input.nextLine().toUpperCase().charAt(0);
        if (opcao == 'C') {
            criar();
        } else if (opcao == 'L') {
            listar();
        } else if (opcao == 'R') {
            excluir();
        } else if (opcao == 'A') {
            atualizar();
        } else if (opcao == 'E') {
            exibir();
        } else if (opcao == 'S') {
            System.out.println("Ate breve");
            break;
        }
    }
}

  public static void main (String[]args){
    new GestaoAluno().menu();
  }



  public void criar() {
    System.out.println("Cadastro de novo Aluno ");
    if (indice < MAX_SIZE) {
      Aluno student = new Aluno();
      System.out.println("Digite o nome do Aluno: ");
      student.setNome(input.nextLine());
      System.out.println("Digite o RA do Aluno: ");
      student.setRa(input.nextLine());
      System.out.println("Digite a data de nascimento do Aluno: ");
      String strDate = input.nextLine();
      student.setNascimento(strDate);
      student.setId(indice);
      alunos[indice] = student;
      indice++;

    } else {
      System.out.println("Não tem mais espaço na memoria para guardar alunos ");
    }
  }

  public void exibir() {
    System.out.println("Exibir dados do Aluno ");
    System.out.println("Digite o RA do Aluno a ser exibido ");
    String ra = input.nextLine();
    int i = pesquisar(ra);
    if (i >= 0) {
      Aluno student = alunos[i];
      System.out.println("Detalhes do Aluno ");
      System.out.println(
          "RA: " + student.getRa() + " Nome: " + student.getNome() + " Data Nascimento: " + student.getNascimento());
    } else {
      System.out.println("Aluno não encontrado com o RA fornecido");
    }
  }

  public int pesquisar(String ra) {
    for (int i = 0; i < MAX_SIZE; i++) {
      Aluno student = alunos[i];
      if (student != null) {
        if (ra.equals(student.getRa())) {
          return i;
        }
      }
    }
    return -1;
  }

  public void excluir() {
    System.out.println("Exclusão do Aluno");
    System.out.println("Informe o RA do Aluno ");
    String ra = input.nextLine();
    int i = pesquisar(ra);
    if(i >= 0){
      alunos[i]= null;
    }
  }

  public void atualizar(){
    System.out.println("Atualização de Aluno ");
    System.out.println("Informe o RA do aluno que deseja atualizar");
    String ra = input.nextLine();
    int i = pesquisar (ra);
    if(i >= 0){
      Aluno student = alunos[i];
      System.out.println("Digite o novo nome do aluno: ");
      student.setNome(input.nextLine());
      System.out.println("Digite o novo RA do Aluno: ");
      student.setRa(input.nextLine());
      System.out.println("Digite a nova data de nascimento: ");
      String strDate = input.nextLine();
      student.setNascimento(strDate);
      student.setId(indice);
    }
  }

  public void listar() {
    System.out.println("Listagem de alunos");
    for (int i = 0; i < MAX_SIZE; i++){
      Aluno student = alunos[i];
      if (student!= null){
        System.out.println(student);
      }
    }
  }
}
