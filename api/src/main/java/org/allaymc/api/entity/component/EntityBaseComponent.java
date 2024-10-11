package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.utils.MathUtils;
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
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent, CommandSender, HasAABB, HasLongId {

    float SPRINTING_MOVEMENT_FACTOR = 1.3f;
    float WALKING_MOVEMENT_FACTOR = 1f;
    float SNEAKING_MOVEMENT_FACTOR = 0.3f;
    float STOP_MOVEMENT_FACTOR = 0f;
    float DEFAULT_PUSH_SPEED_REDUCTION = 1f;
    float DEFAULT_KNOCKBACK = 0.4f;

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
    Location3fc getLocation();

    /**
     * Set the location before the entity is spawned.
     * <p>
     * This method is usually used when you want to spawn the entity at a specific location.
     * Then you need to set the entity's location before spawn the entity.
     *
     * @param location the location you want to set
     *
     * @throws IllegalStateException if the entity is already spawned
     */
    void setLocationBeforeSpawn(Location3fc location);

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
     * Check if the entity will be spawned in the next tick.
     *
     * @return {@code true} if the entity will be spawned in the next tick.
     */
    boolean willBeSpawnedNextTick();

    /**
     * Check if the entity will be despawned in the next tick.
     *
     * @return {@code true} if the entity will be despawned in the next tick.
     */
    boolean willBeDespawnedNextTick();

    /**
     * Check if the entity is dead.
     *
     * @return {@code true} if the entity is dead.
     */
    boolean isDead();

    /**
     * Check if the entity is spawned.
     *
     * @return {@code true} if the entity is spawned.
     */
    boolean isSpawned();

    /**
     * Check if the entity is alive.
     *
     * @return {@code true} if the entity is alive.
     */
    default boolean isAlive() {
        return isSpawned() && !isDead();
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
    void teleport(Location3fc location);

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
     * Send the entity data to the viewers.
     *
     * @param dataTypes the data types to send.
     */
    void sendEntityData(EntityDataType<?>... dataTypes);

    /**
     * Send the entity flags to the viewers.
     *
     * @param flags the flags to send.
     */
    void sendEntityFlags(EntityFlag... flags);

    /**
     * Set and send the entity data to the viewers.
     *
     * @param dataType the data type to set.
     * @param value    the value to set.
     * @param <T>      the type of the value.
     */
    default <T> void setAndSendEntityData(EntityDataType<T> dataType, T value) {
        getMetadata().set(dataType, value);
        sendEntityData(dataType);
    }

    /**
     * Set and send the entity flag to the viewers.
     *
     * @param flag  the flag to set.
     * @param value the value to set.
     */
    default void setAndSendEntityFlag(EntityFlag flag, boolean value) {
        if (value == getMetadata().get(flag)) return;
        getMetadata().set(flag, value);
        sendEntityFlags(flag);
    }

    /**
     * Get the aabb of this entity.
     *
     * @return the aabb of this entity.
     */
    AABBfc getAABB();

    /**
     * Get the offset aabb of this entity.
     *
     * @return the offset aabb of this entity.
     */
    default AABBf getOffsetAABB() {
        return getAABB().translate(getLocation(), new AABBf());
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
        return false;
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
    Vector3fc getMotion();

    /**
     * Set the motion of this entity.
     *
     * @param motion the motion to set.
     */
    void setMotion(Vector3fc motion);

    /**
     * Set the motion of this entity.
     *
     * @param mx the motion x to set.
     * @param my the motion y to set.
     * @param mz the motion z to set.
     */
    default void setMotion(float mx, float my, float mz) {
        setMotion(new Vector3f(mx, my, mz));
    }

    /**
     * Add the motion to this entity.
     *
     * @param add the motion to add.
     */
    default void addMotion(Vector3fc add) {
        setMotion(getMotion().add(add, new Vector3f()));
    }

    /**
     * Add the motion to this entity.
     *
     * @param mx the motion x to add.
     * @param my the motion y to add.
     * @param mz the motion z to add.
     */
    default void addMotion(float mx, float my, float mz) {
        setMotion(getMotion().add(mx, my, mz, new Vector3f()));
    }

    /**
     * Get the last motion of this entity.
     *
     * @return the last motion of this entity.
     */
    Vector3fc getLastMotion();

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
    float getFallDistance();

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
     * Called when the entity ticks.
     *
     * @param currentTick the current tick.
     */
    @ApiStatus.OverrideOnly
    default void tick(long currentTick) {}

    /**
     * Check if the entity has head yaw.
     *
     * @return {@code true} if the entity has head yaw, otherwise {@code false}.
     */
    default boolean enableHeadYaw() {
        return false;
    }

    /**
     * Get the base offset of this entity.
     *
     * @return the base offset of this entity.
     */
    default float getBaseOffset() {
        return 0f;
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
    default float getStepHeight() {
        return 0.6f;
    }

    /**
     * Get the gravity of this entity.
     *
     * @return the gravity of this entity.
     */
    default float getGravity() {
        return 0.08f;
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
    default float getEyeHeight() {
        return (getAABB().maxY() - getAABB().minY()) * 0.9f;
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
    default float getMovementFactor() {
        return STOP_MOVEMENT_FACTOR;
    }

    /**
     * Get the push speed reduction of this entity.
     *
     * @return the push speed reduction of this entity.
     */
    default float getPushSpeedReduction() {
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
     * Knockback the entity.
     *
     * @param source the source of the knockback.
     */
    default void knockback(Vector3fc source) {
        knockback(source, DEFAULT_KNOCKBACK);
    }

    /**
     * Knockback the entity.
     *
     * @param source the source of the knockback.
     * @param kb     the knockback strength to apply.
     */
    default void knockback(Vector3fc source, float kb) {
        knockback(source, kb, false);
    }

    /**
     * Knockback the entity.
     *
     * @param source                    the source of the knockback.
     * @param kb                        the knockback strength to apply.
     * @param ignoreKnockbackResistance {@code true} if the knockback resistance should be ignored.
     */
    void knockback(Vector3fc source, float kb, boolean ignoreKnockbackResistance);

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
    default void applyAction(AnimatePacket.Action action, float rowingTime) {
        var pk = new AnimatePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setAction(action);
        pk.setRowingTime(rowingTime);
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
     * @return {@code true} if the entity is in water, otherwise {@code false}.
     */
    default boolean isEyesInWater() {
        var dim = getDimension();
        var eyeLoc = getLocation().add(0, getEyeHeight(), 0, new Vector3f());
        var eyesBlockState = dim.getBlockState(eyeLoc);

        return eyesBlockState.getBlockType().hasBlockTag(BlockTags.WATER) &&
               eyesBlockState.getBlockStateData().computeOffsetShape(MathUtils.floor(eyeLoc)).intersectsPoint(eyeLoc);
    }

    /**
     * Check if the entity can breathe.
     *
     * @return {@code true} if the entity can breathe, otherwise {@code false}.
     */
    default boolean canBreathe() {
        return hasEffect(EffectTypes.WATER_BREATHING) || hasEffect(EffectTypes.CONDUIT_POWER) || isEyesInWater();
    }

    /**
     * Check if the specific effect can apply on the entity.
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
     *
     * @return {@code true} if the interaction is successful
     */
    default boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return false;
    }

    /**
     * Get the block state which the entity is standing on.
     *
     * @return the block state which the entity is standing on.
     */
    default BlockState getBlockStateStandingOn() {
        var air = BlockTypes.AIR.getDefaultState();
        if (!isOnGround()) return air;

        var loc = getLocation();
        var currentBlockState = getDimension().getBlockState(loc.x(), loc.y(), loc.z());
        if (currentBlockState != air) return currentBlockState;
        else return getDimension().getBlockState(loc.x(), loc.y() - 1, loc.z());
    }
}
