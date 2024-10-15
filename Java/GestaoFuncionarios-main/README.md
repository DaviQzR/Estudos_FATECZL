## Gestão De Funcionários

A classe Funcionario deve conter as seguintes características e comportamentos.
Características :
      id : long
      nome : String
      matricula : String
      admissao : Date
      demissao : Date
      salario : float
      horario : String
Comportamentos :
    void exibir() // Deve mostrar os dados do funcionário na tela

Crie uma classe chamada GestaoFuncionarios, contendo as seguintes características e comportamentos.
Características:
      indice : int      // Indica em qual posição deve ser guardada a próxima instância de funcionario
      funcionarios : Funcionario[ ]  // Matriz com 50 funcionarios
Comportamentos:
      void criar()
      void atualizar()
      void excluir()
      void exibir()
      void menu()

      O comportamento criar() deve criar uma nova instância de Funcionario, preencher as características desta instância com informações fornecidas pelo usuário, e deve guardar esta instância de Funcionario na matriz (funcionarios) na posição indicada pela variável indice.
      
      A função exibir() deve pedir ao usuário para que digite um número de matricula e procure qual funcionario na matriz (funcionarios) possui um matricula idêntico, aquele(s) funcionário(s) com a matricula identica deve(m) ser exibido(s) na tela.
      
      A função exluir() deve pedir ao usuário para digitar uma matricula, e em seguida deve exluir o(s) funcionario(s) com esta matricula na matriz (funcionarios)
      
      A função atualizar() deve pedir ao usuário para digitar uma matricula, e em seguida deve procurar pelo primeiro funcionario na matriz (funcionarios) que contenha esta matricula. A função deve  em seguida solicitar ao usuário para que digite os demais dados do funcionario (nome, admissao, demissao, salario, horario) para trocar os valores das características do funcionario encontrado na matriz pelos valores recém informados pelo usuário.
   
      O método menu() deve rodar em um loop inifinito, mostrando na tela as opções para o usuário:
                (C)riar           (E)xibir             (R)emover              
         (A)tualizar                (S)air

      Pegue a primeira letra digitada pelo usuário e assuma como sendo a opção escolhida
            String textoMaiusculo = scan.nextLine().toUpperCase();
            char letra = textoMaiusculo.charAt(0);
      Conforme a opção escolhida o método deve invocar a função correspondente criar(), exibir(), excluir(), atualizar() ou System.exit(0) para sair do sistema.
