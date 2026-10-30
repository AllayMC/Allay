package org.allaymc.api.entity.component;

/**
 * {@code EntityHeadYawComponent} exposes an entity's head rotation separately
 * from its body rotation. Head yaw uses degrees and can change without
 * replacing the entity location's body yaw, allowing movement encoding to
 * represent where the entity looks independently of its facing.
 *
 * @author daoge_cmd
 */
public interface EntityHeadYawComponent extends EntityComponent {

    /**
     * Returns the independently stored head yaw angle in degrees.
     */
    double getHeadYaw();

    /**
     * Sets head yaw in degrees independently of body yaw.
     */
    void setHeadYaw(double headYaw);
}
