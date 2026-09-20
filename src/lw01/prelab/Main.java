import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner jpx = new Scanner(
            Main.class.getResourceAsStream("jobs.txt")
        );

        PrintJob[] jobs = new PrintJob[100];

        int count = 0;

        while (jpx.hasNext()) {

            String type = jpx.next();
            String id = jpx.next();
            int pages = jpx.nextInt();

            if (type.equals("MONO")) {
                jobs[count] = new MonoPrint(id, pages);
            } else if (type.equals("COLOUR")) {
                jobs[count] = new ColourPrint(id, pages);
            }

            count++;
        }

        jpx.close();

        for (int i = 0; i < count; i++) {
            System.out.println(jobs[i].summary());
        }
    }
}