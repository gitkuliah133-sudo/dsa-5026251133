package lw02;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        LinkedList<String[]> transaksi = new LinkedList<>();
        LinkedList<String[]> customer = new LinkedList<>();

        Scanner file = new Scanner(new File("transactions.txt"));

        // Membaca data transaksi
        while (file.hasNextLine()) {
            String baris = file.nextLine();
            String[] data = baris.split(" ");

            transaksi.add(data);

            // Mengecek apakah customer sudah ada
            boolean ada = false;

            for (String[] c : customer) {
                if (c[0].equals(data[0])) {
                    ada = true;
                    break;
                }
            }

            // Menambahkan customer baru
            if (!ada) {
                String[] c = {data[0], "0"};
                customer.add(c);
            }
        }

        file.close();

        // Memasukkan transaksi ke Queue
        Queue<String[]> antrian = new LinkedList<>();

        for (String[] t : transaksi) {
            antrian.add(t);
        }

        // Stack untuk transaksi yang gagal
        Stack<String[]> gagal = new Stack<>();

        // Memproses transaksi
        while (!antrian.isEmpty()) {
            String[] t = antrian.poll();

            String nama = t[0];
            String jenis = t[1];
            int jumlah = Integer.parseInt(t[2]);

            for (String[] c : customer) {
                if (c[0].equals(nama)) {
                    int saldo = Integer.parseInt(c[1]);

                    if (jenis.equals("DEPOSIT")) {
                        saldo = saldo + jumlah;
                        c[1] = String.valueOf(saldo);
                    } else if (jenis.equals("WITHDRAW")) {
                        if (saldo >= jumlah) {
                            saldo = saldo - jumlah;
                            c[1] = String.valueOf(saldo);
                        } else {
                            gagal.push(t);
                        }
                    }

                    break;
                }
            }
        }

        // Menampilkan saldo akhir
        System.out.println("=== Final Balances ===");

        for (String[] c : customer) {
            System.out.println(c[0] + " : " + c[1]);
        }

        // Menampilkan transaksi gagal
        System.out.println();
        System.out.println("=== Failed Transactions ===");

        while (!gagal.isEmpty()) {
            String[] t = gagal.pop();

            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}