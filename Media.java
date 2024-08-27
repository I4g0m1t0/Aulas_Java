import java.util.Scanner;

public class Media {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Digite a primeira nota: ");
            double nota1 = scanner.nextDouble();
            System.out.println("Digite a segunda nota: ");
            double nota2 = scanner.nextDouble();
            System.out.println("Digite a terceira nota: ");
            double nota3 = scanner.nextDouble();
    
            double media = (nota1 + nota2 + nota3) / 3;
            System.out.println("A média é: " + media);
        } catch (Exception e) {
           System.out.println("Você digitou valores inválidos, execute novamente."); 
        } finally {
            scanner.close();
        }

    }
}
