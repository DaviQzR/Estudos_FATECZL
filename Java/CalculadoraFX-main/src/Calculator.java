import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private double firstOperand = 0; // Armazena números com casas decimais. Ela é inicializada com o valor 0, que é o valor padrão do primeiro operando da calculadora
    private String operator = ""; // Armazenar o operador matemático atualmente selecionado (como +, -, *, /) ou uma string vazia ("") se nenhum operador foi selecionado ainda
    private boolean isTypingNumber = false; // Rastrea se o usuário está atualmente digitando um número na calculadora. Ela é inicializada como false, indicando que o usuário não está digitando um número no início.

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculadora");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER); // Define o alinhamento dos elementos dentro do GridPane para o centro.
        grid.setHgap(10);  // Define o espaçamento horizontal (espaço entre as colunas) entre as células na grade como 10 pixels.
        grid.setVgap(10);  // Define o espaçamento vertical (espaço entre as linhas) entre as células na grade como 10 pixels.

        // Display onde os números e resultados são exibidos
        TextField display = new TextField();
        display.setStyle("-fx-font-size: 36;");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER);
        display.setPrefColumnCount(4);

        // Botão 'C' (Limpar)
        Button btnClear = new Button("C");
        btnClear.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
        
        // Adiciona o visor na primeira linha e o botão 'C' à direita do visor
        grid.add(display, 0, 0, 5, 1);
        grid.add(btnClear, 5, 0);

        Button[] numberButtons = new Button[11];
        for (int i = 0; i <= 9; i++) {
            numberButtons[i] = new Button(Integer.toString(i));
            numberButtons[i].setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
        }
        numberButtons[10] = new Button(".");
        numberButtons[10].setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");

        int buttonCol = 0;
        int buttonRow = 1;

        for (int i = 1; i <= 9; i++) {
            grid.add(numberButtons[i], buttonCol, buttonRow);
            buttonCol++;
            if (buttonCol > 2) {
                buttonCol = 0;
                buttonRow++;
            }
        }

        // Botão '0' e Botão '=' na mesma linha
        grid.add(numberButtons[0], 1, 4);
        Button btnEquals = new Button("=");
        btnEquals.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
        grid.add(btnEquals, 2, 4);
        // Botão de vírgula (ponto) - Adiciona um botão à grade na coluna 0 (primeira coluna) e linha 4.
       // Esse botão permite que o usuário insira um ponto decimal para números não inteiros.
       grid.add(numberButtons[10], 0, 4);

      // Cria botões de operação para adição, subtração, multiplicação e divisão.
      Button btnPlus = new Button("+");
      Button btnMinus = new Button("-");
      Button btnMultiply = new Button("*");
      Button btnDivide = new Button("/");

      // Define o estilo (tamanho da fonte, largura e altura) para os botões de operação.
      btnPlus.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
      btnMinus.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
      btnMultiply.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");
      btnDivide.setStyle("-fx-font-size: 24; -fx-min-width: 80px; -fx-min-height: 80px;");

      // Adiciona os botões de operação à grade nas posições apropriadas (coluna 3, linhas 1 a 4).
      grid.add(btnPlus, 3, 1);
      grid.add(btnMinus, 3, 2);
      grid.add(btnMultiply, 3, 3);
      grid.add(btnDivide, 3, 4);

      // Cria uma cena com a grade e define as dimensões da cena como 450x550 pixels.
      Scene scene = new Scene(grid, 450, 550);

     // Define a cena no palco (janela da aplicação).
     stage.setScene(scene);

     // Configura os controladores de eventos para os botões numéricos, operadores e outros botões.
    for (int i = 0; i <= 10; i++) {
    final int number = i;
    // Configura o controlador de eventos para cada botão numérico (incluindo o botão de ponto).
    numberButtons[i].setOnAction(e -> {
        if (number == 10) {
            if (!isTypingNumber) {
                display.clear();
                isTypingNumber = true;
                display.appendText("0.");
            } else if (!display.getText().contains(".")) {
                display.appendText(".");
            }
        } else {
            appendNumber(display, number);
        }
    });
}

    // Configura os controladores de eventos para os botões de operação.
    btnPlus.setOnAction(e -> setOperator(display, "+"));
    btnMinus.setOnAction(e -> setOperator(display, "-"));
    btnMultiply.setOnAction(e -> setOperator(display, "*"));
    btnDivide.setOnAction(e -> setOperator(display, "/"));

    // Define controladores de eventos para os botões "Limpar" e "Igual".
    btnClear.setOnAction(e -> clear(display));
    btnEquals.setOnAction(e -> calculateResult(display));

    // Exibe o palco com a cena e os botões configurados.
    stage.show();
}

