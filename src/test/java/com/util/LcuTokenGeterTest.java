package com.util;

import com.entity.LeagueClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class LcuTokenGeterTest {
    @Autowired
    private LcuTokenGeter lcuTokenGeter;

    @Test
    public void testGetLcuToken() throws Exception {
        LeagueClient leagueClient = LeagueClient.getInstance();
        //leagueClient = lcuTokenGeter.getClientProcess();
        System.out.println(leagueClient.getToken());
        System.out.println(leagueClient.getPort());
        System.out.println(leagueClient.getRegion());
    }
}
