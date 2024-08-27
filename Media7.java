import java.util.Scanner;

public class Media7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a 1 nota: ");
        double nota1 = scanner.nextDouble();
        System.out.println("Digite a 2 nota: ");
        double nota2 = scanner.nextDouble();
        System.out.println("Digite a 3 nota: ");
        double nota3 = scanner.nextDouble();

        double media = (nota1 + nota2 + nota3) / 3;

        if (media >= 7) {
            System.out.println("Aprovado");
        } else { 
            System.out.println("Reprovado");
        }

        scanner.close();
    }
}