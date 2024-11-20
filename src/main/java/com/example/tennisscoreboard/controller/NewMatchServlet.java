package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.DTO.MatchDTO;
import com.example.tennisscoreboard.service.OnGoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "newMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final OnGoingMatchService matchService = OnGoingMatchService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            req.getRequestDispatcher("newMatch.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        if (!isValidName(player1Name) || !isValidName(player2Name) || equalsIgnoreCaseAndSpace(player1Name, player2Name)) {
            req.setAttribute("errorMessage", "Имена игроков не должны совпадать, и должны содержать только буквы, пробелы и не быть пустыми.");
            req.getRequestDispatcher("newMatch.jsp").forward(req, resp);
            return;
        }

        MatchDTO match = matchService.createNewMatch(player1Name, player2Name);
        String url = "match-score?uuid=" + match.getMatchId();
        resp.sendRedirect(url);
    }

    private boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 50) {
            return false;
        }
        return name.matches("[a-zA-Zа-яА-ЯёЁ\\s]+");
    }

    public boolean equalsIgnoreCaseAndSpace(String str1, String str2) {
        String cleanedStr1 = str1.replace(" ", "");
        String cleanedStr2 = str2.replace(" ", "");

        return cleanedStr1.equalsIgnoreCase(cleanedStr2);
    }
}
