import java.util.Scanner;

public class Imposto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
        System.out.println("Digite o valor faturado: ");
        double faturado = scanner.nextDouble();
        System.out.println("Digite o valor pago em impostos: ");
        double imposto = scanner.nextDouble();

        double percentual = imposto/faturado * 100;

        System.out.println("O percentual de imposto pago foi de: " + percentual + "%");
        } catch (Exception e){
            System.out.println("Valor inválido. Execute novamente");
        } finally {
            scanner.close();
        }
    }
}
