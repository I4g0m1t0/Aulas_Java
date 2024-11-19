public class Curso {
    Integer IdCurso;
    String Nome;
    Integer CH;
    Professor Professor;

    public Curso(
        Integer IdCurso,
        String Nome,
        Integer CH,
        Professor Professor
    ){
        this.IdCurso = IdCurso;
        this.Nome = Nome;
        this.CH = CH;
        this.Professor = Professor;
    }

    public void setIdCurso (int IdCurso){
        this.IdCurso = IdCurso;
    }
    
    public void setNomeCurso (String Nome){
        this.Nome = Nome;
    }
    
    public void setChCurso (int CH){
        this.CH = CH;
    }
    
    public void setProfessor (Professor Professor){
        this.Professor = Professor;
    }

    public int getIdCurso(){
        return this.IdCurso;
    }
    public String getNomeCurso(){
        return this.Nome;
    }
    public int getChCurso(){
        return this.CH;
    }
    public Professor getProfessor(){
        return this.Professor;
    }
    
    @Override
    public String toString() {
        return "Id: " + this.getIdCurso()
            + " - Nome: " + this.getNomeCurso()
            + " - Carga Horária: " + this.getChCurso()
            + " - Professor: " + this.getProfessor()
            + " - Nome do professor: " + this.Professor.getNomeProfessor();
    }
}