/* 
 Este método é responsável por adicionar um número ao campo de exibição (display).
 Se o usuário não estiver atualmente digitando um número, o campo é limpo,
 e a variável 'isTypingNumber' é definida como verdadeira para indicar que o usuário
 está iniciando a entrada de um novo número. Em seguida, o número selecionado
 é anexado ao campo de exibição.
*/
 private void appendNumber(TextField display, int number) {
        if (!isTypingNumber) {
            display.clear(); // Limpa o campo de exibição
            isTypingNumber = true; // Indica que o usuário está digitando um número
        }
        display.appendText(Integer.toString(number)); // Anexa o número ao campo de exibição
    }
/* 
 Este método é usado para definir o operador da operação. Se o usuário já estiver
 digitando um número (isTypingNumber é verdadeiro), ele armazena o primeiro operando
 (que está no campo de exibição) na variável 'firstOperand', define o operador
 selecionado na variável 'operator' e redefine 'isTypingNumber' como falso para
 indicar que o usuário não está mais digitando um número, mas sim um operador.
*/
 private void setOperator(TextField display, String newOperator) {
        if (isTypingNumber) {
            firstOperand = Double.parseDouble(display.getText()); // Armazena o primeiro operando
            operator = newOperator; // Define o operador
            isTypingNumber = false; // Indica que o usuário não está mais digitando um número
        }
    }
/* 
 Este método é usado para limpar o campo de exibição e redefinir a calculadora
 para um estado inicial. Ele limpa o campo, redefine 'firstOperand' para 0,
'operator' para uma string vazia e isTypingNumber para falso.
*/
    private void clear(TextField display) {
        display.clear();  // Limpa o campo de exibição
        firstOperand = 0; // Redefine o primeiro operando como 0
        operator = ""; // Redefine o operador como uma string vazia
        isTypingNumber = false; // Indica que o usuário não está mais digitando um número
    }
/* 
 Este método é usado para calcular o resultado da operação. Se o usuário estiver
 digitando um número (isTypingNumber é verdadeiro) e a variável 'operator' não estiver vazia,
 ele realiza a operação apropriada (adição, subtração, multiplicação ou divisão)
 entre firstOperand e o segundo operando (que está no campo de exibição). O resultado
 é então exibido no campo de exibição. Se a operação de divisão for por zero,
 é exibida uma mensagem de erro
*/
    private void calculateResult(TextField display) {
        if (isTypingNumber && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(display.getText());  // Obtém o segundo operando
            switch (operator) {
                case "+":
                    display.setText(Double.toString(firstOperand + secondOperand));  // Realiza a adição
                    break;
                case "-":
                    display.setText(Double.toString(firstOperand - secondOperand));  // Realiza a subtração
                    break;
                case "*":
                    display.setText(Double.toString(firstOperand * secondOperand)); // Realiza a multiplicação
                    break;
                case "/":
                    if (secondOperand != 0) {
                        display.setText(Double.toString(firstOperand / secondOperand)); // Realiza a divisão
                    } else {
                        display.setText("Erro: Divisão por zero"); // Exibe uma mensagem de erro se houver divisão por zero
                    }
                    break;
            }
            isTypingNumber = false; // Indica que o usuário não está mais digitando um número
            operator = ""; // Redefine o operador como uma string vazia
        }
    }
}
