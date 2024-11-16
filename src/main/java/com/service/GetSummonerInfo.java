package com.service;

import com.entity.SummonerInfo;

import java.io.IOException;

public interface GetSummonerInfo {
    public SummonerInfo getSummonerInfo(String puuid) throws IOException;
    public SummonerInfo getSumoonerRankedInfo(SummonerInfo summonerInfo) throws IOException;
    public SummonerInfo getAllSummonerInfo(String puuid) throws IOException;
}
