public class Curso {
    Integer IdCurso;
    String Nome;
    Integer CH;
    Professor IdProfessor;

    public Curso(
        Integer IdCurso,
        String Nome,
        Integer CH,
        Professor IdProfessor
    ){
        this.IdCurso = IdCurso;
        this.Nome = Nome;
        this.CH = CH;
        this.IdProfessor = IdProfessor;
    }
    
}