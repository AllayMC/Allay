package org.allaymc.api.scoreboard;

import org.allaymc.api.scoreboard.data.DisplaySlot;

/**
 * Describes a scoreboard viewer (e.g., EntityPlayer)
 * <p>
 * This interface is used to abstract the server-to-client protocol methods
 * <p>
 * The term "Scoreboard" is deliberately used in the method names to prevent confusion with other EntityPlayer interfaces
 * <p>
 *
 * @author daoge_cmd
 */
public interface ScoreboardViewer {
    /**
     * Display a scoreboard in a specified slot
     *
     * @param scoreboard The target scoreboard
     * @param slot       The target slot
     */
    void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot);

    /**
     * Clear the display content of the specified slot
     *
     * @param slot The target slot
     */
    void hideScoreboardSlot(DisplaySlot slot);

    /**
     * Notify the viewer that the scoreboard has been deleted (if the scoreboard is in any display slot, the slot will be cleared as well)
     *
     * @param scoreboard The target scoreboard
     */
    void removeScoreboard(Scoreboard scoreboard);

    /**
     * Notify the viewer that the specified line on the specified scoreboard has been deleted
     *
     * @param line The target line
     */
    void removeScoreboardLine(ScoreboardLine line);

    /**
     * Send the new score of the specified line to the viewer
     *
     * @param line The target line
     */
    void updateScore(ScoreboardLine line);

    boolean isScoreboardViewerValid();
}
