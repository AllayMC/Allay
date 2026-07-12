package org.allaymc.api.entity.component;

import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.joml.Vector2fc;

/**
 * Boat-specific state and behavior.
 */
public interface EntityBoatBaseComponent extends EntityBaseComponent, EntityRideableComponent {
    BoatVariant getBoatVariant();

    void setBoatVariant(BoatVariant variant);

    float getRowTimeLeft();

    float getRowTimeRight();

    boolean isPaddlingLeft();

    boolean isPaddlingRight();

    void setPaddleInput(EntityPlayer player, Vector2fc movement, boolean paddleLeft, boolean paddleRight);

    /**
     * Repositions passengers after the boat has moved.
     */
    void updatePassengerPositions();
}
