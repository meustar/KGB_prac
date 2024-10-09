package org.koreait.motivation.service;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;

public class MotivationService {

    private int lastMotivationId;
    private List<Motivation> motivations;

    public MotivationService() {
        lastMotivationId = 0;
        motivations = new ArrayList<>();
    }

    public int doAdd(String source, String body) {
        int id = lastMotivationId + 1;

        Motivation motivation = new Motivation(id, source, body);
        motivations.add(motivation);

        lastMotivationId = id;
        return id;
    }

    public void showList() {
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

    public int doDelete(Rq rq) {
        int id;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e ) {
            System.out.println("정수 입력 오류");
            return -1;
        }

        Motivation motivation = findById(id);

        if ( motivation == null) {
            System.out.println(id + "번 motivation은 없습니다.");
            return -2;
        }

        motivations.remove(motivation);

        return id;
    }

    public int doEdit(Rq rq) {
        int id;
        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e ) {

            return -1;
        }

        Motivation motivation = findById(id);

        if ( motivation == null) {
            System.out.println(id + "번 motivation은 없습니다.");
            return -2;
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

        return id;
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
