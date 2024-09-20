package org.allaymc.api.entity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
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
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.chunk.Chunk;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
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
public interface EntityBaseComponent extends EntityComponent, CommandSender {

    float SPRINTING_MOVEMENT_FACTOR = 1.3f;
    float WALKING_MOVEMENT_FACTOR = 1f;
    float SNEAKING_MOVEMENT_FACTOR = 0.3f;
    float STOP_MOVEMENT_FACTOR = 0f;
    float DEFAULT_PUSH_SPEED_REDUCTION = 1f;
    float DEFAULT_KNOCKBACK = 0.4f;

    private static boolean isWaterType(BlockType<?> blockType) {
        return blockType == BlockTypes.FLOWING_WATER || blockType == BlockTypes.WATER;
    }

    EntityType<? extends Entity> getEntityType();

    String getDisplayName();

    void setDisplayName(String displayName);

    default String getNameTag() {
        return getMetadata().get(EntityDataTypes.NAME);
    }

    default void setNameTag(String nameTag) {
        setAndSendEntityData(EntityDataTypes.NAME, nameTag);
    }

    default void clearNameTag() {
        setAndSendEntityData(EntityDataTypes.NAME, "");
    }

    Location3fc getLocation();

    /**
     * Set the location before the entity is spawned.
     * <p>
     * This method is usually used when you want to spawn the entity at a specific location.
     * <p>
     * Then you need to set the entity's location before spawn the entity.
     *
     * @param location The location you want to set
     *
     * @throws IllegalStateException if the entity is already spawned
     */
    void setLocationBeforeSpawn(Location3fc location);

    Dimension getDimension();

    World getWorld();

    boolean willBeSpawnedNextTick();

    boolean willBeDespawnedNextTick();

    boolean isDead();

    boolean isSpawned();

    default boolean isAlive() {
        return isSpawned() && !isDead();
    }

    boolean canBeSpawned();

    void teleport(Location3fc location);

    long getRuntimeId();

    long getUniqueId();

    Metadata getMetadata();

    void sendEntityData(EntityDataType<?>... dataTypes);

    void sendEntityFlags(EntityFlag... flags);

    default <T> void setAndSendEntityData(EntityDataType<T> dataType, T value) {
        getMetadata().set(dataType, value);
        sendEntityData(dataType);
    }

    default void setAndSendEntityFlag(EntityFlag flag, boolean value) {
        if (value == getMetadata().get(flag)) return;
        getMetadata().set(flag, value);
        sendEntityFlags(flag);
    }

    default void addAndSendEntityFlags(EntityFlag... flags) {
        for (EntityFlag flag : flags) {
            getMetadata().set(flag, true);
        }
        sendEntityFlags(flags);
    }

    default void removeAndSendEntityFlags(EntityFlag... flags) {
        for (EntityFlag flag : flags) {
            getMetadata().set(flag, false);
        }
        sendEntityFlags(flags);
    }

    AABBfc getAABB();

    default boolean hasEntityCollision() {
        return getMetadata().get(EntityFlag.HAS_COLLISION);
    }

    default void setHasEntityCollision(boolean hasEntityCollision) {
        setAndSendEntityFlag(EntityFlag.HAS_COLLISION, hasEntityCollision);
    }

    default boolean computeEntityCollisionMotion() {
        return hasEntityCollision();
    }

    default boolean computeBlockCollisionMotion() {
        return false;
    }

    default void onCollideWith(Entity other) {}

    @UnmodifiableView
    Map<Long, EntityPlayer> getViewers();

    Vector3fc getMotion();

    void setMotion(Vector3fc motion);

    Vector3fc getLastMotion();

    default void setMotion(float mx, float my, float mz) {
        setMotion(new Vector3f(mx, my, mz));
    }

    default void addMotion(Vector3fc add) {
        setMotion(getMotion().add(add, new Vector3f()));
    }

    default void addMotion(float mx, float my, float mz) {
        setMotion(getMotion().add(mx, my, mz, new Vector3f()));
    }

    boolean isOnGround();

    void setOnGround(boolean onGround);

    void spawnTo(EntityPlayer player);

    default void spawnTo(Set<EntityPlayer> players) {
        players.forEach(this::spawnTo);
    }

