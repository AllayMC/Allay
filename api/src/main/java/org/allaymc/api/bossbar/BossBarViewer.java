package org.allaymc.api.bossbar;

/**
 * BossBarViewer represents an object that can view boss bar.
 *
 * @author daoge_cmd
 */
public interface BossBarViewer {
    /**
     * Views the boss bar passed. This method is also used to update the boss bar
     * when its properties change.
     *
     * @param bossBar the boss bar to view
     */
    void viewBossBar(BossBar bossBar);

    /**
     * Clears the boss bar viewing by the viewer currently.
     */
    void clearBossBar();
}
