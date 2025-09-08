package Introducao.RecordClass;

public record Animal(String nome, int idade, int alturaCM, int pesoKG) {
    public boolean isTall(){
        return alturaCM>=180 ? true:false;
    }

    boolean isHeavy(){
        return pesoKG>=300 ? true:false;
    }
}