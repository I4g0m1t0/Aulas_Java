import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite o seu nome: ");
            String nome = scanner.next();
            System.out.println("Digite o seu peso em Kg: ");
            Double peso = scanner.nextDouble();
            System.out.println("Digite a sua altura (ex.: 1,70): ");
            Double altura = scanner.nextDouble();
            System.out.println("Digite a sua data de nascimento: ");
            String dtNasc = scanner.next();
            
            Pessoa pessoa = new Pessoa(nome, peso, altura, dtNasc);
            
            double valorIMC = pessoa.calcularIMC();

             // Classificação do IMC
            if (valorIMC < 18.5) {
                System.out.println("Abaixo do Peso");
            } else if (valorIMC <= 24.9) {
                System.out.println("Peso Ideal");
            } else if (valorIMC <= 29.9) {
                System.out.println("Levemente acima do peso");
            } else if (valorIMC <= 34.9) {
                System.out.println("Obesidade Grau I");
            } else if (valorIMC <= 39.9) {
                System.out.println("Obesidade Grau II");
            } else if (valorIMC >= 40.0) {
                System.out.println("Obesidade Grau III (mórbida)");
            }
        } catch(Exception e){
            System.out.println("Você digitou valores inválidos. Execute novamente");
        } finally {
            scanner.close();
        }
    }
}
