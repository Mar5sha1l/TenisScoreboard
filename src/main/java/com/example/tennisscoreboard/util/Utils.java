package com.example.tennisscoreboard.util;

import com.example.tennisscoreboard.DTO.MatchDTO;
import com.example.tennisscoreboard.entity.Match;

public class Utils {
    public static Match convertMatchDTOtoEntity(MatchDTO matchDTO) {
        Match match = new Match();
        match.setPlayer1(matchDTO.getPlayer1());
        match.setPlayer2(matchDTO.getPlayer2());
        match.setWinner(matchDTO.getWinner());
        return match;
    }
}
