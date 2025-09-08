public class Var {
    public static void main(String[] args) {
        // Java irá fazer a inferencia de tipo, funciona igual a declaracao explicita do tipo.
        var palavra = "Palavra";
        System.out.println(palavra.getClass());
        var numero1 = 5;
        var numero2 = 10;
        System.out.println(numero1+numero2);
    }
}
