package org.koreait.motivation.entity;


public class Motivation {
    private int id;
    private String source;
    private String body;

    public Motivation(int id, String source, String body) {
        this.id = id;
        this.source = source;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Motivation{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
