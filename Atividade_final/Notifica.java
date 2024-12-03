public class Notifica {
    private int id;
    private String texto;

    // Construtor
    public Notifica(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    // Método de envio de notificação (pode ser ajustado conforme necessidade)
    public void enviar() {
        System.out.println("Enviando notificação: " + texto);
    }

    @Override
    public String toString() {
        return "Notificação [ID: " + id + ", Texto: " + texto + "]";
    }

    // Método para mapear ID para notificação padrão (caso haja algum status padrão)
    public static Notifica fromInt(int id) {
        switch (id) {
            case 1:
                return new Notifica(id, "Notificação ativa");
            case 2:
                return new Notifica(id, "Notificação pendente");
            default:
                return new Notifica(id, "Sem notificação configurada");
        }
    }

    // Novo método para associar o ID ao objeto após inserção no banco de dados
    public void setIdFromDatabase(int id) {
        this.id = id;
    }
}
