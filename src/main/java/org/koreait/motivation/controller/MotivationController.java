package org.koreait.motivation.controller;

import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MotivationController {

    int lastMotivationId;
    List<Motivation> motivations;
    Scanner sc;

    public MotivationController(Scanner sc) {
        this.sc = sc;
        lastMotivationId = 0;
        motivations = new ArrayList<>();
    }

    public void add() {
        int id = lastMotivationId + 1;
        System.out.print("source : ");
        String source = sc.nextLine();
        System.out.print("body : ");
        String body = sc.nextLine();

        Motivation motivation = new Motivation(id, source, body);
        motivations.add(motivation);

        System.out.println(id + "번 motivation이 등록 되었습니다.");
        lastMotivationId ++;



    }

    public void list() {
        if (motivations.isEmpty()) {
            System.out.println("등록된 motivation이 없습니다.");
            return;
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
