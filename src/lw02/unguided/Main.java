package unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();


        Scanner jpx = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (jpx.hasNext()) {

            String[] request = new String[2];

            request[0] = jpx.next();
            request[1] = jpx.next();

            requests.add(request);
        }

        jpx.close();

        String[] kalkulus = {"Kalkulus", "2"};
        String[] fisika = {"Fisika", "1"};
        String[] statistika = {"Statistika", "2"};

        books.add(kalkulus);
        books.add(fisika);
        books.add(statistika);

        for (String[] request : requests) {

            String name = request[0];

            boolean found = false;

            for (String[] member : members) {

                if (member[0].equals(name)) {
                    found = true;
                    break;
                }
            }

            if (!found) {

                String[] member = new String[2];

                member[0] = name;
                member[1] = "0";

                members.add(member);
            }
        }

        queue.addAll(requests);

        LinkedList<String[]> successful = new LinkedList<>();

        while (!queue.isEmpty()) {

            String[] request = queue.poll();

            String name = request[0];
            String bookTitle = request[1];

            String[] book = null;
            String[] member = null;


            for (String[] data : books) {

                if (data[0].equals(bookTitle)) {

                    book = data;
                    break;
                }
            }

            for (String[] data : members) {

                if (data[0].equals(name)) {

                    member = data;
                    break;
                }
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);


            if (stock > 0 && borrowed < 2) {
                stock--;
                book[1] = String.valueOf(stock);
                borrowed++;
                member[1] = String.valueOf(borrowed);
                successful.add(request);

            } else {
                failed.push(request);
            }
        }
        System.out.println("=== Successful Requests ===");

        for (String[] request : successful) {

            System.out.println(
                request[0] + " " + request[1]
            );
        }

        System.out.println("=== Remaining Book Stock ===");

        for (String[] book : books) {

            System.out.println(
                book[0] + ": " + book[1]
            );
        }


        System.out.println("=== Failed Requests ===");

        while (!failed.isEmpty()) {

            String[] request = failed.pop();

            System.out.println(
                request[0] + " " + request[1]
            );
        }
    }
}
