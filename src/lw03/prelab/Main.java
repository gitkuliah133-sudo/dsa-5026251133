package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner playlistFile = new Scanner(
                Main.class.getResourceAsStream("playlist.txt")
        );

        List<String> playlist = new ArrayList<>();

        while (playlistFile.hasNextLine()) {
            String command = playlistFile.next();

            if (command.equals("ADD")) {
                String song = playlistFile.nextLine().trim();
                playlist.add(song);

            } else if (command.equals("INSERT")) {
                int index = playlistFile.nextInt();
                String song = playlistFile.nextLine().trim();
                playlist.add(index, song);

            } else if (command.equals("REMOVE")) {
                String song = playlistFile.nextLine().trim();

                if (playlist.contains(song)) {
                    playlist.remove(song);
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        Scanner participantsFile = new Scanner(
                Main.class.getResourceAsStream("participants.txt")
        );

        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        while (participantsFile.hasNextLine()) {
            String name = participantsFile.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: "
                + duplicateRegistrations);

        Scanner inventoryFile = new Scanner(
                Main.class.getResourceAsStream("inventory.txt")
        );

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        while (inventoryFile.hasNextLine()) {
            String type = inventoryFile.next();
            String product = inventoryFile.next();
            int quantity = inventoryFile.nextInt();

            if (type.equals("ADD")) {

                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);
                    inventory.put(product, stock + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);

                    if (stock >= quantity) {
                        inventory.put(product, stock - quantity);
                    } else {
                        failedSales++;
                    }

                } else {
                    failedSales++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");

        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}