package org.allaymc.api.scoreboard.storage;

import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 *
 * TODO
 */
public class JsonScoreboardStorage implements ScoreboardStorage {
    @Override
    public void save(Scoreboard scoreboard) {

    }

    @Override
    public void save(Collection<Scoreboard> scoreboards) {

    }

    @Override
    public void saveDisplay(Map<DisplaySlot, Scoreboard> display) {

    }

    @Override
    public Map<String, Scoreboard> readAll() {
        return new HashMap<>();
    }

    @Override
    public Scoreboard read(String name) {
        return new Scoreboard(name, name);
    }

    @Override
    public Map<DisplaySlot, String> readDisplay() {
        return new HashMap<>();
    }

    @Override
    public void remove(String name) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public boolean contain(String name) {
        return false;
    }
}
