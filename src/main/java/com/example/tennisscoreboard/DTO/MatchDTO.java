package com.example.tennisscoreboard.DTO;

import com.example.tennisscoreboard.entity.Player;

import java.util.UUID;

public class MatchDTO {
    private final UUID matchId;
    private final Player player1;
    private final Player player2;
    private int player1Score;
    private int player2Score;
    private int player1Game;
    private int player2Game;
    private int player1Set;
    private int player2Set;
    private Player winner;
    private boolean isFinished;

    public MatchDTO(Player player1, Player player2) {
        this.matchId = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Game = 0;
        this.player2Game = 0;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getPlayer1Game() {
        return player1Game;
    }

    public void setPlayer1Game(int player1Game) {
        this.player1Game = player1Game;
    }

    public int getPlayer2Game() {
        return player2Game;
    }

    public void setPlayer2Game(int player2Game) {
        this.player2Game = player2Game;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getPlayer1Set() {
        return player1Set;
    }

    public void setPlayer1Set(int player1Set) {
        this.player1Set = player1Set;
    }

    public int getPlayer2Set() {
        return player2Set;
    }

    public void setPlayer2Set(int player2Set) {
        this.player2Set = player2Set;
    }
}
