package Introducao.PatternMatching;

public class PatternMatching {
     public static void main(String[] args) {
        imprimeSeForString("Java");
        imprimeSeForString(false);
     }

     static void imprimeSeForString(Object o){
        if(o instanceof String s){
            System.out.println(s+" É String");
        }
        else{
            System.out.println("Operacao inválida");
        }
     }
}
