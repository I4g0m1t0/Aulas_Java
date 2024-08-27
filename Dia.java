import java.util.Scanner;

public class Dia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número de 1 a 12: ");
        int dia = scanner.nextInt();

        switch (dia) {
            case 1:
                System.out.println("Este valor corresponde ao mês de Janeiro!");
                break;
            case 2:
                System.out.println("Este valor correspode ao mês de Fevereiro!");
                break;
            case 3:
                System.out.println("Este valor corresponde ao mês de Março!");
                break;
            case 4:
                System.out.println("Este valor correspode ao mês de Abril!");
                break;
            case 5:
                System.out.println("Este valor corresponde ao mês de Maio!");
                break;
            case 6:
                System.out.println("Este valor correspode ao mês de Junho!");
                break;
            case 7:
                System.out.println("Este valor corresponde ao mês de Julho!");
                break;
            case 8:
                System.out.println("Este valor correspode ao mês de Agosto!");
                break;
            case 9:
                System.out.println("Este valor correspode ao mês de Setebmbro!");
                break;
            case 10:
                System.out.println("Este valor correspode ao mês de Outubro!");
                break;
            case 11:
                System.out.println("Este valor correspode ao mês de Novembro!");
                break;
            case 12:
                System.out.println("Este valor correspode ao mês de Dezembro!");
                break;
            default:
                System.out.println("Não leu o enunciado?");
                break;
        }
        scanner.close();
    }
}
