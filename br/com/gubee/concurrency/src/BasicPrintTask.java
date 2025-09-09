package br.com.gubee.concurrency.src;

public class BasicPrintTask implements Runnable {
    String threadName;

    public BasicPrintTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Hello " + threadName);
    }

}
