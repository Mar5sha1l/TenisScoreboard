package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.DTO.MatchDTO;
import com.example.tennisscoreboard.service.OnGoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name ="MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    OnGoingMatchService ongoingMatchService = OnGoingMatchService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winner = Integer.parseInt(req.getParameter("winner"));

        try {
            String url = ongoingMatchService.updateMatchScore(matchId, winner);
            resp.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ошибка обновления матча!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));

        Optional<MatchDTO> optionalMatch = ongoingMatchService.getOngoingMatchById(matchId);
        if (optionalMatch.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Матч не найден!");
            return;
        }

        MatchDTO match = optionalMatch.get();
        req.setAttribute("match", match);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
