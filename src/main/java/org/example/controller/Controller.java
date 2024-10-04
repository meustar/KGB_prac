package org.example.controller;

import org.example.dto.Member;

import java.util.List;

public abstract class Controller {

    protected static Member loginedMember = null;

    protected static List<Member> members;

    public abstract void doAction(String cmd, String actionMethodName);

    public static boolean isLogined() {
        return loginedMember != null;
    }

    public void makeTestData() {

    }

}
