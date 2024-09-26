package org.example.articleManager;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null;

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0];

            if (cmdBits.length == 1) {
                System.out.println("명령어를 다시 확인해주세요.");
                continue;
            }

            String actionMethodName = cmdBits[1];

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("사용불가 명령어 입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
//            if (cmd.equals("member join")) {
//                memberController.dojoin();
//            } else if (cmd.equals("article write")) {
//                articleController.doWrite();
//            } else if (cmd.startsWith("article list")) {
//                articleController.showList(cmd);
//            } else if (cmd.startsWith("article detail")) {
//                articleController.showDetail(cmd);
//            } else if (cmd.startsWith("article delete")) {
//                articleController.doDelete(cmd);
//            } else if (cmd.startsWith("article modify")) {
//                articleController.doModify(cmd);
//            } else {
//                System.out.println("사용할 수 없는 명령어 입니다.");
//            }
        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}
