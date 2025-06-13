package com.game.repository;

import com.game.entity.Player;
import jakarta.annotation.PreDestroy; // the annotation package was changed due to developers moving it
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {

    private final SessionFactory sessionFactory;

    public PlayerRepositoryDB() {
        Properties properties = new Properties();

        String username = System.getProperty("USER_NAME");
        String password = System.getProperty("USER_PASS");

        properties.put("hibernate.connection.username", username);
        properties.put("hibernate.connection.password", password);

        Configuration configuration = new Configuration()
                .addProperties(properties);

        this.sessionFactory = configuration.configure().buildSessionFactory();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()){

            NativeQuery<Player> query = session.createNativeQuery("SELECT * FROM rpg.player", Player.class);

            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        }
    }

    @Override
    public int getAllCount() {
        try (Session session = sessionFactory.openSession()){
            return session.createNamedQuery("Player_GetAllCount", Long.class).getSingleResult().intValue();
        }
    }

    @Override
    public Player save(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save player: " + e.getMessage());
            throw new RuntimeException("Database error while saving player", e);
        }
    }

    @Override
    public Player update(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Player merged = (Player) session.merge(player);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to update player: " + e.getMessage());
            throw new RuntimeException("Database error while updating player", e);
        }
    }

    @Override
    public Optional<Player> findById(long id) {
        try(Session session = sessionFactory.openSession()){
            Player player = session.get(Player.class, id);
            return Optional.ofNullable(player);
        }
    }

    @Override
    public void delete(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Player existingPlayer = session.get(Player.class, player.getId());
            if (existingPlayer != null) {
                session.delete(existingPlayer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to delete player: " + e.getMessage());
            throw new RuntimeException("Database error while deleting player", e);
        }
    }

    @PreDestroy
    public void beforeStop() {
        sessionFactory.close();
    }
}