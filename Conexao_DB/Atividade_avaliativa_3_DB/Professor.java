public class Professor {
    Integer IdProfessor;
    String Nome;
    String Departamento;
    
    public Professor (
        Integer IdProfessor, 
        String Nome, 
        String Departamento
    ) {
        this.IdProfessor = IdProfessor;
        this.Nome = Nome;
        this.Departamento = Departamento;
    }

    public void setIdProfessor (int IdProfessor) {
        this.IdProfessor = IdProfessor;
    }

    public void setNomeProfessor (String Nome) {
        this.Nome = Nome;
    }
    
    public void setDepartamento (String Departamento) {
        this.Departamento = Departamento;
    }

    public int getIdProfessor() {
        return this.IdProfessor;
    }
    
    public String getNomeProfessor() {
        return this.Nome;
    }
    
    public String getDepartamento() {
        return this.Departamento;
    }

    @Override
    public String toString() {
        return "Id: " + this.getIdProfessor()
            + " - Nome: " + this.getNomeProfessor()
            + " - Departamento: " + this.getDepartamento();
    }
}
