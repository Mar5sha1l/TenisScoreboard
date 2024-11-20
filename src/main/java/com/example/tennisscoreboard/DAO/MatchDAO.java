package com.example.tennisscoreboard.DAO;
import com.example.tennisscoreboard.util.HibernateUtil;
import com.example.tennisscoreboard.entity.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchDAO extends GenericDAO<Match, Integer> {
    public MatchDAO() {
        super(Match.class);
    }

    public List<Match> findMatchesByPlayers(String player1name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String query = "SELECT m FROM Match m WHERE " +
                "LOWER(m.player1.name) LIKE :name OR " +
                "LOWER(m.player2.name) LIKE :name";

        List<Match> results = session.createQuery(query, Match.class)
                .setParameter("name", "%" + player1name.toLowerCase() + "%")
                .getResultList();
        session.close();

        return results;
    }

    public List<Match> findMatchesWithPagination(int offset, int limit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Match> matches = session.createQuery("FROM Match", Match.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return matches;
    }

    public List<Match> findMatchesWithPaginationAndName(int offset, int limit, String player1name) {
        String sql = "SELECT m FROM Match m WHERE " +
                "LOWER(m.player1.name) LIKE :name OR " +
                "LOWER(m.player2.name) LIKE :name";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Match> matches = session.createQuery(sql, Match.class)
                .setParameter("name", "%" + player1name.toLowerCase() + "%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
        session.close();
        return matches;
    }
}
