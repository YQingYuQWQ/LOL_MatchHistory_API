package com.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标玩家单局比赛数据
 */
@Data
public class PlayerMatchInfo {
    private int kills;
    private int deaths;
    private int assists;
    private int championId;
    private boolean win;
    private int spell1Id;
    private int spell2Id;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private List<Integer> team1Player = new ArrayList<>();
    private List<Integer> team2Player = new ArrayList<>();
}
