import java.util.Scanner;

public class Maiorque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite o primeiro valor: ");
            double v1 = scanner.nextDouble();
            System.out.println("Digite o segundo valor: ");
            double v2 = scanner.nextDouble();

            if (v1 > v2) {
                System.out.println("O maior valor é: " + v1);
            } else {
                System.out.println("O maior vlaor é: " + v2);
            }
        } catch (Exception e){
            System.out.println("Você digitou valores inválidos, execute novamente");
        } finally {
            scanner.close();
        }
    }
}
