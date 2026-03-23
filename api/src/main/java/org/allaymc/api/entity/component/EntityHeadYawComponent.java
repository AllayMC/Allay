package org.allaymc.api.entity.component;

/**
 * Component for entities whose head yaw can rotate independently from body yaw.
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
