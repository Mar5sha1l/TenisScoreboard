package com.example.tennisscoreboard.controller;

import com.example.tennisscoreboard.DAO.MatchDAO;
import com.example.tennisscoreboard.DTO.MatchPage;
import com.example.tennisscoreboard.entity.Match;
import com.example.tennisscoreboard.service.PaginationService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "allMatchesServlet", value = "/matches")
public class AllMatchesServlet extends HttpServlet {
    PaginationService paginationService = new PaginationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        String pageParam = req.getParameter("page");

        try {
            MatchPage matchPage;
            if (name == null || name.trim().isEmpty()) {
                matchPage = paginationService.getMatchesWithPagination(pageParam, 10);
            } else {
                matchPage = paginationService.getMatchesWithPaginationAndName(pageParam, 10, name);
            }
            req.setAttribute("matches", matchPage.matches());
            req.setAttribute("currentPage", matchPage.currentPage());
            req.setAttribute("totalPages", matchPage.totalPages());
            req.getRequestDispatcher("/Matches.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка обработки запроса");
        }
    }
}
