package org.koreait.motivation.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.service.MotivationService;

public class MotivationController {

    private MotivationService motivationService;

    Rq rq;

    public MotivationController() {
        motivationService =  new MotivationService();
    }

    public void add() {
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();

        int id = motivationService.doAdd(source, body);

        System.out.println(id + "번 motivation이 등록 되었습니다.");

    }

    public void list() {
        motivationService.showList();
    }

    public void delete(Rq rq) {
        System.out.println(" == motivation delete == ");

        int id = motivationService.doDelete(rq);

        if ( id == -1) {
            System.out.println("정수 입력 오류");
            return;
        } else if (id == -2) {
            System.out.println("해당 motivation은 없습니다.");
            return;
        }

        System.out.println(id + "번 motivation을 삭제했습니다.");
    }

    public void edit(Rq rq) {
        System.out.println(" == motivation edit == ");

        int id = motivationService.doEdit(rq);

        if ( id == -1) {
            System.out.println("정수 입력 오류");
            return;
        } else if (id == -2) {
            System.out.println("해당 motivation은 없습니다.");
            return;
        }
        System.out.println(id + "번 motivation을 수정했습니다.");
    }
}
