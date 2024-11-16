package com.service;

import java.io.IOException;

public interface GetPlayerPuuidService {
    public String getPlayerPuuid(String playerName, String tagLine) throws IOException;
}
