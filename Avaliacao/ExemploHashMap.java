import java.util.HashMap;

public class ExemploHashMap {
    public static void main(String[] args) {
        // Criando um HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // Adicionando elementos ao HashMap
        map.put("Maçã", 10);
        map.put("Banana", 20);
        map.put("Laranja", 30);
        map.put("Manga", 40);

        // Acessando elementos do HashMap
        System.out.println("Quantidade de Maçãs: " + map.get("Maçã"));

        // Removendo um elemento do HashMap
        map.remove("Banana");

        // Verificando se um elemento existe
        if (map.containsKey("Laranja")) {
            System.out.println("Laranja está no mapa.");
        }

        // Iterando sobre o HashMap
        for (String key : map.keySet()) {
            System.out.println("Fruta: " + key + ", Quantidade: " + map.get(key));
        }
    }
}
