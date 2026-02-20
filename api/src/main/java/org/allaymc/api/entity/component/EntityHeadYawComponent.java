package org.allaymc.api.entity.component;

/**
 * Component that provides independent head yaw rotation for entities.
 * <p>
 * Entities that register this component can rotate their head independently
 * of their body yaw (which is stored in the entity's location).
 *
 * @author daoge_cmd
 */
public interface EntityHeadYawComponent extends EntityComponent {

    /**
     * Gets the head yaw of this entity.
     *
     * @return the head yaw in degrees
     */
    double getHeadYaw();

    /**
     * Sets the head yaw of this entity independently from the body yaw.
     *
     * @param headYaw the head yaw in degrees
     */
    void setHeadYaw(double headYaw);
}
