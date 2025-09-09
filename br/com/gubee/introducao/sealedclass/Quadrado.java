package br.com.gubee.introducao.sealedclass;

public final record Quadrado(double lado) implements Quadrilatero {

    @Override
    public double calcularArea() {
        return lado * lado;
    }

}
