import java.util.Scanner;

public class Salario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try{
            System.out.println("Informe o seu salário: ");
            double salario = scanner.nextDouble();
            
            if (salario < 1903.98) {
                System.out.println("Isento de impostos");
            } else if (salario <= 2826.65) {
                System.out.println("O valor do imposto é de 7,5%");
            } else if (salario <= 3751.05) {
                System.out.println("O valor do imposto é de 15%");
            } else if (salario <= 4664.68) {
                System.out.println("O valor do imposto é de 22,5%");
            } else {
                System.out.println("O valor a pagar de imposto é de 27,5%");
            }
        } catch (Exception e) {
            System.out.println("Valor invalido digitado. Execute novamente.");
        } finally {
            scanner.close();
        }
    }
}
