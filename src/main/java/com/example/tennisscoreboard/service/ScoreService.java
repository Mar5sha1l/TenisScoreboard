package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.DTO.MatchDTO;

public class ScoreService {
    public void updateScore(MatchDTO matchDTO, int winnerId) {
        if (winnerId == 1) {
            updatePlayer1Score(matchDTO);
        } else if (winnerId == 2) {
            updatePlayer2Score(matchDTO);
        } else {
            throw new IllegalArgumentException("Неверный идентификатор игрока!");
        }

        checkSetWinner(matchDTO);

        checkMatchWinner(matchDTO);
    }

    private void updatePlayer1Score(MatchDTO matchDTO) {
        if (matchDTO.getPlayer1Score() == 30) {
            matchDTO.setPlayer1Score(40);
        } else if (matchDTO.getPlayer1Score() == 40) {
            // Обработка ситуации с 40-40
            if (matchDTO.getPlayer2Score() == 40) {
                matchDTO.setPlayer1Score(0);
            } else {
                matchDTO.setPlayer1Score(0);
                matchDTO.setPlayer2Score(0);
                matchDTO.setPlayer1Game(matchDTO.getPlayer1Game() + 1);
            }
        } else {
            matchDTO.setPlayer1Score(matchDTO.getPlayer1Score() + 15);
        }
    }

    private void updatePlayer2Score(MatchDTO matchDTO) {
        if (matchDTO.getPlayer2Score() == 30) {
            matchDTO.setPlayer2Score(40);
        } else if (matchDTO.getPlayer2Score() == 40) {
            // Обработка ситуации с 40-40 (Deuce) и преимуществом
            if (matchDTO.getPlayer1Score() == 40) {
                matchDTO.setPlayer2Score(0);  // "AD" для игрока 2
            } else {
                matchDTO.setPlayer1Score(0);
                matchDTO.setPlayer2Score(0);
                matchDTO.setPlayer2Game(matchDTO.getPlayer2Game() + 1);
            }
        } else {
            matchDTO.setPlayer2Score(matchDTO.getPlayer2Score() + 15);
        }
    }

    private void checkSetWinner(MatchDTO matchDTO) {
        if (matchDTO.getPlayer1Game() >= 6 && matchDTO.getPlayer1Game() - matchDTO.getPlayer2Game() >= 2) {
            matchDTO.setPlayer1Set(matchDTO.getPlayer1Set() + 1);
            resetScoresForNewSet(matchDTO);
        } else if (matchDTO.getPlayer2Game() >= 6 && matchDTO.getPlayer2Game() - matchDTO.getPlayer1Game() >= 2) {
            matchDTO.setPlayer2Set(matchDTO.getPlayer2Set() + 1);
            resetScoresForNewSet(matchDTO);
        }

        if (matchDTO.getPlayer1Game() >= 6 && matchDTO.getPlayer2Game() >= 6) {
            if (matchDTO.getPlayer1Game() >= 7 && matchDTO.getPlayer1Game() - matchDTO.getPlayer2Game() >= 2) {
                matchDTO.setPlayer1Set(matchDTO.getPlayer1Set() + 1);
                resetScoresForNewSet(matchDTO);
            } else if (matchDTO.getPlayer2Game() >= 7 && matchDTO.getPlayer2Game() - matchDTO.getPlayer1Game() >= 2) {
                matchDTO.setPlayer2Set(matchDTO.getPlayer2Set() + 1);
                resetScoresForNewSet(matchDTO);
            }
        }
    }

    private void resetScoresForNewSet(MatchDTO matchDTO) {
        matchDTO.setPlayer1Score(0);
        matchDTO.setPlayer2Score(0);
        matchDTO.setPlayer1Game(0);
        matchDTO.setPlayer2Game(0);
    }

    private void checkMatchWinner(MatchDTO matchDTO) {
        if (matchDTO.getPlayer1Set() == 2) {
            matchDTO.setWinner(matchDTO.getPlayer1());
        } else if (matchDTO.getPlayer2Set() == 2) {
            matchDTO.setWinner(matchDTO.getPlayer2());
        }
    }
}
