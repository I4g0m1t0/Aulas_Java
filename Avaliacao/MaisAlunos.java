import java.util.HashMap;
import java.util.Scanner;

public class MaisAlunos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando um Hashmap
        HashMap<String, Character> map = new HashMap<>();

        String aluno, continuar;
        char nota;

        do {
            System.out.println("Digite o nome do aluno: ");
            aluno = scanner.nextLine();
            System.out.println("Digite o conceito do aluno: ");
            nota = scanner.next().charAt(0);
            
            //Joga valores para o Map
            map.put(aluno, nota);

            System.out.println("Deseja adicionar outro aluno? (s/n)");
            continuar = scanner.next();
            scanner.nextLine(); // Limpa o buffer do scanner

        } while (continuar.equalsIgnoreCase("s"));

        //Exibir o Hashmap
        System.out.println("\nAlunos e conceitos inseridos:");
        for (String key : map.keySet()) { // Tipo da chave deve ser String, não char
            System.out.println("Aluno: " + key + ", Conceito: " + map.get(key));
        }

        scanner.close();
    }
}