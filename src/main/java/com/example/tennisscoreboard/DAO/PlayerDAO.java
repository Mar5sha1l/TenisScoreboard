package com.example.tennisscoreboard.DAO;

import com.example.tennisscoreboard.util.HibernateUtil;
import com.example.tennisscoreboard.entity.Player;
import org.hibernate.Session;

public class PlayerDAO extends GenericDAO<Player, Integer> {
    public PlayerDAO() {
        super(Player.class);
    }

    public Player findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Player player = session.createQuery(
                        "FROM Player WHERE name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return player;
    }
}
