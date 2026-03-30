package org.allaymc.api.bossbar;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a boss bar, a visual element often used in games to display progress, health, or status.
 * It supports customization options such as color, style, title, progress, and visibility behavior.
 *
 * @author daoge_cmd
 */
public class BossBar {

    protected Set<BossBarViewer> viewers;
    protected String title;
    protected float progress;
    protected BossBarColor color;
    protected BossBarStyle style;
    protected boolean darkenSky;

    /**
     * Create a new boss bar.
     */
    public BossBar() {
        this.viewers = new HashSet<>();
        this.title = "";
        this.progress = 1.0f;
        this.color = BossBarColor.PINK;
        this.style = BossBarStyle.SOLID;
        this.darkenSky = false;
    }

    /**
     * Create a new boss bar.
     *
     * @return the boss bar
     */
    public static BossBar create() {
        return new BossBar();
    }

    /**
     * Add a viewer to the boss bar.
     *
     * @param viewer the viewer to add
     */
    public void addViewer(BossBarViewer viewer) {
        if (viewers.add(viewer)) {
            viewer.viewBossBar(this);
        }
    }

    /**
     * Remove a viewer from the boss bar.
     *
     * @param viewer the viewer to remove
     */
    public void removeViewer(BossBarViewer viewer) {
        if (viewers.remove(viewer)) {
            viewer.clearBossBar();
        }
    }

    /**
     * Remove all viewers from the boss bar.
     */
    public void removeAllViewers() {
        getViewers().forEach(this::removeViewer);
    }

    /**
     * Get the viewers of the boss bar.
     *
     * @return the viewers of the boss bar
     */
    @UnmodifiableView
    public Collection<BossBarViewer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    /**
     * Get the color of the boss bar.
     *
     * @return the color of the boss bar
     */
    public BossBarColor getColor() {
        return color;
    }

    /**
     * Set the color of the boss bar.
     *
     * @param color the color to set
     */
    public void setColor(BossBarColor color) {
        this.color = color;
        update();
    }

    /**
     * Get the style of the boss bar.
     *
     * @return the style of the boss bar
     */
    public BossBarStyle getStyle() {
        return style;
    }

    /**
     * Set the style of the boss bar.
     *
     * @param style the style to set
     */
    public void setStyle(BossBarStyle style) {
        this.style = style;
        update();
    }

    /**
     * Check if the boss bar will darken the sky
     *
     * @return {@code true} if the boss bar will darken the sky, otherwise {@code false}.
     */
    public boolean isDarkenSky() {
        return darkenSky;
    }

    /**
     * Set if the boss bar will darken the sky.
     *
     * @param darkenSky {@code true} if the boss bar will darken the sky, otherwise {@code false}
     */
    public void setDarkenSky(boolean darkenSky) {
        this.darkenSky = darkenSky;
        update();
    }

    /**
     * Get the progress of the boss bar.
     *
     * @return the progress of the boss bar, between 0 and 1
     */
    @Range(from = 0, to = 1)
    public float getProgress() {
        return progress;
    }

    /**
     * Set the progress of the boss bar.
     *
     * @param progress the progress to set, between 0 and 1
     */
    public void setProgress(@Range(from = 0, to = 1) float progress) {
        Preconditions.checkArgument(progress >= 0 && progress <= 1, "Progress must be between 0 and 1");
        this.progress = progress;
        update();
    }

    /**
     * Get the title of the boss bar.
     *
     * @return the title of the boss bar
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the boss bar.
     *
     * @param name the title to set
     */
    public void setTitle(String name) {
        this.title = name;
        update();
    }

    protected void update() {
        viewers.forEach(viewer -> viewer.viewBossBar(this));
    }
}
