package com.entity;

import com.util.LcuTokenGeter;
import lombok.Data;

import java.io.IOException;

@Data
public class LeagueClient {
    public static LeagueClient instance;

    private String port;
    private String token;
    private String region;

    private LeagueClient(){
    }

    public static LeagueClient getInstance() throws IOException {
        if(instance == null){
            instance = new LeagueClient();
            instance.setToken(LcuTokenGeter.getClientProcess().get("Token"));
            instance.setPort(LcuTokenGeter.getClientProcess().get("Port"));
            instance.setRegion(LcuTokenGeter.getClientProcess().get("Region"));
        }
        return instance;
    }

}
