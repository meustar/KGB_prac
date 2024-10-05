package org.motivation;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        new App(sc).run();

        sc.close();
    }
}
