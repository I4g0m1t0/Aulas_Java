import java.util.Scanner;

public class ParImpar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite um valor e veja a mágica!");
            double valor = scanner.nextDouble();

            if (valor % 2 == 0) {
                System.out.println("O valor digitado é par");
            } else {
                System.out.println("O valor digitado é ímpar");
            }
        } catch (Exception e) {
            System.out.println("Valor inválido digitado. Execute novamente");
        } finally {
            scanner.close();
        }
    }
}
