package br.com.gubee.concurrency.src;

public class VirtualThreadPrinting {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            Thread vThread = Thread.ofVirtual().start(() -> {
                System.out.println("Ola" + Thread.currentThread());
            });
        }
    }
}