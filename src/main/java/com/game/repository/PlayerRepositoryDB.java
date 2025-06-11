package com.game.repository;

import com.game.entity.Player;
import jakarta.annotation.PreDestroy; // the annotation package was changed due to developers moving it
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {

    public PlayerRepositoryDB() {

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
}