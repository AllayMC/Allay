package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Component for vehicles which can carry player passengers.
 */
public interface EntityRideableComponent extends EntityComponent {
    int getPassengerCapacity();

    @UnmodifiableView
    List<EntityPlayer> getPassengers();

    @Nullable EntityPlayer getControllingPassenger();

    boolean addPassenger(EntityPlayer passenger);

    boolean removePassenger(EntityPlayer passenger);

    void ejectPassengers();
}
