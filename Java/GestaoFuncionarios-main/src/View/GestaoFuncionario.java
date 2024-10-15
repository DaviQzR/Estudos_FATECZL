package View;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import Model.Funcionario;

public class GestaoFuncionario {

  private int indice = 0;
  public static final int MAX_SIZE = 50;
  private final Funcionario[] funcionarios = new Funcionario[MAX_SIZE];
  private final Scanner input = new Scanner(System.in);

  public void menu() {
    while (true) {
      System.out.println("Gestão de Funcionários");
      System.out.println("(C)riar");
      System.out.println("(E)xibir");
      System.out.println("(R)emover");
      System.out.println("(A)tualizar");
      System.out.println("(L)istar");
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
    new GestaoFuncionario().menu();
  }

  public void criar() {
    System.out.println("Cadastro de novo Funcionário");
    Funcionario func = new Funcionario();
    if (indice < MAX_SIZE) {
      System.out.println("Informe o nome do Funcionario");
      func.setNome(input.nextLine());

      System.out.println("Informe a matricula do Funcionario");
      func.setMatricula(input.nextLine());

      System.out.println("Informe a data de admissão");
      String strDate = input.nextLine();
      func.setAdmissao(strDate);

      System.out.println("Informe a data de demissão do Funcionário");
      String Date = input.nextLine();
      func.setDemissao(Date);

      System.out.println("Informe o salário do Funcionário");
      String salario = input.nextLine();
      try {
        func.setSalarioFormatado(salario);
      } catch (ParseException e) {
        System.out.println("Erro ao converter o salário. Certifique-se de que a entrada seja um número válido.");
      }

      System.out.println("Informe a hora do Funcionário");
      String hora = input.nextLine();
      try {
        func.setHorario(hora);
      } catch (DateTimeParseException e) {
        System.out
            .println("Erro ao converter a hora. Certifique-se de que a entrada esteja no formato correto (HH:mm:ss).");
      }
      func.setId(indice);
      funcionarios[indice] = func;
      indice++;
    }
  }

  public void exibir() {
    System.out.println("Exibindo os dados do Funcionário");
    System.out.println("Informe a matrícula:");
    String matricula = input.nextLine();
    int i = pesquisar(matricula);
    if (i >= 0) {
      Funcionario func = funcionarios[i];
      System.out.println("Detalhes do Funcionário:");
      System.out.println("Nome: " + func.getNome());
      System.out.println("Matrícula: " + func.getMatricula());
      System.out.println("Admissão: " + func.getAdmissao());
      System.out.println("Demissão: " + func.getDemissao());
      System.out.println("Salário: " + func.getSalarioFormatado());
      System.out.println("Hora: " + func.getHorario());
      System.out.println("ID: " + func.getId());
    } else {
      System.out.println("Não foi possível encontrar o funcionário com a matrícula fornecida.");
    }
  }

  public int pesquisar(String matricula) {
    for (int i = 0; i < MAX_SIZE; i++) {
      Funcionario func = funcionarios[i];
      if (func != null && matricula.equals(func.getMatricula())) {
        return i;
      }
    }
    return -1;
  }

  public void remover() {
    System.out.println("Exclusão do Funcionário");
    System.out.println("Informe a matricula do Funcionário");
    String matricula = input.nextLine();
    int i = pesquisar(matricula);
    if (i >= 0) {
      funcionarios[i] = null;
    }
  }

  public void atualizar() {
    System.out.println("Atualização do Funcionário");
    System.out.println("Informe a matrícula do Funcionário que deseja atualizar");
    String matricula = input.nextLine();
    int i = pesquisar(matricula);

    if (i >= 0) {
      Funcionario func = funcionarios[i];
      System.out.println("Digite o novo nome do Funcionário");
      func.setNome(input.nextLine());

      System.out.println("Digite a nova matrícula do Funcionário");
      func.setMatricula(input.nextLine());

      System.out.println("Digite a nova data de admissão do Funcionário (dd/mm/yyyy)");
      String strDateAdmissao = input.nextLine();
      func.setAdmissao(strDateAdmissao);

      System.out.println("Digite a nova data de demissão do Funcionário (dd/mm/yyyy)");
      String strDateDemissao = input.nextLine();
      func.setDemissao(strDateDemissao);

      System.out.println("Digite o novo salário do Funcionário");
      String salario = input.nextLine();
      try {
        func.setSalarioFormatado(salario);
        System.out.println("Salário atualizado com sucesso: " + func.getSalarioFormatado());
      } catch (ParseException e) {
        System.out.println("Erro ao atualizar o salário. Certifique-se de inserir um valor válido.");
      }

      System.out.println("Digite a nova hora do Funcionário (HH:mm:ss)");
      String hora = input.nextLine();
      func.setHorario(hora);
      System.out.println("Hora atualizada com sucesso: " + func.getHorario());
    } else {
      System.out.println("Funcionário não encontrado.");
    }
  }

  public void listar() {
    System.out.println("Listagem dos Funcionários");
    for (int i = 0; i < MAX_SIZE; i++) {
      Funcionario func = funcionarios[i];
      if (func != null) {
        System.out.println(func);
      }
    }
  }
}
