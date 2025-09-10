package org.allaymc.api.entity;

import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.AnimateAction;
import org.allaymc.api.entity.data.EntityEvent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.ServerSettings;
import org.joml.Vector3dc;

import java.util.UUID;

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
     * Views the game mode of an {@link EntityPlayer}.
     *
     * @param player the player to view
     */
    void viewPlayerGameMode(EntityPlayer player);

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
     * Views the metadata of the entity passed.
     *
     * @param entity the entity to view
     */
    void viewEntityMetadata(Entity entity);

    /**
     * Views the item in main hand of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity);

    /**
     * Views the item in offhand of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity);

    /**
     * Views the armors of the entity passed.
     *
     * @param entity the entity to view
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity);

    /**
     * Views the skin of the player passed.
     *
     * @param player the player to view
     */
    void viewPlayerSkin(EntityPlayer player);

    /**
     * Views an event that is happening on the entity passed.
     *
     * @param entity the entity to view
     * @param event  the event that is happening
     * @param data   the data of this event
     */
    void viewEntityEvent(Entity entity, EntityEvent event, int data);

    /**
     * Views an action that is happening on the entity passed.
     *
     * @param entity     the entity to view
     * @param action     the action that is happening
     * @param rowingTime the rowing time of the action
     */
    void viewEntityAction(Entity entity, AnimateAction action, double rowingTime);

    /**
     * Views an emote being performed by a player.
     *
     * @param player  the player who performed the emote
     * @param emoteId the id of the emote
     */
    void viewPlayerEmote(EntityPlayer player, UUID emoteId);

    /**
     * Views an effect change in the entity passed.
     *
     * @param entity    the entity to view
     * @param newEffect the new effect instance. Can be {@code null} if the effect is removed
     * @param oldEffect the old effect instance. Can be {@code null} if the effect is not exist in the entity before
     */
    void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect);
}
