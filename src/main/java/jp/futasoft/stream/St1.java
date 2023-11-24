package jp.futasoft.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class St1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Use stream to filter, map, and collect results
        List<String> longNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(longNames); // Prints: [ALICE, CHARLIE, DAVID]
    }
}
