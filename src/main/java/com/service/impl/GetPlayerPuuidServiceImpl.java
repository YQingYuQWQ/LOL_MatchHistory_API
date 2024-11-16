package com.service.impl;

import com.entity.LeagueClient;
import com.entity.SgpClient;
import com.service.GetPlayerPuuidService;
import com.util.Http;
import com.util.LcuTokenGeter;
import com.util.SgpTokenGeter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class GetPlayerPuuidServiceImpl extends Http implements GetPlayerPuuidService {
    @Autowired
    LcuTokenGeter lcuTokenGeter;
    @Autowired
    SgpTokenGeter sgpTokenGeter;

    //获取玩家的puuid
    @Override
    public String getPlayerPuuid(String playerName, String tagLine) throws IOException {
        String puuid = null;

        LeagueClient leagueClient = LeagueClient.getInstance();
        SgpClient sgpClient = new SgpClient();
        sgpClient = sgpTokenGeter.getSgpToken(leagueClient);

        OkHttpClient client = getUnsafeOkHttpClient();
        String url = "https://prod-rso.lol.qq.com:3001/aliases/v1/aliases?gameName=" + playerName + "&tagLine=" + tagLine;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + sgpClient.getToken())
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONArray jsonArray = new JSONArray(responseBody);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                puuid = jsonObject.getString("puuid"); ;
            } else {
                log.error("Request failed with code: " + response.code());
                log.error("Response body: " + response);
            }
        } catch (Exception e) {
            log.error("Error occurred while making the request", e);
        }
        return puuid;
    }
}
