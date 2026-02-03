package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.EntityMoveEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.PlayerStorage;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent, CommandSender, HasAABB, HasLongId, PersistentDataHolder, TaskCreator {

    /**
     * Gets the type of this entity.
     *
     * @return the type of this entity
     */
    EntityType<? extends Entity> getEntityType();

    /**
     * Broadcasts the current entity state to all its viewers.
     */
    void broadcastState();

    /**
     * Get the display name of the entity.
     * <p>
     * Display name is used in chat, damage message, etc. Normally, it is equal to the origin name.
     * However, you can change the display name compared to the origin name. This is very useful for
     * plugins, especially if the plugin wants to change the appearance of entity's name in chat because
     * its origin name cannot be changed.
     *
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Sets the display name of this entity.
     *
     * @param displayName the display name to set
     */
    void setDisplayName(String displayName);

    /**
     * Gets the name tag of this entity.
     *
     * @return the name tag of this entity
     */
    String getNameTag();

    /**
     * Sets the name tag of this entity.
     *
     * @param nameTag the name tag to set, or {@code null} to clear the name tag
     */
    void setNameTag(String nameTag);

    /**
     * Determines whether the entity has a name tag assigned.
     *
     * @return {@code true} if the entity has a name tag; {@code false} otherwise
     */
    default boolean hasNameTag() {
        return getNameTag() != null;
    }

    /**
     * Determines whether the name tag is always displayed for an entity or object.
     *
     * @return {@code true} if the name tag is set to always show, {@code false} otherwise.
     */
    boolean isNameTagAlwaysShow();

    /**
     * Sets whether the name tag of an entity should always be visible above it.
     *
     * @param nameTagAlwaysShow a boolean value where {@code true} makes the name tag always visible,
     *                          and {@code false} causes it to only display under certain conditions.
     */
    void setNameTagAlwaysShow(boolean nameTagAlwaysShow);

    /**
     * Checks whether this entity is visible to its viewers.
     *
     * @return whether this entity is visible to its viewers
     */
    boolean isInvisible();

    /**
     * Sets whether this entity is invisible to its viewers. When set to {@code true}, this
     * entity will not be visible to its viewers. However, the item in its hand/offhand, the
     * armor it wears and the effect particle around this entity will still remain visible.
     *
     * @param invisible whether the body of this entity is invisible to its viewers
     */
    void setInvisible(boolean invisible);

    /**
     * Checks if the entity is currently immobile.
     *
     * @return {@code true} if the Player is currently immobile.
     */
    boolean isImmobile();

    /**
     * Sets the immobile state of an object. When set to {@code true}, the entity can't move
     * around, but is still allowed to look around.
     *
     * @param immobile {@code true} to make the entity immobile, {@code false} to make it mobile.
     */
    void setImmobile(boolean immobile);

    /**
     * Gets the scale of the entity, greater scale will result in a bigger model and aabb.
     * The default value is 1.0
     *
     * @return the scale of the entity
     */
    double getScale();

    /**
     * Sets the scale of the entity.
     *
     * @param scale the scale of the entity to set
     */
    void setScale(double scale);

    /**
     * Gets the last location of this entity.
     *
     * @return the last location of this entity
     */
    Location3dc getLastLocation();

    /**
     * Gets the location of this entity.
     *
     * @return the location of this entity
     */
    Location3dc getLocation();

    /**
     * Set the location before the entity is spawned.
     * <p>
     * This method is usually used when you want to spawn the entity at a specific location.
     * Then you need to set the entity's location before spawn the entity.
     *
     * @param location the location you want to set
     * @throws IllegalStateException if the entity is already spawned
     */
    void setLocationBeforeSpawn(Location3dc location);

    /**
     * Gets the dimension of this entity.
     *
     * @return the dimension of this entity
     */
    Dimension getDimension();

    /**
     * Gets the world of this entity.
     *
     * @return the world of this entity
     */
    default World getWorld() {
        return getLocation().dimension() != null ? getLocation().dimension().getWorld() : null;
    }

    /**
     * Gets the scheduler of this entity which is running on the entity tick.
     *
     * @return the scheduler of this entity
     */
    Scheduler getScheduler();

    /**
     * Get the state of the entity.
     *
     * @return the state of the entity
     */
    EntityState getState();

    /**
     * Check if the entity will be spawned later.
     *
     * @return {@code true} if the entity will be spawned later.
     */
    default boolean willBeSpawnedLater() {
        return getState() == EntityState.SPAWNED_LATER;
    }

    /**
     * Check if the entity will be despawned later.
     *
     * @return {@code true} if the entity will be despawned later.
     */
    default boolean willBeDespawnedLater() {
        return getState() == EntityState.DESPAWNED_LATER;
    }

    /**
     * Check if the entity is dead.
     *
     * @return {@code true} if the entity is dead.
     */
    default boolean isDead() {
        return getState() == EntityState.DEAD;
    }

    /**
     * Check if the entity is spawned.
     *
     * @return {@code true} if the entity is spawned.
     */
    default boolean isSpawned() {
        return getState().isSpawned();
    }

    /**
     * Check if the entity is alive.
     *
     * @return {@code true} if the entity is alive.
     */
    default boolean isAlive() {
        return getState() == EntityState.ALIVE;
    }

    /**
     * Checks if the entity has been despawned.
     *
     * @return {@code true} if the entity's state is despawned, {@code false} otherwise.
     */
    default boolean isDespawned() {
        return getState() == EntityState.DESPAWNED;
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
     * @param location the location to teleport the entity to
     * @return {@code true} if the operation is valid, {@code false} otherwise (event being cancelled).
     */
    default boolean teleport(Location3dc location) {
        return teleport(location, EntityTeleportEvent.Reason.UNKNOWN);
    }

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to
     * @param reason   the reason of the teleport
     * @return {@code true} if the operation is valid, {@code false} otherwise (event being cancelled).
     */
    boolean teleport(Location3dc location, EntityTeleportEvent.Reason reason);

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to
     */
    default boolean teleport(Location3ic location) {
        return teleport(new Location3d(location));
    }

    /**
     * Get the runtime id of this entity.
     *
     * @return the runtime id of this entity
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
     * Get the unique id of this entity. Non-player entities only have the last 8 bytes of the UUID set
     * {@link UUID#getLeastSignificantBits()} while the most 8 bytes {@link UUID#getMostSignificantBits()}
     * will be zero. For actual player, this should be same to {@link LoginData#getUuid()}.
     *
     * @return the unique id of this entity
     */
    UUID getUniqueId();

    /**
     * Get the base aabb of this entity which is not affected by the scale factor ({@link #getScale()}).
     *
     * @return the base aabb of this entity
     */
    AABBdc getBaseAABB();

    /**
     * Gets the aabb of this entity which is affected by the scale factor ({@link #getScale()}).
     *
     * @return the aabb of this entity
     */
    default AABBdc getAABB() {
        var base = getBaseAABB();
        var scale = getScale();
        return new AABBd(
                base.minX() * scale,
                base.minY() * scale,
                base.minZ() * scale,
                base.maxX() * scale,
                base.maxY() * scale,
                base.maxZ() * scale
        );
    }

    /**
     * Get the offset aabb of this entity.
     *
     * @return the offset aabb of this entity
     */
    default AABBdc getOffsetAABB() {
        return getAABB().translate(getLocation(), new AABBd());
    }

    /**
     * Get the offset aabb of this entity for collision check.
     *
     * @return the offset aabb of this entity for collision check
     */
    AABBdc getOffsetAABBForCollisionCheck();

    /**
     * Check if this entity has entity collision. If return {@code true}, method {@link #onCollideWithEntity(Entity)}
     * will be called when this entity collide with another entity.
     *
     * @return {@code true} if the entity has entity collision.
     */
    default boolean hasEntityCollision() {
        return true;
    }

    /**
     * Called when the entity collides with another entity.
     *
     * @param other the entity collides with
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(Entity other) {
    }

    /**
     * Called when the entity is inside a block.
     *
     * @param block the block that this entity inside
     */
    @ApiStatus.OverrideOnly
    default void onInsideBlock(Block block) {
    }

    /**
     * Called when the entity collides with a block.
     *
     * @param block the block that collides with this entity
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithBlock(Block block) {
    }

    /**
     * Called when the entity is hit by a projectile.
     *
     * @param projectile the projectile that hits the entity
     * @param hitPos     the hit pos
     */
    @ApiStatus.OverrideOnly
    default void onProjectileHit(EntityProjectile projectile, Vector3dc hitPos) {
    }

    /**
     * Get the viewers of this entity.
     *
     * @return the viewers of this entity
     */
    @UnmodifiableView
    Set<WorldViewer> getViewers();

    /**
     * Foreach the viewers of this entity.
     *
     * @param consumer the consumer to be called
     */
    default void forEachViewers(Consumer<WorldViewer> consumer) {
        getViewers().forEach(consumer);
    }

    /**
     * Try to set this entity's location. This method is intended to be called by the physics engine, and the
     * user is not expected to call it. The implementation will call {@link EntityMoveEvent}, and because this
     * event is cancellable, the method call may not be valid.
     *
     * @param newLocation the new location that this entity moves to
     * @return {@code true} if the method call is valid, {@code false} otherwise.
     */
    @ApiStatus.OverrideOnly
    boolean trySetLocation(Location3dc newLocation);

    /**
     * Spawn the entity to the specified viewer.
     *
     * @param viewer the viewer to spawn the entity to
     */
    void spawnTo(WorldViewer viewer);

    /**
     * Spawn the entity to a number of specified viewers.
     *
     * @param viewers the viewers to spawn the entity to
     */
    default void spawnTo(Collection<? extends WorldViewer> viewers) {
        viewers.forEach(this::spawnTo);
    }

    /**
     * Despawn the entity from the specified viewer.
     *
     * @param viewer the viewer to despawn the entity from
     */
    void despawnFrom(WorldViewer viewer);

    /**
     * Despawn the entity from a number of specified viewers.
     *
     * @param viewers the viewers to spawn the entity to
     */
    default void despawnFrom(Collection<? extends WorldViewer> viewers) {
        viewers.forEach(this::despawnFrom);
    }

    /**
     * Despawn the entity from all viewers. This method will only remove the entity from all the viewers, and it will
     * still exist in the dimension.
     */
    void despawnFromAll();

    /**
     * @see #remove(Runnable)
     */
    default void remove() {
        remove(() -> {
        });
    }

    /**
     * Remove the entity from the current dimension's {@link EntityManager}. Compared to {@link #despawnFromAll()},
     * this method will also remove the entity from the world, and {@link #willBeDespawnedLater()} will return
     * {@code true} after this method is called.
     *
     * @param callback the callback to be called after the entity is removed from the world. Can be {@code null}.
     */
    void remove(Runnable callback);

    /**
     * Save the entity to NBT.
     *
     * @return the NBT of this entity
     */
    NbtMap saveNBT();

    /**
     * Load the entity from NBT.
     *
     * @param nbt the NBT to load
     */
    void loadNBT(NbtMap nbt);

    /**
     * Called when the entity is splashed by a splash water bottle.
     */
    @ApiStatus.OverrideOnly
    default void onSplash() {
    }


    /**
     * Get the eye height of this entity.
     *
     * @return the eye height of this entity
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
        return loc.dimension().getChunkManager().isChunkLoaded(cx, cz);
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
     * @return the current chunk of the entity, or {@code null} if the chunk is not loaded
     */
    default Chunk getCurrentChunk() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkManager().getChunk(cx, cz);
    }

    /**
     * Get the horizontal face of the entity.
     *
     * @return the horizontal face of the entity
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
     * Apply an action to the entity.
     *
     * @param action the action to apply
     */
    void applyAction(EntityAction action);

    /**
     * Apply an animation to the entity.
     *
     * @param animation the animation to apply
     */
    void applyAnimation(EntityAnimation animation);

    /**
     * Add a tag to the entity.
     *
     * @param tag the tag to add
     * @return {@code true} if the tag is added, otherwise {@code false}.
     */
    boolean addTag(String tag);

    /**
     * Remove a tag from the entity.
     *
     * @param tag the tag to remove
     * @return {@code true} if the tag is removed, otherwise {@code false}.
     */
    boolean removeTag(String tag);

    /**
     * Check if the entity has the specified tag.
     *
     * @param tag the tag to check
     * @return {@code true} if the entity has the specified tag, otherwise {@code false}.
     */
    boolean hasTag(String tag);

    /**
     * Get the tags of the entity.
     *
     * @return the tags of the entity
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
     * Check if the entity is persistent. If the entity is not persistent, it will not be saved
     * through {@link WorldStorage}. If you don't want the entity to be saved, or
     * you want to save the entity by yourself, you can call {@link #setPersistent(boolean)} with
     * {@code false}.
     * <p>
     * When {@code false}, the entity will always be loaded, and {@link EntityManager}
     * will not remove and save the entity even if the entity is in unloaded chunk. The entity can only be removed
     * manually in this case.
     * <p>
     * For example, player data is handled by {@link PlayerStorage}, so this returns
     * {@code false} by default in {@link EntityPlayer}.
     *
     * @return {@code true} if the entity is persistent, otherwise {@code false}.
     */
    default boolean isPersistent() {
        return true;
    }

    /**
     * Set whether the entity is persistent.
     *
     * @param persistent {@code true} if the entity should be persistent
     */
    void setPersistent(boolean persistent);

    @Override
    default boolean isValid() {
        return getState().isSpawned();
    }
}
