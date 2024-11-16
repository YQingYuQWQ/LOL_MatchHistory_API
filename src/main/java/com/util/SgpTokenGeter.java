package com.util;

import com.entity.LeagueClient;
import com.entity.SgpClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SgpTokenGeter extends Http {
    String url;

    //获取sgp的token
    public SgpClient getSgpToken(LeagueClient leagueClient) {
        SgpClient sgpClient = new SgpClient();
        OkHttpClient client = getUnsafeOkHttpClient();
        url = "https://127.0.0.1:" + leagueClient.getPort() + "/entitlements/v1/token";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Credentials.basic("riot", leagueClient.getToken()))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                sgpClient.setToken(jsonObject.getString("accessToken")); ;
            } else {
                log.error("Request failed with code: " + response.code());
                log.error("Response body: " + response);
            }
        } catch (Exception e) {
            log.error("Error occurred while making the request", e);
        }
        return sgpClient;
    }
}
