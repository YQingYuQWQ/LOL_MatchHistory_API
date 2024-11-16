package com.service.impl;

import com.entity.LeagueClient;
import com.entity.SgpClient;
import com.entity.SummonerInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.service.GetSummonerInfo;
import com.util.Http;
import com.util.SgpTokenGeter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class GetSummonerInfoImpl extends Http implements GetSummonerInfo {
    @Autowired
    private SgpTokenGeter sgpTokenGeter;

    //获取玩家头像及信息
    @Override
    public SummonerInfo getSummonerInfo(String puuid) throws IOException {
        LeagueClient leagueClient = LeagueClient.getInstance();
        SgpClient sgpClient = new SgpClient();

        String url = "https://127.0.0.1:" + leagueClient.getPort() + "/lol-summoner/v2/summoners/puuid/" + puuid;

        SummonerInfo summonerInfo = new SummonerInfo();
        OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        sgpClient.setToken(sgpTokenGeter.getSgpToken(leagueClient).getToken());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Credentials.basic("riot", leagueClient.getToken()))
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                summonerInfo = objectMapper.readValue(responseBody, SummonerInfo.class);
            } else {
                log.error("Request failed with code: " + response.code());
                log.error("Response body: " + response);
            }
        } catch (Exception e) {
            log.error("Error occurred while making the request", e);
        }
        return summonerInfo;
    }

    //获取玩家排位信息
    @Override
    public SummonerInfo getSumoonerRankedInfo(SummonerInfo summonerInfo) throws IOException {
        LeagueClient leagueClient = LeagueClient.getInstance();
        SgpClient sgpClient = new SgpClient();
        String url = "https://127.0.0.1:" + leagueClient.getPort() + "/lol-ranked/v1/ranked-stats/" + summonerInfo.getPuuid();

        OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        sgpClient.setToken(sgpTokenGeter.getSgpToken(leagueClient).getToken());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Credentials.basic("riot", leagueClient.getToken()))
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);

                // 检查是否有 queues 字段
                if (jsonObject.has("queues")) {
                    JSONArray queues = jsonObject.getJSONArray("queues");
                    for (int i = 0; i < queues.length(); i++) {
                        JSONObject queue = queues.getJSONObject(i);
                        String queueType = queue.getString("queueType");
                        if ("RANKED_SOLO_5x5".equals(queueType)) {
                            summonerInfo.setRANKED_SOLO_5x5(queue.toMap());
                        } else if ("RANKED_FLEX_SR".equals(queueType)) {
                            summonerInfo.setRANKED_FLEX_SR(queue.toMap());
                        }
                    }
                }
            } else {
                log.error("Request failed with code: " + response.code());
                log.error("Response body: " + response);
            }
        } catch (Exception e) {
            log.error("Error occurred while making the request", e);
        }
        return summonerInfo;
    }

    //获取玩家所有信息
    @Override
    public SummonerInfo getAllSummonerInfo(String puuid) throws IOException {
        SummonerInfo summonerInfo = getSummonerInfo(puuid);
        summonerInfo = getSumoonerRankedInfo(summonerInfo);
        return summonerInfo;
    }
}
