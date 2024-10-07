package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.system.controller.SystemController;

public class App {

    byte system_status = 1;

    public void run() {
        System.out.println(" == Motivation 실행 == ");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (system_status == 1) {
            System.out.print("명령어)  ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.isEmpty()) {
                System.out.println("명령어를 입력해주세요");
                continue;
            }

            Rq rq = new Rq(cmd);

            if (rq.getErrMsg().equals("오타가 있습니다.(id)")) {
                continue;
            }

            switch (rq.getActionMethod()) {
                case "exit":
                    systemController.exit();
                    system_status = 0;
                    break;
                case "add":
                    motivationController.add();
                    break;
                case "list":
                    motivationController.list();
                    break;
                case "delete":
                    motivationController.delete(rq);
                    break;
                case "edit":
                    motivationController.edit(rq);
                    break;
                default:
                    System.out.println("사용할 수 없는 명령어 입니다.");
            }
        }
    }
}
