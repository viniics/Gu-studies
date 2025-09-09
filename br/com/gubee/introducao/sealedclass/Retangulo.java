package br.com.gubee.introducao.sealedclass;
public final record Retangulo(double base, double altura) implements Quadrilatero {
    @Override
    public double calcularArea() {
        return base * altura;
    }

}
