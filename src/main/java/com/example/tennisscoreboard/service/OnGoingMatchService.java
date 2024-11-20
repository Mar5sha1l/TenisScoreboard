package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.DAO.MatchDAO;
import com.example.tennisscoreboard.DAO.PlayerDAO;
import com.example.tennisscoreboard.DTO.MatchDTO;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.entity.Player;

import java.util.*;

import static com.example.tennisscoreboard.util.Utils.convertMatchDTOtoEntity;

public class OnGoingMatchService {
    private static final OnGoingMatchService INSTANCE = new OnGoingMatchService();
    private final Map<UUID, MatchDTO> ongoingMatches = new HashMap<>();
    private final MatchDAO matchDAO = new MatchDAO();
    PlayerDAO playerDAO = new PlayerDAO();
    private final PlayerService playerService = new PlayerService();
    ScoreService scoreService = new ScoreService();

    private OnGoingMatchService() {}

    public static OnGoingMatchService getInstance() {
        return INSTANCE;
    }

    public MatchDTO createNewMatch(String player1Name, String player2Name) {
        Player player1 = playerService.getOrCreatePlayer(player1Name);
        Player player2 = playerService.getOrCreatePlayer(player2Name);

        MatchDTO matchDTO = new MatchDTO(player1, player2);
        ongoingMatches.put(matchDTO.getMatchId(), matchDTO);
        return matchDTO;
    }

    public String updateMatchScore(UUID matchId, int winnerId) {
        String redirectUrl;
        Optional<MatchDTO> optionalMatch = getOngoingMatchById(matchId);
        if (optionalMatch.isEmpty()) {
            throw new IllegalArgumentException("Матч не найден");
        }

        MatchDTO match = optionalMatch.get();
        scoreService.updateScore(match, winnerId);

        if (isMatchOver(match)) {
            completeMatch(match);
            redirectUrl = "main";
        } else {
            redirectUrl = "match-score?uuid=" + matchId;
        }
        return redirectUrl;
    }

    public Optional<MatchDTO> getOngoingMatchById(UUID matchId) {
        return Optional.ofNullable(ongoingMatches.get(matchId));
    }

    private boolean isMatchOver(MatchDTO matchDTO) {
        return matchDTO.getWinner() != null;
    }

    private void completeMatch(MatchDTO matchDTO) {
        ongoingMatches.remove(matchDTO.getMatchId());

        Match matchEntity = convertMatchDTOtoEntity(matchDTO);
        playerDAO.save(matchEntity.getPlayer1());
        playerDAO.save(matchEntity.getPlayer2());
        matchDAO.save(matchEntity);
    }
}
