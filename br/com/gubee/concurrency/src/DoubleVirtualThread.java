package br.com.gubee.concurrency.src;

public class DoubleVirtualThread {

    static Runnable task = () -> {
        System.out.println("Current Thread Id:" + Thread.currentThread().threadId());
    };

    public static void main(String[] args) throws InterruptedException {
        Thread.Builder builder = Thread.ofVirtual().name("worker-", 10);

        Thread t1 = builder.start(task);
        t1.join();
        System.out.println(t1.getName() + " finished");

        Thread t2 = builder.start(task);
        t2.join();
        System.out.println(t2.getName() + " finished");
    }
}
