import java.util.Scanner;

public class Volume {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Digite o comprimento: ");
            double lado1 = scanner.nextDouble();
            System.out.println("Digite a altura: ");
            double lado2 = scanner.nextDouble();
            System.out.println("Digite a profundidade: ");
            double lado3 = scanner.nextDouble();
    
            double volume = lado1 * lado2 * lado3;
            System.out.println("A área é: " + volume);
        } catch (Exception e) {
           System.out.println("Você digitou valores inválidos, execute novamente."); 
        } finally {
            scanner.close();
        }

    }
}
