//chamando as threads criadas
package threads;

public class UsarThreads {
	public static void main(String[] args) {
		MinhasThreads thread = new MinhasThreads("Thread 1", 100);
		MinhasThreads thread2 = new MinhasThreads("Thread 2", 200);
		MinhasThreads thread3 = new MinhasThreads("Thread 3", 300);
		MinhasThreads thread4 = new MinhasThreads("Thread 4", 400);
		MinhasThreads thread5 = new MinhasThreads("Thread 5", 500);
		MinhasThreads thread6 = new MinhasThreads("Thread 6", 600);
		MinhasThreads thread7 = new MinhasThreads("Thread 7", 700);
		MinhasThreads thread8 = new MinhasThreads("Thread 8", 800);
		MinhasThreads thread9 = new MinhasThreads("Thread 9", 900);
		MinhasThreads thread10 = new MinhasThreads("Thread 10", 1000);
	}
}
