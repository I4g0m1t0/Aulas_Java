package Exercicio4;

import java.util.Scanner;

public class ImprimeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] array = new double[10];

        for (int i = 0; i< array.length; i++){
            System.out.println("Digite o " + (i+1) + "º valor: ");
            array[i] = scanner.nextDouble();
        }

        for (double d : array) {
            System.out.println(d);
        }

        for (int i = 0; i< array.length; i++){
            System.out.println("O  " + (i+1) + "º valor digitado foi: " + array[i]);
        }

        scanner.close();
    }
}
