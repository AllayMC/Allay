package org.allaymc.api.entity;

import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.ServerSettings;
import org.joml.Vector3dc;

/**
 * EntityViewer represents an object that can view entities.
 *
 * @author daoge_cmd
 */
public interface EntityViewer {
    /**
     * Views the entity passed.
     *
     * @param entity the entity to view
     */
    void viewEntity(Entity entity);

    /**
     * Removes the entity passed.
     *
     * @param entity the entity to remove
     */
    void removeEntity(Entity entity);

    /**
     * Views the gametype of an {@link EntityPlayer}.
     *
     * @param player the player to view
     */
    void viewPlayerGameType(EntityPlayer player);

    /**
     * Views the location of the entity passed.
     *
     * @param entity           the entity to view
     * @param locationLastSent the location that is sent in the last method call. Changes will be applied to this parameter when the difference in a
     *                         specific field (e.g. x-axis) exceeds the threshold defined in {@link ServerSettings.EntitySettings.PhysicsEngineSettings}.
     *                         This field is used only when {@link ServerSettings.EntitySettings.PhysicsEngineSettings#useDeltaMovePacket()} is enabled.
     * @param newLocation      the new location of the entity
     * @param teleporting      whether the entity is being teleported to the new location
     */
    void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting);

    /**
     * Views the motion of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion);

    /**
     * View the metadata of the entity passed.
     *
     * @param entity the entity to view
     */
    void viewEntityMetadata(Entity entity);

    /**
     * View the item in main hand of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity);

    /**
     * View the item in offhand of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity);

    /**
     * View the armors of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity);

    /**
     * View the skin of the player passed.
     *
     * @param player the player to view
     */
    void viewPlayerSkin(EntityPlayer player);
}
