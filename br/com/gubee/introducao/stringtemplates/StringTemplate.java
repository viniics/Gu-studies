package br.com.gubee.introducao.stringtemplates;

public class StringTemplate {
    public static void main(String[] args) {
        var nomePessoa = (new Pessoa("Marcelo")).nome();
        var result = STR."Bom dia \{nomePessoa}";
    }
}

record Pessoa(String nome) {
}
