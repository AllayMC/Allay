package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.CompassRoseDirection;

/**
 * @author daoge_cmd
 */
public interface BlockEntityHeadBaseComponent extends BlockEntityBaseComponent {

    /**
     * Retrieves the direction based on the 16-point compass rose.
     *
     * @return the current {@link CompassRoseDirection} of the block entity
     */
    CompassRoseDirection getDirection();

    /**
     * Sets the direction of the BlockEntityHead.
     *
     * @param direction the {@code CompassRoseDirection} to set. Accepts one of the 16 directions
     *                  represented by the compass rose. For example, {@code NORTH}, {@code EAST},
     *                  or any of the intercardinal directions like {@code NORTH_EAST}.
     */
    void setDirection(CompassRoseDirection direction);

    /**
     * Checks whether the head is playing animation. For example, when the pig spirit head is fanning
     * its ears or the mouth of the dragon head is moving.
     *
     * @return {@code true} if the head is playing animation, otherwise {@code false}
     */
    boolean isPlayingAnimation();

    /**
     * Sets whether the head block entity is playing an animation.
     *
     * @param playingAnimation {@code true} to set the block entity to play an animation,
     *                         {@code false} to stop the animation
     */
    void setPlayingAnimation(boolean playingAnimation);

    /**
     * Gets the total number of ticks in which the head plays the animation.
     *
     * @return the total number of ticks in which the head plays the animation
     */
    int getAnimationTicks();

    /**
     * Sets the total number of ticks during which the animation for the head block entity will be played.
     *
     * @param ticks the number of ticks to set. Must be a positive integer representing the duration of the animation.
     */
    void setAnimationTicks(int ticks);
}
