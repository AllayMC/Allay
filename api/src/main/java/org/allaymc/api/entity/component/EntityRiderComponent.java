package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * State held by an entity which can ride another entity.
 */
public interface EntityRiderComponent extends EntityComponent {
    @Nullable Entity getVehicle();

    default boolean isRiding() {
        return getVehicle() != null;
    }

    /**
     * Updates the other side of a ride relationship. Rideable components are the public entry point.
     */
    @ApiStatus.Internal
    void setVehicle(@Nullable Entity vehicle);
}
