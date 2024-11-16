package com.service;

import com.entity.PlayerMatchHistory;
import com.service.impl.GetPlayerMatchHistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class GetPlayerMatchHistoryServiceImplTest {
    @Autowired
    private GetPlayerMatchHistoryServiceImpl getPlayerMatchHistoryServiceImpl;


    @Test
    public void testGetPlayerMatchHistory() throws IOException {
        //playerMatchHistory = getPlayerMatchHistoryServiceImpl.getPlayerMatchHistory("比尔吉沃特", "寒冰菇", "32963");
        //System.out.println(playerMatchHistory.getMatchHistory());
        //playerMatchHistory = getPlayerMatchHistoryServiceImpl.sortMatchiHistory(playerMatchHistory);
        //System.out.println(playerMatchHistory.getOtherPlayer().get(0));
        //System.out.println(playerMatchHistory.getMatchHistory().get(1));
        //playerMatchHistory = getPlayerMatchHistoryServiceImpl.all("比尔吉沃特", "寒冰菇", "32963");
        //System.out.println(playerMatchHistory);
    }
}
