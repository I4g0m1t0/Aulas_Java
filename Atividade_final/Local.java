import java.util.ArrayList;

public class Local {
    private int id;
    private String descricao;
    private int vagas;
    private ArrayList<Evento> eventos;

    public Local(int id, String descricao, int vagas) {
        this.id = id;
        this.descricao = descricao;
        this.vagas = vagas;
        this.eventos = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void adicionarEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void removerEvento(Evento evento) {
        this.eventos.remove(evento);
    }

    @Override
    public String toString() {
        StringBuilder eventosStr = new StringBuilder();
        for (Evento evento : eventos) {
            eventosStr.append(evento.getDescricao())
                    .append(" em ")
                    .append(evento.getData())
                    .append(" organizado por ")
                    .append(evento.getOrganizador().getNome())
                    .append("\n");
        }
        return "Local [ID: " + id +
            ", Descrição: " + descricao +
            ", Vagas: " + vagas +
            "]\nEventos:\n" +
            (eventos.isEmpty() ? "Nenhum evento cadastrado." : eventosStr.toString());
    }
}
