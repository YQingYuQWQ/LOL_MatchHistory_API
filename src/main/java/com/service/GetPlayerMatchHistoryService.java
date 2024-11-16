package com.service;

import com.entity.MatchHistory;
import com.entity.PlayerMatchHistory;

import java.io.IOException;

public interface GetPlayerMatchHistoryService {
    public PlayerMatchHistory getPlayerMatchHistory(String region, String playerName, String tagLine)throws IOException;
  //  public PlayerMatchHistory sortMatchiHistory(MatchHistory matchHistory, PlayerMatchHistory playerMatchHistory) throws IOException;
}
