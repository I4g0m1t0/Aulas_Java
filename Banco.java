import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o valor que você deseja sacar: ");
        int valor = scanner.nextInt();

        int total = valor;
        int ced = 100;
        int totced = 0;
        
        while (total > 0) {
            if (total >= ced) {
                total -= ced;
                totced += 1;
            } else {
                if (totced > 0) {
                    System.out.println("Total de " + totced + " cédulas de " + ced + " reais.");
                }
                // Mudar para a próxima denominação de cédula
                if (ced == 100) {
                    ced = 50;
                } else if (ced == 50) {
                    ced = 10;
                } else if (ced == 10) {
                    ced = 5;
                } else if (ced == 5) {
                    ced = 1;
                }
                totced = 0; // Resetar o contador de cédulas
            }
        }

        // Imprimir cédulas restantes de 1 real
        if (totced > 0) {
            System.out.println("Total de " + totced + " cédulas de " + ced + " reais.");
        }
    scanner.close();
}
}
