package com.entity;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PlayerMatchHistory {
    private String puuid;
    private List<Map<String, Object>> matchHistoryToMap = new ArrayList<>();
    private List<Map<String, Object>> mineInfo = new ArrayList<>();
}
