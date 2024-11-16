package com.service.impl;

import com.entity.LeagueClient;
import com.entity.MatchHistory;
import com.entity.PlayerMatchHistory;
import com.entity.SgpClient;
import com.enums.Regions;
import com.service.GetPlayerMatchHistoryService;
import com.util.Http;
import com.util.RegionMapping;
import com.util.SgpTokenGeter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GetPlayerMatchHistoryServiceImpl extends Http implements GetPlayerMatchHistoryService {
    @Autowired
    private GetPlayerPuuidServiceImpl getPlayerPuuidService;
    @Autowired
    private SgpTokenGeter sgpTokenGeter;
    @Autowired
    private RegionMapping regionMapping;

    //获取玩家的比赛历史
    public PlayerMatchHistory getPlayerMatchHistory(String region, String playerName, String tagLine) throws IOException {
        LeagueClient leagueClient = LeagueClient.getInstance();

        SgpClient sgpClient = new SgpClient();
        sgpClient.setToken(sgpTokenGeter.getSgpToken(leagueClient).getToken());

        PlayerMatchHistory playerMatchHistory = new PlayerMatchHistory();
        playerMatchHistory.setPuuid(getPlayerPuuidService.getPlayerPuuid(playerName, tagLine));

        region = regionMapping.regionMapping(region);
        if (region == null) {
            log.error("Region not found");
            return null;
        }
        String url = "https://"+ region +"-sgp.lol.qq.com:21019/match-history-query/v1/products/lol/player/" + playerMatchHistory.getPuuid() + "/SUMMARY?startIndex=0&count=5";
        if (region.equals("HN1") || region.equals("HN10") || region.equals("BGP2")){
            url = "https://" + region + "-k8s-sgp.lol.qq.com:21019/match-history-query/v1/products/lol/player/" + playerMatchHistory.getPuuid() + "/SUMMARY?startIndex=0&count=5";
        }

        OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "LeagueOfLegendsClient/14.22.632.3512 (rcp-be-lol-match-history)")
                .addHeader("Authorization", "Bearer " + sgpClient.getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                if (jsonObject.has("games")) {
                    JSONArray gamesArray = jsonObject.getJSONArray("games");
                    for (int i = 0; i < gamesArray.length(); i++) {
                        JSONObject gameObject = gamesArray.getJSONObject(i);
                        playerMatchHistory.getMatchHistoryToMap().add(gameObject.toMap());
                    }
                }
            } else {
                log.error("Request failed with code: " + response.code());
                log.error("Response body: " + response);
            }
        } catch (Exception e) {
            log.error("Error occurred while making the request", e);
        }
        return playerMatchHistory;
    }

    //分离搜索玩家和其他玩家的比赛数据
    public PlayerMatchHistory sortMatchiHistory(PlayerMatchHistory playerMatchHistory) throws IOException {
        List<JSONObject> matchHistory = new ArrayList<>();
        for (Map<String, Object> map : playerMatchHistory.getMatchHistoryToMap()) {
            JSONObject jsonObject = new JSONObject(map);
            matchHistory.add(jsonObject);
        }

        for (JSONObject game : matchHistory) {
            JSONObject json = game.optJSONObject("json");
            if (json != null) {
                JSONArray participants = json.optJSONArray("participants");
                for (int i = 0; i < participants.length(); i++) {
                    JSONObject participant = participants.optJSONObject(i);
                    if (participant != null) {
                        if (playerMatchHistory.getPuuid().equals(participant.optString("puuid"))) {
                            playerMatchHistory.getMineInfo().add(participant.toMap());
                        }
                    }
                }
            }
        }
        return playerMatchHistory;
    }

    public PlayerMatchHistory all(String region, String playerName, String tagLine) throws IOException {
        PlayerMatchHistory playerMatchHistory = getPlayerMatchHistory(region, playerName, tagLine);
        playerMatchHistory = sortMatchiHistory(playerMatchHistory);
        return playerMatchHistory;
    }
}

