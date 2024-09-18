package org.allaymc.api.scoreboard.storage;


import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;

import java.util.Collection;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface ScoreboardStorage {
    void save(Scoreboard scoreboard);

    void save(Collection<Scoreboard> scoreboards);

    void saveDisplay(Map<DisplaySlot, Scoreboard> display);

    Map<String, Scoreboard> readAll();

    Scoreboard read(String name);

    Map<DisplaySlot, String> readDisplay();

    void remove(String name);

    void removeAll();

    boolean contain(String name);
}
