package br.com.vinicius;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        var carro = new Carro("GranTurismo", "Maserati", 2015);
        var gson = new Gson();
        System.out.println(gson.toJson(carro));
    }


    record Carro(String nome, String marca, Integer ano) {
    }
}