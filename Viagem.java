import java.util.Scanner;

public class Viagem {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite a distância percorrida em quilômetros: ");
            double km = scanner.nextDouble();
            System.out.println("Digite o tempo do trajeto em horas: ");
            double h = scanner.nextDouble();

            double viagem = km / h;
            System.out.println("A velocidade média da viagem foi de: " + viagem + " km/h");
        } catch (Exception e) {
            System.out.println("Valor inválido. Execute novamente");
        } finally {
            scanner.close();
        }
    }
}