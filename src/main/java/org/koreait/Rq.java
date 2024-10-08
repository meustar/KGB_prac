package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String actionMethod;
    private Map<String, String> params;
    private String errMsg = "";

    //Rq = Reqeust (요청)
    public Rq(String cmd) {
        // parsing
        String[] cmdBits = cmd.split("\\?", 2);
        String[] paramBits;

        actionMethod = cmdBits[0];

        params = new HashMap<>();

        if (cmdBits.length == 1) {
            return;
        }

        try {
            paramBits = cmdBits[1].split("&");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어를 다시 확인해주세요.");
            return;
        }

        for(String paramStr : paramBits) {
            String[] paramStrBits = paramStr.split("=", 2);
            String key = paramStrBits[0];
            if (key.equals("id") == false) {
                System.out.println("오타가 있습니다.(id)");
                errMsg = "오타가 있습니다.(id)";
            }
            String value = paramStrBits[1];
            params.put(key, value);
        }
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public String getParams(String paramName) {

        // key를 넣으면 해당 value를 얻을수 있음.
        return params.get(paramName);
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "Rq{" +
                "actionMethod='" + actionMethod + '\'' +
                ", params=" + params +
                '}';
    }

}
