package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityBellBaseComponent} exposes a bell's ringing flag, animation
 * counter, strike direction, and ring operation. The server starts a ring by
 * resetting its counter, plays a strike sound, and sends state to viewers until
 * the ringing interval finishes.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBellBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns whether the bell currently marks its ringing animation active.
     */
    boolean isRinging();

    /**
     * Replaces the stored ringing flag without initiating a bell strike.
     */
    void setRinging(boolean ringing);

    /**
     * Returns the number of elapsed ticks in the ringing animation.
     */
    int getTicks();

    /**
     * Replaces the elapsed ringing-animation counter with the supplied tick
     * value.
     */
    void setTicks(int ticks);

    /**
     * Returns the stored horizontal direction from which the bell was struck.
     */
    int getDirection();

    /**
     * Replaces the stored horizontal strike direction used by bell animation.
     */
    void setDirection(int direction);

    /**
     * Starts a new bell strike using the supplied horizontal direction. The
     * server resets elapsed ticks, marks ringing active, plays the hit sound,
     * and sends the new state to viewers.
     */
    void ring(int direction);
}
