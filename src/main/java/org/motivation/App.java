package org.motivation;

import java.util.Scanner;

public class App {

    private Scanner sc;
    private int lastmotivationId = 1;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println(" == Motivation 실행 == ");
        while (true) {
            System.out.print("명령어)  ");
            String cmd = sc.nextLine().trim();
            int id = lastmotivationId;

            if (cmd.equals("exit")) {
                System.out.println(" == Motivation 종료 == ");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            if (cmd.equals("add")) {
                System.out.print("motivation : ");
                String motivation = sc.nextLine();
                System.out.print("source : ");
                String source = sc.nextLine();
                System.out.println(id + "번 motivation이 등록 되었습니다.");
                lastmotivationId ++;
            }
        }

    }
}
