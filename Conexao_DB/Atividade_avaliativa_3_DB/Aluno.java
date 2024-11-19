import java.sql.Date;

public class Aluno {
    Integer IdAluno;
    String Nome;
    Date DtNasc;
    String CPF;
    Curso Curso;

    public Aluno (
        Integer IdAluno,
        String Nome,
        Date DtNasc,
        String CPF,
        Curso Curso
    ) {
        this.IdAluno = IdAluno;
        this.Nome = Nome;
        this.DtNasc = DtNasc;
        this.CPF = CPF;
        this.Curso = Curso;
    }

    public void setIdAluno(int IdAluno){
        this.IdAluno = IdAluno;
    }
    
    public void setNomeAluno(String Nome){
        this.Nome = Nome;
    }
    
    public void setDtNascAluno(Date DtNasc){
        this.DtNasc = DtNasc;
    }
    
    public void setCpfAluno(String CPF){
        this.CPF = CPF;
    }
    
    public void setCurso(Curso Curso){
        this.Curso = Curso;
    }

    public int getIdAluno(){
        return this.IdAluno;
    }
    public String getNomeAluno(){
        return this.Nome;
    }
    public Date getDtNascAluno(){
        return this.DtNasc;
    }
    public String getCpfAluno(){
        return this.CPF;
    }
    public Curso getCurso(){
        return this.Curso;
    }

    @Override
    public String toString() {
        return "Id: " + this.getIdAluno()
            + " - Nome: " + this.getNomeAluno()
            + " - Data de nascimento: " + this.getDtNascAluno()
            + " - CPF: " + this.getCpfAluno()
            + " - Curso: " + this.getCurso()
            + " - Nome do curso: " + this.Curso.getNomeCurso();
    }
}
