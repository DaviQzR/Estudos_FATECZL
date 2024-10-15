import java.util.Random;
import java.util.Scanner;

public class Joguin {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int numeroGerado = new Random().nextInt(101); // Gerando um número de 0 a 100
        int tentativas = 0;

        while (tentativas < 5) {
            System.out.println("Informe um número de 0 a 100: ");
            int numeroDigitado = read.nextInt();
            tentativas++;

            if (numeroDigitado == numeroGerado) {
                System.out.println("Parabéns, você acertou o número em " + tentativas + " tentativas!");
                break; // interrompe o loop while
            } else if (numeroGerado < numeroDigitado) {
                System.out.println("O número digitado é menor que o número gerado.");
            } else {
                System.out.println("O número digitado é maior que o número gerado.");
            }
        }

        if (tentativas == 5) {
            System.out.println("Você não conseguiu adivinhar o número em 5 tentativas. O número era " + numeroGerado);
            System.exit(0); // Indica uma saída bem-sucedida
        }
    }
}
