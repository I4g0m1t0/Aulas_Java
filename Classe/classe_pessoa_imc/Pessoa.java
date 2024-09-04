public class Pessoa {
    String nome;
    Double peso;
    Double altura;
    String dtNasc;

    public Pessoa(
        String nome,
        Double peso,
        Double altura,
        String dtNasc
    ){
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.dtNasc = dtNasc;
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }
}
