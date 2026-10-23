package org.allaymc.api.scoreboard;

import org.allaymc.api.scoreboard.data.DisplaySlot;

/**
 * {@code ScoreboardViewer} receives scoreboard display and line-update operations for one
 * presentation target. The interface separates objective display slots from scorer-line changes,
 * allowing scoreboard logic to maintain subscriptions while the viewer translates each notification
 * into its client protocol.
 *
 * @author daoge_cmd
 */
public interface ScoreboardViewer {
    /**
     * Displays the supplied objective and its lines in the requested client slot.
     */
    void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot);

    /**
     * Clears the objective currently displayed in the requested client slot.
     */
    void hideScoreboardSlot(DisplaySlot slot);

    /**
     * Removes the supplied objective from the viewer's client state, including slots displaying it.
     */
    void removeScoreboard(Scoreboard scoreboard);

    /**
     * Removes the supplied line from the viewer's displayed scoreboard data.
     */
    void removeScoreboardLine(ScoreboardLine line);

    /**
     * Sends the supplied line's current score to this viewer's presentation target.
     */
    void updateScore(ScoreboardLine line);

    /**
     * Returns whether this viewer remains eligible for scoreboard subscription and notifications.
     */
    boolean isScoreboardViewerValid();
}
