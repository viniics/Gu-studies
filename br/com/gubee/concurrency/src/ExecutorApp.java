import java.util.concurrent.*;

public class ExecutorApp {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 5; i++) {
                int num = i;
                executor.submit(() -> {
                    System.out.println("Tarefa " + num + " rodando em " + Thread.currentThread());
                });
            }
        }
    }
}
