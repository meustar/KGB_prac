package org.koreait.motivation.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;
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

        int id = motivationService.add(source, body);

        System.out.println(id + "번 motivation이 등록 되었습니다.");

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

    public void delete(Rq rq) {
        System.out.println(" == motivation delete == ");
        int id;
        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e ) {
            System.out.println("정수 입력 오류");
            return;
        }

        Motivation motivation = findById(id);

        if ( motivation == null) {
            System.out.println(id + "번 motivation은 없습니다.");
            return;
        }

        motivations.remove(motivation);
        System.out.println(id + "번 motivation을 삭제했습니다.");
    }

    public void edit(Rq rq) {
        System.out.println(" == motivation edit == ");
        int id;
        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e ) {
            System.out.println("정수 입력 오류");
            return;
        }

        Motivation motivation = findById(id);

        if ( motivation == null) {
            System.out.println(id + "번 motivation은 없습니다.");
            return;
        }
        // 불러온 motivation의 인스턴스 변수에 접근.
        System.out.println("source : " + motivation.getSource());
        System.out.println("body : " + motivation.getBody());

        System.out.print("new source : ");
        String source = Container.getScanner().nextLine();
        System.out.print("new body : ");
        String body = Container.getScanner().nextLine();

        // motivation의 인스턴스변수 수정.
        motivation.setSource(source);
        motivation.setBody(body);

        System.out.println(id + "번 motivation을 수정했습니다.");
    }

    // 입력된 id와 일치하는 motivation 찾기
    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            if(motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }
}
