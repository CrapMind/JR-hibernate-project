package com.game.repository;

import com.game.entity.Player;
import jakarta.annotation.PreDestroy; // the annotation package was changed due to developers moving it
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
        return null;
    }

    @Override
    public int getAllCount() {
        return 0;
    }

    @Override
    public Player save(Player player) {
        return null;
    }

    @Override
    public Player update(Player player) {
        return null;
    }

    @Override
    public Optional<Player> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Player player) {

    }
    @PreDestroy
    public void beforeStop() {

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}