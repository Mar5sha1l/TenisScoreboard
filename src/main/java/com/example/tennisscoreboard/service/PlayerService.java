package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.DAO.PlayerDAO;
import com.example.tennisscoreboard.entity.Player;

public class PlayerService {
    PlayerDAO playerDAO = new PlayerDAO();


    public Player getOrCreatePlayer(String playerName) {
        Player playerByName = playerDAO.findByName(playerName);
        if (playerByName == null) {
            Player newPlayer = new Player();
            newPlayer.setName(playerName);
            return newPlayer;
        } else {
            return playerByName;
        }
    }
}
