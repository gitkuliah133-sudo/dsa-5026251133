package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner jpx = new Scanner(Main.class.getResourceAsStream("enrollment.txt")
        );

        Map<String, Integer> courses = new LinkedHashMap<>();
        int rejectedOperations = 0;

        List<String> checkResults = new ArrayList<>();

        while (jpx.hasNextLine()) {
            String operation = jpx.next();

            if (operation.equals("REGISTER")) {
                String course = jpx.next();
                int count = jpx.nextInt();

                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (courses.containsKey(course)) {
                        courses.put(course, courses.get(course) + count);
                    } else {
                        courses.put(course, count);
                    }
                }

            } else if (operation.equals("WITHDRAW")) {
                String course = jpx.next();
                int count = jpx.nextInt();

                if (count <= 0) {
                    rejectedOperations++;
                } else if (courses.containsKey(course)) {
                    int current = courses.get(course);
                    if (current >= count) {
                        courses.put(course, current - count);
                    } else {
                        rejectedOperations++;
                    }
                } else {
                    rejectedOperations++;
                }

            } else if (operation.equals("CHECK")) {
                String course = jpx.next();

                if (courses.containsKey(course)) {
                    checkResults.add(course + ": " + courses.get(course) + " students");
                } else {
                    checkResults.add(course + ": Not found");
                }
            }
        }

        System.out.println("==== Enrollment Checks ====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("===== Final Enrollment ====");
        for (String course : courses.keySet()) {
            System.out.println(course + ": " + courses.get(course) + " students");
        }
        System.out.println(" ");
        System.out.println("Rejected operations: " + rejectedOperations);

        jpx.close();
    }
}
