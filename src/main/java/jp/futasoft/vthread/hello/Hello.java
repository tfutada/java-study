package jp.futasoft.vthread.hello;


public class Hello {
    static int numberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void main(String[] args) {
        System.out.println("Hello Java");

        System.out.println("!!! availableProcessors: " + numberOfCores());
    }
}