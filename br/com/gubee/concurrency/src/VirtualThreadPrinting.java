package br.com.gubee.concurrency.src;

public class VirtualThreadPrinting {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
        thread.join();
    }
}