    void despawnFrom(EntityPlayer player);

    void despawnFromAll();

    void despawn();

    BedrockPacket createSpawnPacket();

    void sendPacketToViewers(BedrockPacket packet);

    void sendPacketToViewersImmediately(BedrockPacket packet);

    void broadcastMoveToViewers(Location3fc newLoc);

    void broadcastMoveToViewers(Location3fc newLoc, boolean teleporting);

    NbtMap saveNBT();

    default NbtMap saveNBTWithoutPos() {
        var builder = saveNBT().toBuilder();
        builder.remove("Pos");
        return builder.build();
    }

    void loadNBT(NbtMap nbt);

    float getFallDistance();

    void onFall();

    boolean hasEffect(EffectType effectType);

    int getEffectLevel(EffectType effectType);

    void addEffect(EffectInstance effectInstance);

    void removeEffect(EffectType effectType);

    void removeAllEffects();

    default void tick(long currentTick) {}

    default boolean enableHeadYaw() {
        return false;
    }

    default float getBaseOffset() {
        return 0f;
    }

    default AABBf getOffsetAABB() {
        return getAABB().translate(getLocation(), new AABBf());
    }

    default boolean computeMovementServerSide() {
        return true;
    }

    default float getStepHeight() {
        return 0.6f;
    }

    default float getGravity() {
        return 0.08f;
    }

    default float getEyeHeight() {
        return (getAABB().maxY() - getAABB().minY()) * 0.9f;
    }

    default boolean hasGravity() {
        return getMetadata().get(EntityFlag.HAS_GRAVITY);
    }

    void setHasGravity(boolean hasGravity);

    /**
     * Given yaw, if the movement multiplier is not 0, the entity will move towards the direction specified by yaw. <p>
     *
     * @see <a href="https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas">Horizontal Movement Formulas</a>
     */
    default float getMovementFactor() {
        return STOP_MOVEMENT_FACTOR;
    }

    default float getPushSpeedReduction() {
        return DEFAULT_PUSH_SPEED_REDUCTION;
    }

    default boolean isCurrentChunkLoaded() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkService().isChunkLoaded(cx, cz);
    }

    default boolean isYInRange() {
        var loc = getLocation();
        return loc.dimension().isYInRange(loc.y());
    }

    default boolean isInWorld() {
        return isYInRange() && isCurrentChunkLoaded();
    }

    default Chunk getCurrentChunk() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.dimension().getChunkService().getChunk(cx, cz);
    }

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

    default void knockback(Vector3fc source) {
        knockback(source, DEFAULT_KNOCKBACK);
    }

    default void knockback(Vector3fc source, float kb) {
        knockback(source, kb, false);
    }

    void knockback(Vector3fc source, float kb, boolean ignoreKnockbackResistance);

    default void applyEntityEvent(EntityEventType event, int data) {
        var pk = new EntityEventPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setType(event);
        pk.setData(data);
        sendPacketToViewers(pk);
    }

    default void applyAnimation(AnimatePacket.Action action) {
        applyAnimation(action, 0);
    }

    default void applyAnimation(AnimatePacket.Action action, float rowingTime) {
        var pk = new AnimatePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setAction(action);
        pk.setRowingTime(rowingTime);
        sendPacketToViewers(pk);
    }

    default boolean canCriticalAttack() {
        return !isOnGround() && getMotion().y() < 0 && !hasEffect(EffectTypes.BLINDNESS) && !hasEffect(EffectTypes.SLOW_FALLING);
    }

    boolean addTag(String tag);

    boolean removeTag(String tag);

    boolean hasTag(String tag);

    @UnmodifiableView
    Set<String> getTags();

    default boolean isInWater() {
        var loc = getLocation();
        var blockType = getDimension().getBlockState(loc).getBlockType();
        if (isWaterType(blockType)) return true;

        blockType = getDimension().getBlockState(loc, 1).getBlockType();
        return isWaterType(blockType);
    }

    /**
     * @param player    The player who interacted with the entity, can be null
     * @param itemStack The item used to interact with the entity
     *
     * @return {@code true} if the interaction is successful
     */
    default boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        return false;
    }

    BlockState getBlockStateStandingOn();
}
