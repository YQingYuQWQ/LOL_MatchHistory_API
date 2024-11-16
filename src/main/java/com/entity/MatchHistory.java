package com.entity;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchHistory {
    private JSONArray otherPlayer = new JSONArray();
    private List<JSONObject> matchHistory = new ArrayList<>();

}
