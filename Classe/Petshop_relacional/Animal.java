public class Animal {
    Integer IdAnimal;
    String Nome;
    String Raca;
    Double Peso;
    Categoria IdCategoria;
    Tutor IdTutor;

    public Animal (
        Integer IdAnimal, 
        String Nome, 
        String Raca, 
        Double Peso, 
        Categoria IdCategoria, 
        Tutor IdTutor
    ) {
        this.IdAnimal = IdAnimal;
        this.Nome = Nome;
        this.Raca = Raca;
        this.Peso = Peso;
        this.IdCategoria = IdCategoria;
        this.IdTutor = IdTutor;
    }
}
