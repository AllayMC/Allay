package org.allaymc.api.scoreboard.storage;


import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;

import java.util.Collection;
import java.util.Map;

/**
 * ScoreboardStorage is used to store and read scoreboards.
 *
 * @author daoge_cmd
 */
public interface ScoreboardStorage {
    /**
     * Save the scoreboard to the storage.
     *
     * @param scoreboard The target scoreboard
     */
    void save(Scoreboard scoreboard);

    /**
     * Save the scoreboards to the storage.
     *
     * @param scoreboards The target scoreboards
     */
    void save(Collection<Scoreboard> scoreboards);

    /**
     * Save the display to the storage.
     *
     * @param display The target display
     */
    void saveDisplay(Map<DisplaySlot, Scoreboard> display);

    /**
     * Read all scoreboards from the storage.
     *
     * @return The scoreboards
     */
    Map<String, Scoreboard> readAll();

    /**
     * Read the scoreboard from the storage.
     *
     * @param name The target scoreboard name
     *
     * @return The scoreboard
     */
    Scoreboard read(String name);

    /**
     * Read the display from the storage.
     *
     * @return The display
     */
    Map<DisplaySlot, String> readDisplay();

    /**
     * Remove the scoreboard from the storage.
     *
     * @param name The target scoreboard name
     */
    void remove(String name);

    /**
     * Remove all scoreboards from the storage.
     */
    void removeAll();

    /**
     * Check if the storage contains the scoreboard.
     *
     * @param name The target scoreboard name
     *
     * @return true if the storage contains the scoreboard
     */
    boolean contain(String name);
}
