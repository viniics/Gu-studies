package br.com.gubee.introducao.sealedclass;

public sealed interface Quadrilatero permits Quadrado, Retangulo {
    public double calcularArea();
}
