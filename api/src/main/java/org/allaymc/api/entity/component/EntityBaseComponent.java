package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.EntityMoveEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent, CommandSender, HasAABB, HasLongId, PersistentDataHolder {

    /**
     * Gets the type of this entity.
     *
     * @return the type of this entity.
     */
    EntityType<? extends Entity> getEntityType();

    /**
     * Gets the display name of this entity.
     *
     * @return the display name of this entity.
     */
    String getDisplayName();

    /**
     * Sets the display name of this entity.
     *
     * @param displayName the display name to set.
     */
    void setDisplayName(String displayName);

    /**
     * Gets the name tag of this entity.
     *
     * @return the name tag of this entity.
     */
    default String getNameTag() {
        return getMetadata().get(EntityDataTypes.NAME);
    }

    /**
     * Sets the name tag of this entity.
     *
     * @param nameTag the name tag to set.
     */
    default void setNameTag(String nameTag) {
        setAndSendEntityData(EntityDataTypes.NAME, nameTag);
    }

    /**
     * Clears the name tag of this entity.
     */
    default void clearNameTag() {
        setAndSendEntityData(EntityDataTypes.NAME, "");
    }

    /**
     * Gets the last location of this entity.
     *
     * @return the last location of this entity.
     */
    Location3dc getLastLocation();

    /**
     * Gets the location of this entity.
     *
     * @return the location of this entity.
     */
    Location3dc getLocation();

    /**
     * Set the location before the entity is spawned.
     * <p>
     * This method is usually used when you want to spawn the entity at a specific location.
     * Then you need to set the entity's location before spawn the entity.
     *
     * @param location the location you want to set.
     * @throws IllegalStateException if the entity is already spawned.
     */
    void setLocationBeforeSpawn(Location3dc location);

    /**
     * Gets the dimension of this entity.
     *
     * @return the dimension of this entity.
     */
    Dimension getDimension();

    /**
     * Gets the world of this entity.
     *
     * @return the world of this entity.
     */
    default World getWorld() {
        return getLocation().dimension() != null ? getLocation().dimension().getWorld() : null;
    }

    /**
     * Get the status of the entity.
     *
     * @return the status of the entity.
     */
    EntityStatus getStatus();

    /**
     * Check if the entity will be spawned in the next tick.
     *
     * @return {@code true} if the entity will be spawned in the next tick.
     */
    default boolean willBeSpawnedNextTick() {
        return getStatus() == EntityStatus.SPAWNED_NEXT_TICK;
    }

    /**
     * Check if the entity will be despawned in the next tick.
     *
     * @return {@code true} if the entity will be despawned in the next tick.
     */
    default boolean willBeDespawnedNextTick() {
        return getStatus() == EntityStatus.DESPAWNED_NEXT_TICK;
    }

    /**
     * Check if the entity is dead.
     *
     * @return {@code true} if the entity is dead.
     */
    default boolean isDead() {
        return getStatus() == EntityStatus.DEAD;
    }

    /**
     * Check if the entity is spawned.
     *
     * @return {@code true} if the entity is spawned.
     */
    default boolean isSpawned() {
        return getStatus().isSpawned();
    }

    /**
     * Check if the entity is alive.
     *
     * @return {@code true} if the entity is alive.
     */
    default boolean isAlive() {
        return getStatus() == EntityStatus.ALIVE;
    }

    /**
     * Check if the entity can be spawned.
     *
     * @return {@code true} if the entity can be spawned.
     */
    boolean canBeSpawned();

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to.
     * @return {@code true} if the operation is valid, {@code false} otherwise (event being cancelled).
     */
    default boolean teleport(Location3dc location) {
        return teleport(location, EntityTeleportEvent.Reason.UNKNOWN);
    }

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to.
     * @param reason   the reason of the teleport.
     * @return {@code true} if the operation is valid, {@code false} otherwise (event being cancelled).
     */
    boolean teleport(Location3dc location, EntityTeleportEvent.Reason reason);

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to.
     */
    default boolean teleport(Location3ic location) {
        return teleport(new Location3d(location));
    }

    /**
     * Teleport the entity to the specified location asynchronously.
     * <p>
     * This method is safe to be used in world thread.
     *
     * @param location the location to teleport the entity to.
     */
    default void teleportAsync(Location3dc location) {
        Thread.ofVirtual().start(() -> teleport(location));
    }

    /**
     * Teleport the entity to the specified location asynchronously.
     * <p>
     * This method is safe to be used in world thread.
     *
     * @param location the location to teleport the entity to.
     */
    default void teleportAsync(Location3ic location) {
        teleportAsync(new Location3d(location));
    }

    /**
     * Get the runtime id of this entity.
     *
     * @return the runtime id of this entity.
     */
    long getRuntimeId();

    /**
     * Same to {@link #getRuntimeId()}.
     */
    @Override
    default long getLongId() {
        return getRuntimeId();
    }

    /**
     * Get the unique id of this entity.
     *
     * @return the unique id of this entity.
     */
    long getUniqueId();

    /**
     * Get the metadata of this entity.
     *
     * @return the metadata of this entity.
     */
    Metadata getMetadata();

    /**
     * Send the entity metadata to the viewers.
     */
    void sendMetadata();

    /**
     * Set and send the entity data to the viewers.
     *
     * @param dataType the data type to set.
     * @param value    the value to set.
     * @param <T>      the type of the value.
     */
    default <T> void setAndSendEntityData(EntityDataType<T> dataType, T value) {
        getMetadata().set(dataType, value);
        sendMetadata();
    }

    /**
     * Set and send the entity flag to the viewers.
     *
     * @param flag  the flag to set.
     * @param value the value to set.
     */
    default void setAndSendEntityFlag(EntityFlag flag, boolean value) {
        if (value == getMetadata().get(flag)) {
            return;
        }
        getMetadata().set(flag, value);
        sendMetadata();
    }

    /**
     * Get the aabb of this entity.
     *
     * @return the aabb of this entity.
     */
    AABBdc getAABB();

    /**
     * Get the offset aabb of this entity.
     *
     * @return the offset aabb of this entity.
     */
    default AABBd getOffsetAABB() {
        return getAABB().translate(getLocation(), new AABBd());
    }

    /**
     * Get the offset aabb of this entity for collision check.
     *
     * @return the offset aabb of this entity for collision check.
     */
    AABBd getOffsetAABBForCollisionCheck();

    /**
     * Check if this entity has entity collision. If return {@code true}, method {@link #onCollideWithEntity(Entity)}
     * will be called when this entity collide with another entity.
     *
     * @return {@code true} if the entity has entity collision.
     */
    default boolean hasEntityCollision() {
        return getMetadata().get(EntityFlag.HAS_COLLISION);
    }

    /**
     * Set if the entity has collision.
     *
     * @param hasEntityCollision {@code true} if the entity has collision, {@code false} otherwise.
     */
    default void setHasEntityCollision(boolean hasEntityCollision) {
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, hasEntityCollision);
    }

    /**
     * Called when the entity collides with another entity.
     *
     * @param other the entity collides with.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(Entity other) {
    }

    /**
     * Called when the entity is inside a block.
     *
     * @param block the block that this entity inside.
     */
    @ApiStatus.OverrideOnly
    default void onInsideBlock(Block block) {
    }

    /**
     * Called when the entity collides with a block.
     *
     * @param block the block that collides with this entity.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithBlock(Block block) {
    }

    /**
     * Called when the entity is hit by a projectile.
     *
     * @param projectile the projectile that hits the entity.
     * @param hitPos     the hit pos.
     */
    @ApiStatus.OverrideOnly
    default void onProjectileHit(EntityProjectile projectile, Vector3dc hitPos) {
    }

    /**
     * Get the viewers of this entity.
     *
     * @return the viewers of this entity.
     */
    @UnmodifiableView
    Map<Long, EntityPlayer> getViewers();

    /**
     * Try to set this entity's location. This method is intended to be called by physics engine,
     * and user is not expected to call it.
     * <p>
     * The implementation will call {@link EntityMoveEvent}, and because this event is cancellable,
     * the method call may not be valid.
     *
     * @param newLocation the new location that this entity moves to.
     * @return {@code true} if the method call is valid, {@code false} otherwise.
     */
    @ApiStatus.OverrideOnly
    boolean trySetLocation(Location3dc newLocation);

    /**
     * Spawn the entity to the specified player.
     *
     * @param player the player to spawn the entity to.
     */
    void spawnTo(EntityPlayer player);

    /**
     * Spawn the entity to the specified players.
     *
     * @param players the players to spawn the entity to.
     */
    default void spawnTo(Collection<EntityPlayer> players) {
        players.forEach(this::spawnTo);
    }

    /**
     * Despawn the entity from the specified player.
     * <p>
     * This method will only remove the entity from the specific viewer, and it will still exist in the world.
     *
     * @param player the player to despawn the entity from.
     */
    void despawnFrom(EntityPlayer player);

    /**
     * Despawn the entity from all viewers.
     * <p>
     * This method will only remove the entity from the viewers, and it will still exist in the world.
     */
    void despawnFromAll();

    /**
     * Despawn the entity.
     * <p>
     * Compared to {@link #despawnFromAll()}, this method will also remove the entity from the world.
     */
    void despawn();

    /**
     * Create the spawn packet of this entity.
     *
     * @return the spawn packet of this entity.
     */
    BedrockPacket createSpawnPacket();

    /**
     * Send a packet to the viewers of this entity.
     *
     * @param packet the packet to send.
     */
    void sendPacketToViewers(BedrockPacket packet);

    /**
     * Send a packet to the viewers of this entity immediately.
     *
     * @param packet the packet to send.
     */
    void sendPacketToViewersImmediately(BedrockPacket packet);

    /**
     * Save the entity to NBT.
     *
     * @return the NBT of this entity.
     */
    NbtMap saveNBT();

    /**
     * Save the entity to NBT without position.
     *
     * @return the NBT of this entity without position.
     */
    default NbtMap saveNBTWithoutPos() {
        var builder = saveNBT().toBuilder();
        builder.remove("Pos");
        return builder.build();
    }

    /**
     * Load the entity from NBT.
     *
     * @param nbt the NBT to load.
     */
    void loadNBT(NbtMap nbt);

    /**
     * Called when the entity is splashed by a splash water bottle.
     */
    @ApiStatus.OverrideOnly
    default void onSplash() {
    }

    /**
     * Get all the effects of the entity.
     *
     * @return all the effects of the entity.
     */
    @UnmodifiableView
    Map<EffectType, EffectInstance> getAllEffects();

    /**
     * Check if the entity has the specified effect.
     *
     * @param effectType the effect type to check.
     * @return {@code true} if the entity has the specified effect, otherwise {@code false}.
     */
    boolean hasEffect(EffectType effectType);

    /**
     * Get the effect level of the specified effect.
     *
     * @param effectType the effect type to get.
     * @return the effect level of the specified effect.
     */
    int getEffectLevel(EffectType effectType);

    /**
     * Add the specified effect to the entity.
     *
     * @param effectInstance the effect instance to add.
     * @return {@code true} if the effect is added successfully, otherwise {@code false}.
     */
    boolean addEffect(EffectInstance effectInstance);

    /**
     * Remove the specified effect from the entity.
     *
     * @param effectType the effect type to remove.
     */
    void removeEffect(EffectType effectType);

    /**
     * Remove all effects from the entity.
     */
    void removeAllEffects();

    /**
     * Check if the entity has head yaw.
     *
     * @return {@code true} if the entity has head yaw, otherwise {@code false}.
     */
    default boolean enableHeadYaw() {
        return false;
    }

    /**
     * Get the network offset of this entity.
     * <p>
     * The network offset is the additional offset in y coordinate when sent over network.
     * This is mostly the case for older entities such as players and TNT.
     *
     * @return the base offset of this entity.
     */
    default float getNetworkOffset() {
        return 0;
    }

    /**
     * Get the eye height of this entity.
     *
     * @return the eye height of this entity.
     */
    default double getEyeHeight() {
        return (getAABB().maxY() - getAABB().minY()) * 0.9;
    }

    /**
     * Check if the chunk which the entity's location is in is loaded.
     *
     * @return {@code true} if the chunk is loaded, otherwise {@code false}.
     */
    default boolean isCurrentChunkLoaded() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkService().isChunkLoaded(cx, cz);
    }

    /**
     * Check if the y coordinate of the entity's location is in the range of the dimension.
     *
     * @return {@code true} if the y coordinate is in the range, otherwise {@code false}.
     */
    default boolean isYInRange() {
        var loc = getLocation();
        return loc.dimension().isYInRange(loc.y());
    }

    /**
     * Check if the entity is in the world.
     *
     * @return {@code true} if the entity is in the world, otherwise {@code false}.
     */
    default boolean isInWorld() {
        return isYInRange() && isCurrentChunkLoaded();
    }

    /**
     * Get the current chunk of the entity.
     *
     * @return the current chunk of the entity, or {@code null} if the chunk is not loaded.
     */
    default Chunk getCurrentChunk() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkService().getChunk(cx, cz);
    }

    /**
     * Get the horizontal face of the entity.
     *
     * @return the horizontal face of the entity.
     */
    default BlockFace getHorizontalFace() {
        var rotation = getLocation().yaw() % 360;
        if (rotation < 0) rotation += 360.0;

        if (45 <= rotation && rotation < 135) {
            return BlockFace.WEST;
        } else if (135 <= rotation && rotation < 225) {
            return BlockFace.NORTH;
        } else if (225 <= rotation && rotation < 315) {
            return BlockFace.EAST;
        } else {
            return BlockFace.SOUTH;
        }
    }

    /**
     * Apply the entity event to the entity.
     *
     * @param event the entity event to apply.
     * @param data  the data of the entity event.
     */
    default void applyEntityEvent(EntityEventType event, int data) {
        var pk = new EntityEventPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setType(event);
        pk.setData(data);
        sendPacketToViewers(pk);
    }

    /**
     * Apply an action to the entity.
     *
     * @param action the action to apply.
     */
    default void applyAction(AnimatePacket.Action action) {
        applyAction(action, 0);
    }

    /**
     * Apply an action to the entity.
     *
     * @param action     the action of the action.
     * @param rowingTime the rowing time of the action.
     */
    default void applyAction(AnimatePacket.Action action, double rowingTime) {
        var pk = new AnimatePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setAction(action);
        pk.setRowingTime((float) rowingTime);
        sendPacketToViewers(pk);
    }

    /**
     * Add a tag to the entity.
     *
     * @param tag the tag to add.
     * @return {@code true} if the tag is added, otherwise {@code false}.
     */
    boolean addTag(String tag);

    /**
     * Remove a tag from the entity.
     *
     * @param tag the tag to remove.
     * @return {@code true} if the tag is removed, otherwise {@code false}.
     */
    boolean removeTag(String tag);

    /**
     * Check if the entity has the specified tag.
     *
     * @param tag the tag to check.
     * @return {@code true} if the entity has the specified tag, otherwise {@code false}.
     */
    boolean hasTag(String tag);

    /**
     * Get the tags of the entity.
     *
     * @return the tags of the entity.
     */
    @UnmodifiableView
    Set<String> getTags();

    /**
     * Checks if the entity is colliding with any blocks.
     *
     * @return {@code true} if the entity is colliding with any blocks, otherwise {@code false}.
     */
    boolean checkBlockCollision();

    /**
     * Check if the entity's eyes is in water.
     *
     * @return {@code true} if the entity's eyes is in water, otherwise {@code false}.
     */
    default boolean isEyesInWater() {
        var dim = getDimension();
        var eyeLoc = getLocation().add(0, getEyeHeight(), 0, new Vector3d());
        var eyesBlockState = dim.getBlockState(eyeLoc);

        return eyesBlockState.getBlockType().hasBlockTag(BlockTags.WATER) &&
               eyesBlockState.getBlockStateData().computeOffsetShape(MathUtils.floor(eyeLoc)).intersectsPoint(eyeLoc);
    }

    /**
     * Check if the entity is touching water.
     *
     * @return {@code true} if the entity is touching water, otherwise {@code false}.
     */
    default boolean isTouchingWater() {
        var dim = getDimension();
        var loc = getLocation();
        var blockState = dim.getBlockState(loc);

        return blockState.getBlockType().hasBlockTag(BlockTags.WATER) &&
               blockState.getBlockStateData().computeOffsetShape(MathUtils.floor(loc)).intersectsPoint(loc);
    }

    /**
     * Check if the entity can breathe.
     *
     * @return {@code true} if the entity can breathe, otherwise {@code false}.
     */
    default boolean canBreathe() {
        return hasEffect(EffectTypes.WATER_BREATHING) || hasEffect(EffectTypes.CONDUIT_POWER) || !isEyesInWater();
    }

    /**
     * Check if the specific effect can apply on the entity.
     *
     * @param effectType the specific effect
     * @return {@code true} if the specific effect can apply on the entity, otherwise {@code false}.
     */
    default boolean canApplyEffect(EffectType effectType) {
        return true;
    }

    /**
     * Called when the entity interacts with another entity.
     *
     * @param player    The player who interacted with the entity, can be null
     * @param itemStack The item used to interact with the entity
     * @return {@code true} if the interaction is successful
     */
    default boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return false;
    }

    /**
     * Check if the entity will be saved through {@link org.allaymc.api.world.storage.WorldStorage}.
     * If you don't want the entity to be saved, or you want to save the entity by yourself, you can
     * override this method and return {@code false}.
     * <p>
     * When return {@code false}, the entity will always be loaded, and {@link org.allaymc.api.world.service.EntityService}
     * will not remove and save the entity even if the entity is in unloaded chunk. The entity can only be removed
     * manually in this case.
     * <p>
     * For example, {@link EntityPlayer} is handled by {@link org.allaymc.api.client.storage.PlayerStorage},
     * and this method is override to return {@code false} in {@link EntityPlayer}.
     *
     * @return {@code true} if the entity will be saved, otherwise {@code false}.
     */
    default boolean willBeSaved() {
        return true;
    }
}
