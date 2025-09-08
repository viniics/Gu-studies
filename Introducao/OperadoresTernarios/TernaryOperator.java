package Introducao.OperadoresTernarios;

class TernaryOperator {
    public static void main(String[] args) {
        System.out.println(imparOuPar(1));
        
        System.out.println(imparOuPar(99990));
        
        System.out.println(isAprovadoNaOab(10, 21));
        
        System.out.println(isAprovadoNaOab(10, 10));
        
        System.out.println(isAprovadoNaOab(40, 80));

        System.out.println(descontoNoPedido(152.8));

        System.out.println(descontoNoPedido(10));

    }

    static String imparOuPar(int numero) {
        var output = (numero % 2 == 0) ? "PAR" : "IMPAR";
        return output;
    }

    // Para ser aprovado na OAB, o candidato precisa acertar no mínimo 50% das questões
    static String isAprovadoNaOab(int numeroDeAcertos, int numeroDeQuestoes) {
        var output = ((float) numeroDeAcertos / numeroDeQuestoes >= 0.5) ? "APROVADO" : "REPROVADO";
        return output;
    }

    // Para compras acima de R$ 100, conceder um desconto de 20%
    // Para compras abaixo dos R$ 100, fornecer 5% de desconto
    static double descontoNoPedido(double valorPedido){
        var finalPrice = valorPedido>100 ? valorPedido*0.8 : valorPedido*0.95;
        return finalPrice;
    }
}