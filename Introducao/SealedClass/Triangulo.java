package Introducao.SealedClass;

//Não deve permitir.
public record Triangulo() implements Quadrilatero {

    @Override
    public double calcularArea() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularArea'");
    }

}
