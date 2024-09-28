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

            // Controller로 진입 전 App에서 로그인 체크먼저 하기.
            String forLoginCheck = controllerName + "/" + actionMethodName;

            switch (forLoginCheck) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    // 위 기능들은 로그인을 했을때만 가능하도록.
                    if(Controller.isLogined() == false) {
                        System.out.println("로그인이 필요합니다.");
                        continue;
                    }
                    break;
            }
            switch (forLoginCheck) {
                case "member/login":
                case "member/join":
                    // 위 기능들은 로그아웃 상태에서만 할 수 있도록.
                    if(Controller.isLogined()) {
                        System.out.println("로그아웃이 필요합니다");
                        continue;
                    }
                    break;
            }

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("사용불가 명령어 입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}
