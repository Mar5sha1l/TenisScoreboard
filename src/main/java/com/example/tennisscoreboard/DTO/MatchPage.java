package com.example.tennisscoreboard.DTO;

import com.example.tennisscoreboard.entity.Match;

import java.util.List;

public record MatchPage(List<Match> matches, int currentPage, int totalPages) {
}
