package org.allaymc.api.entity.component.base;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.world.chunk.Chunk;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent extends EntityComponent {
    float SPRINTING_MOVEMENT_FACTOR = 1.3f;
    float WALKING_MOVEMENT_FACTOR = 1f;
    float SNEAKING_MOVEMENT_FACTOR = 0.3f;
    float STOP_MOVEMENT_FACTOR = 0f;
    float DEFAULT_PUSH_SPEED_REDUCTION = 1f;

    EntityType<? extends Entity> getEntityType();

    Location3fc getLocation();

    boolean willBeAddedNextTick();

    @ApiStatus.Internal
    void setWillBeAddedNextTick(boolean willBeAddedNextTick);

    boolean willBeRemovedNextTick();

    @ApiStatus.Internal
    void setWillBeRemovedNextTick(boolean willBeRemovedNextTick);

    boolean isSpawned();

    @ApiStatus.Internal
    void setSpawned(boolean spawned);

    @ApiStatus.Internal
    void setLocation(Location3fc location);

    long getUniqueId();

    Metadata getMetadata();

    void sendEntityData(EntityDataType<?>... dataTypes);

    void sendEntityFlags(EntityFlag... flags);

    AABBfc getAABB();

    void setAABB(AABBf aabb);

    default boolean hasEntityCollision() {
        return getMetadata().getFlag(EntityFlag.HAS_COLLISION);
    }

    void setHasEntityCollision(boolean hasEntityCollision);

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

    void removeEntity();

    BedrockPacket createSpawnPacket();

    void sendPacketToViewers(BedrockPacket packet);

    void sendPacketToViewersImmediately(BedrockPacket packet);

    void broadcastMoveToViewers(Location3fc newLoc);

    NbtMap saveNBT();

    void loadNBT(NbtMap nbt);

    float getFallDistance();

    void onFall();

    void addEffect(EffectInstance effectInstance);

    void removeEffect(EffectType effectType);

    default void tick() {
    }

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
        return getMetadata().getFlag(EntityFlag.HAS_GRAVITY);
    }

    void setHasGravity(boolean hasGravity);

    /**
     * 给定yaw，若移动乘数不为0实体将往yaw指定的方向前进 <p>
     * 参见：<a href="https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas/zh">...</a>
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
        double rotation = getLocation().yaw() % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }

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
}
