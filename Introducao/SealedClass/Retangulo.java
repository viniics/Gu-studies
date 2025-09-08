package Introducao.SealedClass;

public final record Retangulo(double base, double altura) implements Quadrilatero {
    @Override
    public double calcularArea() {
        return base * altura;
    }

}
