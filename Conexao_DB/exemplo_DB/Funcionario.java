import java.sql.Date;

public class Funcionario {
    public int id;
    public String nome;
    public String cpf;
    public Date dataNascimento;
    public String matricula;

    public Funcionario(
        int id,
        String nome,
        String cpf,
        Date dataNascimento,
        String matricula
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    

    public int getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getCpf() {
        return this.cpf;
    }
    public Date getDataNascimento() {
        return this.dataNascimento;
    }
    public String getMatricula() {
        return this.matricula;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId()
            + " - Nome: " + this.getNome()
            + " - CPF: " + this.getCpf()
            + " - Data de Nascimento: " + this.getDataNascimento()
            + " - Matricula: " + this.getMatricula();
    }

}
