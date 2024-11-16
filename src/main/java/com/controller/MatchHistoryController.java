package com.controller;

import com.entity.PlayerMatchHistory;
import com.entity.Result;
import com.entity.SummonerInfo;
import com.service.impl.GetPlayerMatchHistoryServiceImpl;
import com.service.impl.GetPlayerPuuidServiceImpl;
import com.service.impl.GetSummonerInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/matchHistory")
public class MatchHistoryController {
    @Autowired
    private GetPlayerPuuidServiceImpl getPlayerPuuidService;
    @Autowired
    private GetSummonerInfoImpl getSummonerInfo;
    @Autowired
    private GetPlayerMatchHistoryServiceImpl getPlayerMatchHistoryService;

    @PostMapping("/all")
    public Result getPlayer(@RequestParam("region") String region, @RequestParam("gameName") String gameName, @RequestParam("tagLine") String tagLine) throws IOException {
        SummonerInfo summonerInfo = new SummonerInfo();
        summonerInfo.setPuuid(getPlayerPuuidService.getPlayerPuuid(gameName, tagLine));
        summonerInfo = getSummonerInfo.getAllSummonerInfo(summonerInfo.getPuuid());
        summonerInfo.setPlayerMatchHistory(getPlayerMatchHistoryService.all(region, gameName, tagLine));
        return Result.success(summonerInfo);
    }
}
