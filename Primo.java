import java.util.Scanner;

public class Primo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
        System.out.println("Digite um valor: ");
        double valor = scanner.nextDouble();
        int i,contador = 0;

        for (i = 1; i < valor + 1; i++){
            if (valor % i == 0) {
                contador += 1;
            }
        }
        if ((contador == 2) || (contador == 1)) {
            System.out.println("É primo.");
        } else {
            System.out.println("Não é primo.");
        }
        /* Solução do professor:
        boolean primo = true;
        int valor = scanner.nextInt();
        for (int i = 2; i < (valor - 1); i++) {
            if (valor % i == 0) {
                primo = false;
                break;
            }
        }

        if (primo) {
            System.out.println("É primo");
        } else {
            System.out.println("Não é primo");
        }*/
        } catch (Exception e){
            System.out.println("Valor inválido digitado. Execute novamente.");
        } finally{
            scanner.close();
        }
    }
}
