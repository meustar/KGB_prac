package org.motivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;
    private int lastMotivationId = 0;

    List<Motivation> motivations = new ArrayList<>();

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println(" == Motivation 실행 == ");
        while (true) {
            System.out.print("명령어)  ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println(" == Motivation 종료 == ");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            if (cmd.equals("add")) {
                int id = lastMotivationId + 1;
                System.out.print("source : ");
                String source = sc.nextLine();
                System.out.print("body : ");
                String body = sc.nextLine();

                Motivation motivation = new Motivation(id, source, body);

                System.out.println(id + "번 motivation이 등록 되었습니다.");
                lastMotivationId ++;

                motivations.add(motivation);


            } else if (cmd.equals("list")) {
                if (motivations.isEmpty()) {
                    System.out.println("등록된 motivation이 없습니다.");
                    continue;
                }
                System.out.println(" == motivation list == ");
                System.out.print("   id   //        source        //              body            \n");
                System.out.println("=".repeat(50));

                for (int i = motivations.size() - 1; i >= 0; i--) {
                    Motivation motivation = motivations.get(i);

                    if (motivation.getSource().length() > 7) {
                        System.out.printf("    %d   //       %s       //        %s          \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                        continue;
                    }

                    System.out.printf("    %d   //       %s       //        %s          \n", motivation.getId(), motivation.getSource(), motivation.getBody());
                }
            }
        }
    }
}
