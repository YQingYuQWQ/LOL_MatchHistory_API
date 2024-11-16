package com.service;

import com.entity.SummonerInfo;
import com.service.impl.GetSummonerInfoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class GetSummonerInfoImplTest {
    @Autowired
    private GetSummonerInfoImpl getSummonerInfo;


    @Test
    public void testGetSummonerInfo() throws IOException {
//        summonerInfo = getSummonerInfo.getSummonerInfo("6409d6ed-f427-5390-a05c-b35f77992f77");
//        summonerInfo = getSummonerInfo.getSumoonerRankedInfo("6409d6ed-f427-5390-a05c-b35f77992f77");
        //summonerInfo = getSummonerInfo.getAllSummonerInfo("6409d6ed-f427-5390-a05c-b35f77992f77");
        //System.out.println(summonerInfo);
    }
}
