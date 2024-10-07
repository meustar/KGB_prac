package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.system.controller.SystemController;

public class App {

    public void run() {
        System.out.println(" == Motivation 실행 == ");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (true) {
            System.out.print("명령어)  ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.equals("exit")) {
                systemController.exit();
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            }
        }
    }
}
