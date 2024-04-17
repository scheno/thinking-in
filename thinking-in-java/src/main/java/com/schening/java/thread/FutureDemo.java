package com.schening.java.thread;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> result = Executors.newFixedThreadPool(1).submit(() -> "hello");
        System.out.println(result.get());

        App app = new App();
        app.showSearch("张三");
    }

    interface ArchiveSearcher {
        String search(String target);
    }

    static class App {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        ArchiveSearcher searcher = (target) -> "Search " + target + " in MySQL";

        void showSearch(final String target) throws InterruptedException {
            Future<String> future = executor.submit(() -> searcher.search(target));
            displayOtherThings();
            // do other things while searching
            try {
                displayText(future.get());
                // use future
            } catch (
                    ExecutionException ex) {
                cleanup();
                return;
            }
        }

        private void displayOtherThings() {
            System.out.println("displayOtherThings...");
        }

        private void displayText(String s) {
            System.out.println(s + "...");
        }

        private void cleanup() {
            System.out.println("cleanup...");
        }
    }
}
