import java.util.Scanner;

public class Strings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Digite qualquer cosia: ");
            String t1 = scanner.next();
            System.out.println("Digite mais alguma coisa: ");
            String t2 = scanner.next();

            if (t1.equals(t2)) {
                System.out.println("Os textos digitados são iguais (não ignorando maiúsculas/minúsculas).");
            } else {
                System.out.println("Os textos digitados são diferentes (não ignorando maiúsculas/minúsculas).");
            }

            if (t1.equalsIgnoreCase(t2)) {
                System.out.println("As strings são iguais (ignorando maiúsculas/minúsculas).");
            } else {
                System.out.println("Os textos digitados são diferentes (ignorando maiúsculas/minúsculas).");
            }
        } catch (Exception e){
            System.out.println("Valores inválidos digitados. Execute novamente");
        } finally {
            scanner.close();
        }
    }
}
