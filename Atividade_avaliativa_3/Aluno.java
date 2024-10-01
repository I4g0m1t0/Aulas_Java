public class Aluno {
    Integer IdAluno;
    String Nome;
    String DtNasc;
    String CPF;
    Curso IdCurso;

    public Aluno (
        Integer IdAluno,
        String Nome,
        String DtNasc,
        String CPF,
        Curso IdCurso
    ) {
        this.IdAluno = IdAluno;
        this.Nome = Nome;
        this.DtNasc = DtNasc;
        this.CPF = CPF;
        this.IdCurso = IdCurso;
    }
}
