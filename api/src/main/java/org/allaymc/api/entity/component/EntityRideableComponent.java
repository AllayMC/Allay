package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Component for vehicles which can carry player passengers.
 */
public interface EntityRideableComponent extends EntityComponent {
    /**
     * Gets the maximum number of passengers this entity can carry.
     *
     * @return the passenger capacity
     */
    int getPassengerCapacity();

    /**
     * Gets the players currently riding this entity, ordered by seat.
     *
     * @return an unmodifiable list of passengers
     */
    @UnmodifiableView
    List<EntityPlayer> getPassengers();

    /**
     * Gets the passenger currently controlling this entity.
     *
     * @return the controlling passenger, or {@code null} if this entity has no controller
     */
    @Nullable EntityPlayer getControllingPassenger();

    /**
     * Attempts to add a player as a passenger.
     *
     * @param passenger the player to add
     * @return {@code true} if the player was added, otherwise {@code false}
     */
    boolean addPassenger(EntityPlayer passenger);

    /**
     * Attempts to remove a player from this entity.
     *
     * @param passenger the player to remove
     * @return {@code true} if the player was removed, otherwise {@code false}
     */
    boolean removePassenger(EntityPlayer passenger);

    /**
     * Removes all passengers from this entity.
     */
    void ejectPassengers();
}
