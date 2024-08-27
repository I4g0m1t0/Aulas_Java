package Exercicio4;
import java.util.Scanner;

public class ArrayNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite quantas notas serão contabilizadas: ");
        int tam = scanner.nextInt();
        double[] notas = new double[tam];
        double soma = 0;

        for(int i = 0; i < notas.length; i++){
            System.out.println("Digite a " + (i+1) + "ª nota: ");
            notas[i] = scanner.nextDouble();
            soma += notas[i];
        }

        double media = soma/tam;
        System.out.println("A média final é de: " + media);
        scanner.close();
    }
}
