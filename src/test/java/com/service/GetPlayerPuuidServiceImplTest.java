package com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class GetPlayerPuuidServiceImplTest {
    @Autowired
    private GetPlayerPuuidService getPlayerPuuidService;

    @Test
    public void testGetPlayerPuuid() throws IOException {
        String puuid = getPlayerPuuidService.getPlayerPuuid("寒冰菇", "32963");
        System.out.println(puuid);
    }
}
