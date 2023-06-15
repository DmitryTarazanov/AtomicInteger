package src;

import java.util.Random;

import static src.Util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }


        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        palindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (isAscendingOrder(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();

        ascendingOrder.join();
        sameChar.join();
        palindrome.join();

        System.out.println("Красивых слов с длиной слова 3: " + counter3);
        System.out.println("Красивых слов с длиной слова 4: " + counter4);
        System.out.println("Красивых слов с длиной слова 5: " + counter5);

    }
}
