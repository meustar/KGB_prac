package org.koreait.motivation.service;

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

    public int add(String source, String body) {
        int id = lastMotivationId + 1;

        Motivation motivation = new Motivation(id, source, body);
    }
}
