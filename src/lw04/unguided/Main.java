package lw04.unguided;

import java.util.*;

public class Main {

    public static void selectionSort(int[] arr, int passes) {

        for (int i = 0; i < passes && i < arr.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static int linearSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner jpx = new Scanner(
            Main.class.getResourceAsStream("cards2.txt")
        );

        int n = jpx.nextInt();

        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = jpx.nextInt();
        }

        int m = jpx.nextInt();

        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = jpx.nextInt();
        }

        jpx.close();

        selectionSort(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i]);

            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < m; i++) {

            int index = linearSearch(cards, targets[i]);

            if (index != -1) {
                System.out.println(
                    targets[i] + ": FOUND at index " + index
                );
            } else {
                System.out.println(
                    targets[i] + ": NOT FOUND"
                );
            }
        }
    }
}
