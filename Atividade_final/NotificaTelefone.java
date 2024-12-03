public class NotificaTelefone extends Notifica {
    private String telefoneDestino;

    public NotificaTelefone(int id, String texto, String telefoneDestino) {
        super(id, texto);
        this.telefoneDestino = telefoneDestino;
    }

    public String getTelefoneDestino() {
        return telefoneDestino;
    }

    public void setTelefoneDestino(String telefoneDestino) {
        this.telefoneDestino = telefoneDestino;
    }

    // Método para validar o formato do telefone (opcional)
    public boolean validarTelefone() {
        // Um exemplo simples de validação de número de telefone (a lógica pode ser expandida)
        return telefoneDestino != null && telefoneDestino.matches("\\+?\\d{10,15}");
    }

    @Override
    public void enviar() {
        // Valida o telefone antes de enviar
        if (validarTelefone()) {
            System.out.println("Enviando SMS para: " + telefoneDestino + " com texto: " + getTexto());
        } else {
            System.out.println("Erro: Número de telefone inválido.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Telefone destino: " + telefoneDestino;
    }
}
