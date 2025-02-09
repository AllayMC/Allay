package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
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

import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent, CommandSender, HasAABB, HasLongId, PersistentDataHolder {
    String TAG_IDENTIFIER = "identifier";
    String TAG_ON_GROUND = "OnGround";
    String TAG_POS = "Pos";
    String TAG_MOTION = "Motion";
    String TAG_ROTATION = "Rotation";
    String TAG_TAGS = "Tags";
    String TAG_ACTIVE_EFFECTS = "ActiveEffects";
    String TAG_UNIQUE_ID = "UniqueID";
    String TAG_PDC = "PDC";

    double SPRINTING_MOVEMENT_FACTOR = 1.3;
    double WALKING_MOVEMENT_FACTOR = 1;
    double SNEAKING_MOVEMENT_FACTOR = 0.3;
    double STOP_MOVEMENT_FACTOR = 0;
    double DEFAULT_PUSH_SPEED_REDUCTION = 1;
    double DEFAULT_KNOCKBACK = 0.4;

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
     *
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
     */
    default void teleport(Location3dc location) {
        teleport(location, EntityTeleportEvent.Reason.UNKNOWN);
    }

    /**
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to.
     * @param reason   the reason of the teleport.
     */
    void teleport(Location3dc location, EntityTeleportEvent.Reason reason);

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
     * Teleport the entity to the specified location.
     *
     * @param location the location to teleport the entity to.
     */
    default void teleport(Location3ic location) {
        teleport(new Location3d(location));
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
     * Check if the entity has collision.
     *
     * @return {@code true} if the entity has collision.
     */
    default boolean hasEntityCollision() {
        return getMetadata().get(EntityFlag.HAS_COLLISION);
    }

    /**
     * Set if the entity has collision.
     *
     * @param hasEntityCollision {@code true} if the entity has collision.
     */
    default void setHasEntityCollision(boolean hasEntityCollision) {
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, hasEntityCollision);
    }

    /**
     * Check if the entity has entity collision motion.
     *
     * @return {@code true} if the entity has entity collision motion.
     */
    default boolean computeEntityCollisionMotion() {
        return hasEntityCollision();
    }

    /**
     * Check if the entity has block collision motion.
     *
     * @return {@code true} if the entity has block collision motion.
     */
    default boolean computeBlockCollisionMotion() {
        return true;
    }

    /**
     * Check if the entity has liquid motion.
     *
     * @return {@code true} if the entity has liquid motion.
     */
    default boolean computeLiquidMotion() {
        return true;
    }

    /**
     * Called when the entity collides with another entity.
     *
     * @param other the entity collides with.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWith(Entity other) {}

    /**
     * Get the viewers of this entity.
     *
     * @return the viewers of this entity.
     */
    @UnmodifiableView
    Map<Long, EntityPlayer> getViewers();

    /**
     * Get the motion of this entity.
     *
     * @return the motion of this entity.
     */
    Vector3dc getMotion();

    /**
     * Set the motion of this entity.
     *
     * @param motion the motion to set.
     */
    void setMotion(Vector3dc motion);

    /**
     * Set the motion of this entity.
     *
     * @param mx the motion x to set.
     * @param my the motion y to set.
     * @param mz the motion z to set.
     */
    default void setMotion(double mx, double my, double mz) {
        setMotion(new Vector3d(mx, my, mz));
    }

    /**
     * Add the motion to this entity.
     *
     * @param add the motion to add.
     */
    default void addMotion(Vector3dc add) {
        setMotion(getMotion().add(add, new Vector3d()));
    }

    /**
     * Add the motion to this entity.
     *
     * @param mx the motion x to add.
     * @param my the motion y to add.
     * @param mz the motion z to add.
     */
    default void addMotion(double mx, double my, double mz) {
        setMotion(getMotion().add(mx, my, mz, new Vector3d()));
    }

    /**
     * Get the last motion of this entity.
     *
     * @return the last motion of this entity.
     */
    Vector3dc getLastMotion();

    /**
     * Check if the entity is on the ground.
     *
     * @return {@code true} if the entity is on the ground, otherwise {@code false}.
     */
    boolean isOnGround();

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
    default void spawnTo(Set<EntityPlayer> players) {
        players.forEach(this::spawnTo);
    }

    /**
     * Despawn the entity from the specified player.
     *
     * @param player the player to despawn the entity from.
     */
    void despawnFrom(EntityPlayer player);

    /**
     * Despawn the entity from all viewers.
     * <p>
     * This method will only remove the entity from the viewers, but the entity will still exist in the world.
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
     * Get the fall distance of this entity.
     *
     * @return the fall distance of this entity.
     */
    double getFallDistance();

    /**
     * Called when the entity falls.
     */
    @ApiStatus.OverrideOnly
    void onFall();

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
     *
     * @return {@code true} if the entity has the specified effect, otherwise {@code false}.
     */
    boolean hasEffect(EffectType effectType);

    /**
     * Get the effect level of the specified effect.
     *
     * @param effectType the effect type to get.
     *
     * @return the effect level of the specified effect.
     */
    int getEffectLevel(EffectType effectType);

    /**
     * Add the specified effect to the entity.
     *
     * @param effectInstance the effect instance to add.
     *
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
     * Check whether the entity's movement should be computed server-side.
     *
     * @return {@code true} if the entity's movement should be computed server-side, otherwise {@code false}.
     */
    default boolean computeMovementServerSide() {
        return true;
    }

    /**
     * Get the step height of this entity.
     *
     * @return the step height of this entity.
     */
    default double getStepHeight() {
        return 0.6;
    }

    /**
     * Get the gravity of this entity.
     *
     * @return the gravity of this entity.
     */
    default double getGravity() {
        return 0.08;
    }

    /**
     * Get the drag factor when on ground of this entity.
     * <p>
     * This factor will be multiplied to the motion along x and z axis every tick.
     * The bigger the factor is, the quicker the entity will stop in x-axis and z-axis.
     *
     * @return the drag factor when on ground of this entity.
     */
    default double getDragFactorOnGround() {
        return 0.1;
    }

    /**
     * Get the drag factor when in air of this entity.
     * <p>
     * This value is similar to {@link #getDragFactorOnGround}, however this value
     * will be used to reduce motion along x-axis and z-axis when the entity is not on ground.
     * This value will always be used for motion along y-axis.
     *
     * @return the drag factor when in air of this entity.
     */
    default double getDragFactorInAir() {
        return 0.02;
    }

    /**
     * Check if the entity has gravity.
     *
     * @return {@code true} if the entity has gravity, otherwise {@code false}.
     */
    default boolean hasGravity() {
        return getMetadata().get(EntityFlag.HAS_GRAVITY);
    }

    /**
     * Set if the entity has gravity.
     *
     * @param hasGravity {@code true} if the entity has gravity, otherwise {@code false}.
     */
    default void setHasGravity(boolean hasGravity) {
        setAndSendEntityFlag(EntityFlag.HAS_GRAVITY, hasGravity);
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
     * Get the movement factor of this entity.
     * <p>
     * Given yaw, if the movement multiplier is not 0, the entity will move towards the direction specified by yaw.
     *
     * @return the movement factor of this entity.
     *
     * @see <a href="https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas">Horizontal Movement Formulas</a>
     */
    default double getMovementFactor() {
        return STOP_MOVEMENT_FACTOR;
    }

    /**
     * Get the push speed reduction of this entity.
     *
     * @return the push speed reduction of this entity.
     */
    default double getPushSpeedReduction() {
        return DEFAULT_PUSH_SPEED_REDUCTION;
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
     * @return the current chunk of the entity.
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
     * @see #knockback(Vector3dc, double, boolean, double)
     */
    default void knockback(Vector3dc source) {
        knockback(source, DEFAULT_KNOCKBACK);
    }

    /**
     * @see #knockback(Vector3dc, double, boolean, double)
     */
    default void knockback(Vector3dc source, double kb) {
        knockback(source, kb, false);
    }

    /**
     * @see #knockback(Vector3dc, double, boolean, double)
     */
    default void knockback(Vector3dc source, double kb, boolean ignoreKnockbackResistance) {
        knockback(source, kb, ignoreKnockbackResistance, kb);
    }

    /**
     * Knockback the entity with specified kb value.
     *
     * @param source                    the source of the knockback.
     * @param kb                        the knockback strength to apply.
     * @param ignoreKnockbackResistance {@code true} if the knockback resistance should be ignored.
     * @param kby                       the knockback strength in y-axis.
     */
    void knockback(Vector3dc source, double kb, boolean ignoreKnockbackResistance, double kby);

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
     * Check if the entity can critical attack.
     *
     * @return {@code true} if the entity can critical attack, otherwise {@code false}.
     */
    default boolean canCriticalAttack() {
        return !isOnGround() && getMotion().y() < 0 && !hasEffect(EffectTypes.BLINDNESS) && !hasEffect(EffectTypes.SLOW_FALLING);
    }

    /**
     * Add a tag to the entity.
     *
     * @param tag the tag to add.
     *
     * @return {@code true} if the tag is added, otherwise {@code false}.
     */
    boolean addTag(String tag);

    /**
     * Remove a tag from the entity.
     *
     * @param tag the tag to remove.
     *
     * @return {@code true} if the tag is removed, otherwise {@code false}.
     */
    boolean removeTag(String tag);

    /**
     * Check if the entity has the specified tag.
     *
     * @param tag the tag to check.
     *
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
     *
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
     *
     * @return {@code true} if the interaction is successful
     */
    default boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return false;
    }

    /**
     * Get the block which the entity is standing on.
     *
     * @return the block which the entity is standing on, or air if the entity is not standing on any block (and the pos will be {@code null}).
     */
    default BlockStateWithPos getBlockStateStandingOn() {
        var loc = getLocation();
        var air = BlockTypes.AIR.getDefaultState();
        if (!isOnGround()) {
            return new BlockStateWithPos(air, null);
        }

        var currentBlockState = getDimension().getBlockState(loc.x(), loc.y(), loc.z());
        if (currentBlockState != air) {
            return new BlockStateWithPos(
                    currentBlockState,
                    new Position3i((int) Math.floor(loc.x()), (int) Math.floor(loc.y()), (int) Math.floor(loc.z()), getDimension())
            );
        } else {
            return new BlockStateWithPos(
                    getDimension().getBlockState(loc.x(), loc.y() - 1, loc.z()),
                    new Position3i((int) Math.floor(loc.x()), (int) Math.floor(loc.y() - 1), (int) Math.floor(loc.z()), getDimension())
            );
        }
    }

    /**
     * @see #canStandSafely(int, int, int, Dimension)
     */
    default boolean canStandSafely(Position3ic pos) {
        return canStandSafely(pos.x(), pos.y(), pos.z(), getDimension());
    }

    /**
     * Check if the specified position is a safe standing position.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     *
     * @return {@code true} if the specified position is a safe standing position, otherwise {@code false}.
     */
    default boolean canStandSafely(int x, int y, int z, Dimension dimension) {
        var blockUnder = dimension.getBlockState(x, y - 1, z);
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        var aabb = getAABB().translate(x + 0.5, y + 0.5, z + 0.5, new AABBd());
        return dimension.getCollidingBlockStates(aabb) == null;
    }
}
