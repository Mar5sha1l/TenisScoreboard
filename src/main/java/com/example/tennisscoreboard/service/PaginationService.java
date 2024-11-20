package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.DAO.MatchDAO;
import com.example.tennisscoreboard.DTO.MatchPage;
import com.example.tennisscoreboard.entity.Match;

import java.util.List;

public class PaginationService {
    private final MatchDAO matchDAO = new MatchDAO();

    public MatchPage getMatchesWithPagination(String pageParam, int matchesPerPage) {

        int totalMatches = matchDAO.countAll();
        int totalPages = (int) Math.ceil((double) totalMatches / matchesPerPage);
        int page = intParser(pageParam, totalPages);
        int offset = (page - 1) * matchesPerPage;

        List<Match> matches = matchDAO.findMatchesWithPagination(offset, matchesPerPage);
        return new MatchPage(matches, page, totalPages);
    }

    public MatchPage getMatchesWithPaginationAndName(String pageParam, int matchesPerPage, String name) {
        int totalMatches = matchDAO.findMatchesByPlayers(name).size();
        int totalPages = (int) Math.ceil((double) totalMatches / matchesPerPage);
        int page = intParser(pageParam, totalPages);
        int offset = (page - 1) * matchesPerPage;

        List<Match> matches = matchDAO.findMatchesWithPaginationAndName(offset, matchesPerPage, name);
        return new MatchPage(matches, page, totalPages);

    }

    private int intParser(String pageParam, int totalPages) {
        int page;
        try {
            page = Integer.parseInt(pageParam);

            if (page <= 0) {
                page = 1;
            }
            if(page > totalPages) {
                page = totalPages;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        return page;
    }


}
