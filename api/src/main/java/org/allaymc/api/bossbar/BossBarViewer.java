package org.allaymc.api.bossbar;

/**
 * {@code BossBarViewer} receives commands to display or clear a boss bar. {@code BossBar} invokes
 * the same display operation for initial viewing and later appearance changes, while clearing
 * targets the viewer's current display rather than a particular bar identity.
 *
 * @author daoge_cmd
 */
public interface BossBarViewer {
    /**
     * Displays the supplied bar using its current appearance, replacing the current client display
     * when necessary. {@code BossBar} also calls this operation after appearance changes, so
     * implementations must support repeated display requests.
     */
    void viewBossBar(BossBar bossBar);

    /**
     * Clears the boss bar currently displayed by this viewer.
     */
    void clearBossBar();
}
