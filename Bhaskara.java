import java.util.Scanner;

public class Bhaskara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite o valor de a: ");
            double a = scanner.nextDouble();
            System.out.println("Digite o valor de b: ");
            double b = scanner.nextDouble();
            System.out.println("Digite o valor de c: ");
            double c = scanner.nextDouble();

            double delta = (Math.pow(b, 2)) - (4 * a * c);
            double raizDelta = Math.sqrt(delta);
            double x1 = ((-b) + raizDelta) / 2 * a;
            double x2 = ((-b) - raizDelta) / 2 * a;

            if (delta > 0){
                System.out.println("O valor de x' é de: " + x1);
                System.out.println("O valor de x'' é de: " + x2);
            } else{
                if (delta == 0) {
                    System.out.println("O valor de x' é de: " + x1);
                }
                else {
                    System.out.println("Não possui raiz real.");
                }
            }

        } catch (Exception e) {
            System.out.println("Você digitou valores inválidos, execute novamente");
        } finally {
            scanner.close();
        }
    }
}
