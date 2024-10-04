package org.example.controller;

import org.example.articleManager.Container;
import org.example.dto.Member;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
//    private List<Member> members;
    private String cmd;

    private MemberService memberService;

    private int lastMemberId = 3;

    public MemberController(Scanner sc) {
        this.memberService = Container.memberService;
        members = Container.memberDao.members;
        this.sc = sc;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                dojoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }


    private void doLogout() {
        loginedMember = null;
        System.out.println("로그아웃 되었습니다.");
    }

    private void doLogin() {
        System.out.println("== 로그인 ==");
        System.out.print("로그인 아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String loginPw = sc.nextLine();

        // 회원가입이 되어있는지 확인. -> 사용자가 입력한 로그인 아이디와 일치하는 회원이 가입되어 있는지 확인
        Member member = getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }
        // 회원가입된 회원이라면 -> 비밀번호가 일치하는지 확인
        if (member.getLoginPw().equals(loginPw) == false){
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }
        loginedMember = member; // 누가 로그인 했는지 저장

        System.out.printf("%s님 로그인 성공\n", member.getName());

    }


    private void dojoin() {
        System.out.println("== 회원 가입==");
        int id = lastMemberId + 1;
        String regDate = Util.getNow();

        String loginId = null;
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isjoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중인 아이디 입니다.");
                continue;
            }
            break;
        }
        String loginPw = null;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("비밀번호를 다시 확인해주세요.");
                continue;
            }
            break;
        }
        System.out.print("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        memberService.add(member);

        System.out.println(id + "번 회원이 가입되었습니다.");
        lastMemberId++;
    }

    private boolean isjoinableLoginId(String loginId) {
        for (Member member : members) {
            if(member.getLoginId().equals(loginId)){
                return false;
            }
        }
        return true;
    }

    private Member getMemberByLoginId(String loginId) {
        for (Member member : members) {
            if(member.getLoginId().equals(loginId)){
                return member;
            }
        }
        return null;
    }

    public void makeTestData() {
        System.out.println("회원 테스트 데이터 생성");
        memberService.add(new Member(1, Util.getNow(), "test1", "test1", "김철수"));
        memberService.add(new Member(2, Util.getNow(), "test2", "test2", "김영희"));
        memberService.add(new Member(3, Util.getNow(), "test3", "test3", "홍길동"));
    }

}
