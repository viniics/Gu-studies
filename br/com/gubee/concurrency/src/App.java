package br.com.gubee.concurrency.src;
public class App {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new BasicPrintTask("Primeira"));
        Thread thread2 = new Thread(new BasicPrintTask("Segunda"));
        Thread thread3 = new Thread(new BasicPrintTask("Terceira"));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
