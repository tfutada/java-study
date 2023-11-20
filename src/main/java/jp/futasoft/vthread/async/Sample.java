package jp.futasoft.vthread.async;

import java.util.concurrent.CompletableFuture;

public class Sample {
	public static int compute(int n) {
		return n * 2;
	}

	// Fires off the async task immediately and uses the common fork-join pool by default
	// (unless you provide a custom executor). It's eager execution.
	public static CompletableFuture<Integer> create(int n) {
		return CompletableFuture.supplyAsync(() -> compute(n));
	}

	public static void main(String[] args) {
		var job = create(4)
				.thenApply(data -> data + 1)
				.thenAccept(System.out::println);

		job.join(); // This will block until the CompletableFuture is complete.

		System.out.println("Done!!!");
	}
}
