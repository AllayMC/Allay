package org.allaymc.api.bossbar;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@code BossBar} stores a boss bar's appearance and the viewers receiving its updates. Each viewer
 * displays one current boss bar, so displaying another bar can replace its client display without
 * changing this object's membership.
 *
 * <p>
 * {@code title}, {@code progress}, {@code color}, {@code style}, and {@code darkenSky} hold the
 * appearance sent together by {@code update}. Setters store their value before notifying
 * {@code viewers}; a viewer failure can therefore leave later viewers with an older display.
 * Membership changes also precede viewer callbacks. Callers must coordinate access to this mutable
 * object and its live viewer view; the implementation does not synchronize these compound
 * operations.
 *
 * @author daoge_cmd
 */
public class BossBar {

    /**
     * {@code viewers} contains subscribed viewers. {@code getViewers()} exposes a live unmodifiable
     * view, and membership changes precede display callbacks.
     */
    protected Set<BossBarViewer> viewers;
    /**
     * Text displayed above the progress bar.
     */
    protected String title;
    /**
     * Filled fraction of the progress bar.
     */
    protected float progress;
    /**
     * Color used for the displayed bar.
     */
    protected BossBarColor color;
    /**
     * Segmentation applied to the progress bar.
     */
    protected BossBarStyle style;
    /**
     * Whether the bar requests a darkened sky.
     */
    protected boolean darkenSky;

    /**
     * Creates an empty, full-progress boss bar with a blank title. The initial appearance uses
     * {@code PINK}, {@code SOLID}, and an undarkened sky; no viewer receives a display until
     * added.
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
     * Creates a new boss bar with its default appearance and no viewers.
     */
    public static BossBar create() {
        return new BossBar();
    }

    /**
     * Adds a viewer and displays this bar when membership changes. Adding an existing viewer does
     * nothing; if {@code viewBossBar} throws, the newly added viewer remains in {@code viewers}.
     */
    public void addViewer(BossBarViewer viewer) {
        if (viewers.add(viewer)) {
            viewer.viewBossBar(this);
        }
    }

    /**
     * Removes a viewer and clears that viewer's current boss bar when membership changes. A
     * callback failure leaves the viewer removed; removing an absent viewer performs no display
     * operation.
     */
    public void removeViewer(BossBarViewer viewer) {
        if (viewers.remove(viewer)) {
            viewer.clearBossBar();
        }
    }

    /**
     * Removes viewers by traversing the live viewer collection and calling {@code removeViewer}.
     * Structural changes during traversal can raise {@code ConcurrentModificationException}, so
     * this method can stop before every viewer is removed.
     */
    public void removeAllViewers() {
        getViewers().forEach(this::removeViewer);
    }

    /**
     * Returns a live unmodifiable view of this bar's viewer membership. Later additions and
     * removals are reflected in the result, and callers must coordinate iteration with membership
     * changes.
     */
    @UnmodifiableView
    public Collection<BossBarViewer> getViewers() {
        return Collections.unmodifiableSet(viewers);
    }

    /**
     * Returns the color currently stored for this boss bar.
     */
    public BossBarColor getColor() {
        return color;
    }

    /**
     * Stores the supplied color and sends the complete appearance to current viewers. The stored
     * value remains changed if a viewer callback throws, and later viewers may miss that update.
     */
    public void setColor(BossBarColor color) {
        this.color = color;
        update();
    }

    /**
     * Returns the segmentation style currently stored for this boss bar.
     */
    public BossBarStyle getStyle() {
        return style;
    }

    /**
     * Stores the supplied style and sends the complete appearance to current viewers. The stored
     * value remains changed if a viewer callback throws, and later viewers may miss that update.
     */
    public void setStyle(BossBarStyle style) {
        this.style = style;
        update();
    }

    /**
     * Returns whether this boss bar requests a darkened sky.
     */
    public boolean isDarkenSky() {
        return darkenSky;
    }

    /**
     * Stores the supplied sky-darkening flag and sends the complete appearance to current viewers.
     * The stored value remains changed if a viewer callback throws, and later viewers may miss that
     * update.
     */
    public void setDarkenSky(boolean darkenSky) {
        this.darkenSky = darkenSky;
        update();
    }

    /**
     * Returns the filled fraction currently stored for this boss bar.
     */
    @Range(from = 0, to = 1)
    public float getProgress() {
        return progress;
    }

    /**
     * Stores the filled fraction and sends the complete appearance to current viewers. Values
     * outside {@code [0, 1]}, including {@code NaN}, are rejected before storage; callback
     * failures leave the new progress stored.
     *
     * @throws IllegalArgumentException if {@code progress} is outside the accepted fraction range
     */
    public void setProgress(@Range(from = 0, to = 1) float progress) {
        Preconditions.checkArgument(progress >= 0 && progress <= 1, "Progress must be between 0 and 1");
        this.progress = progress;
        update();
    }

    /**
     * Returns the display title currently stored for this boss bar.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Stores the supplied title and sends the complete appearance to current viewers. The stored
     * value remains changed if a viewer callback throws, and later viewers may miss that update.
     */
    public void setTitle(String name) {
        this.title = name;
        update();
    }

    /**
     * Sends this bar's current appearance to each subscribed viewer. A viewer exception propagates
     * and stops the traversal, so callers cannot assume that all client displays received the same
     * update.
     */
    protected void update() {
        viewers.forEach(viewer -> viewer.viewBossBar(this));
    }
}
