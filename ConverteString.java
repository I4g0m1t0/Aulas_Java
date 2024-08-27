public class ConverteString {
    public static void main(String[] args) {
        String valor = "2.2";
        double doubleValor = Double.parseDouble(valor);
        int intValor = (int) doubleValor;

        System.out.println("O valor original era " + valor);
        System.out.println("O valor convertido para double ficou " + doubleValor);
        System.out.println("O valor convertido fica " + intValor);
    }
}