package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
召唤师信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerInfo {
    private String puuid;
    private String displayName;
    private String gameName;
    private String tagLine;
    private String summonerLevel;
    private String summonerId;
    private String profileIconId;
    private String accountId;
    private String privacy;
    private Map<String, Object> RANKED_SOLO_5x5;
    private Map<String, Object> RANKED_FLEX_SR;
    private PlayerMatchHistory playerMatchHistory;
}
