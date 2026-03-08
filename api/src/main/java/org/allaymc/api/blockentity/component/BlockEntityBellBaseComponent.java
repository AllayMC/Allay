package org.allaymc.api.blockentity.component;

/**
 * Base component for bell block entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBellBaseComponent extends BlockEntityBaseComponent {
    /**
     * Check if the bell is currently ringing.
     *
     * @return true if the bell is ringing
     */
    boolean isRinging();

    /**
     * Set the ringing state of the bell.
     *
     * @param ringing whether the bell should be ringing
     */
    void setRinging(boolean ringing);

    /**
     * Get the remaining ticks of the ringing animation.
     *
     * @return the remaining ticks
     */
    int getTicks();

    /**
     * Set the remaining ticks of the ringing animation.
     *
     * @param ticks the remaining ticks
     */
    void setTicks(int ticks);

    /**
     * Get the direction from which the bell was struck.
     *
     * @return the direction (0-3 for horizontal directions)
     */
    int getDirection();

    /**
     * Set the direction from which the bell was struck.
     *
     * @param direction the direction (0-3 for horizontal directions)
     */
    void setDirection(int direction);

    /**
     * Ring the bell from the specified direction.
     *
     * @param direction the direction from which the bell is struck
     */
    void ring(int direction);
}
