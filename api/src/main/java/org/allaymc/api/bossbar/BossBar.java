package org.allaymc.api.bossbar;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;

/**
 * BossBar will be shown at the top of the screen.
 *
 * @author daoge_cmd
 */
public interface BossBar {
    /**
     * Add a viewer to the boss bar.
     *
     * @param viewer the viewer to add.
     */
    void addViewer(EntityPlayer viewer);

    /**
     * Remove a viewer from the boss bar.
     *
     * @param viewer the viewer to remove.
     */
    void removeViewer(EntityPlayer viewer);

    /**
     * Get the viewers of the boss bar.
     *
     * @return the viewers of the boss bar.
     */
    @UnmodifiableView
    Collection<EntityPlayer> getViewers();

    /**
     * Get the color of the boss bar.
     *
     * @return the color of the boss bar.
     */
    BossBarColor getColor();

    /**
     * Set the color of the boss bar.
     *
     * @param color the color to set.
     */
    void setColor(BossBarColor color);

    /**
     * Check if the boss bar will darken the sky
     *
     * @return {@code true} if the boss bar will darken the sky, otherwise {@code false}.
     */
    boolean isDarkenSky();

    /**
     * Set if the boss bar will darken the sky.
     *
     * @param darkenSky {@code true} if the boss bar will darken the sky, otherwise {@code false}.
     */
    void setDarkenSky(boolean darkenSky);

    /**
     * Get the progress of the boss bar.
     *
     * @return the progress of the boss bar.
     */
    float getProgress();

    /**
     * Set the progress of the boss bar.
     *
     * @param progress the progress to set.
     */
    void setProgress(float progress);

    /**
     * Get the title of the boss bar.
     *
     * @return the title of the boss bar.
     */
    String getTitle();

    /**
     * Set the title of the boss bar.
     *
     * @param name the title to set.
     */
    void setTitle(String name);
}
