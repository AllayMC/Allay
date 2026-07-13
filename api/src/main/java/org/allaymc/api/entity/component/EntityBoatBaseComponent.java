package org.allaymc.api.entity.component;

import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.joml.Vector2fc;

/**
 * Boat-specific state and behavior.
 */
public interface EntityBoatBaseComponent extends EntityBaseComponent, EntityRideableComponent {
    /**
     * Gets the visual and material variant of this boat.
     *
     * @return the boat variant
     */
    BoatVariant getBoatVariant();

    /**
     * Sets the visual and material variant of this boat.
     *
     * @param variant the boat variant to set
     */
    void setBoatVariant(BoatVariant variant);

    /**
     * Gets the current left paddle rowing animation time.
     *
     * @return the left paddle rowing animation time
     */
    float getRowTimeLeft();

    /**
     * Gets the current right paddle rowing animation time.
     *
     * @return the right paddle rowing animation time
     */
    float getRowTimeRight();

    /**
     * Checks whether the left paddle is currently rowing.
     *
     * @return {@code true} if the left paddle is rowing, otherwise {@code false}
     */
    boolean isPaddlingLeft();

    /**
     * Checks whether the right paddle is currently rowing.
     *
     * @return {@code true} if the right paddle is rowing, otherwise {@code false}
     */
    boolean isPaddlingRight();

    /**
     * Updates the movement and paddle input supplied by a player. Input from a player who is not
     * controlling this boat is ignored.
     *
     * @param player      the player supplying the input
     * @param movement    the horizontal movement input
     * @param paddleLeft  whether the left paddle is active
     * @param paddleRight whether the right paddle is active
     */
    void setPaddleInput(EntityPlayer player, Vector2fc movement, boolean paddleLeft, boolean paddleRight);

    /**
     * Repositions passengers after the boat has moved.
     */
    void updatePassengerPositions();
}
