package cn.allay.api.entity.component.base;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.world.chunk.Chunk;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
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

    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    Location3fc getLocation();

    @Inject
    @ApiStatus.Internal
    void setLocation(Location3fc location);

    @Inject
    long getUniqueId();

    @Inject
    Metadata getMetadata();

    @Inject
    AABBfc getAABB();

    @Inject
    void setAABB(AABBf aabb);

    default boolean hasCollision() {
        return getMetadata().getFlag(EntityFlag.HAS_COLLISION);
    }

    @Inject
    void setHasCollision(boolean hasCollision);

    @Inject
    @UnmodifiableView
    Map<Long, Client> getViewers();

    @Inject
    Vector3fc getMotion();

    @Inject
    void setMotion(Vector3fc motion);

    default void addMotion(Vector3fc add) {
        setMotion(getMotion().add(add, new Vector3f()));
    }

    @Inject
    boolean isOnGround();

    @Inject
    void setOnGround(boolean onGround);

    @Inject
    void spawnTo(Client client);

    @Inject
    void despawnFrom(Client client);

    @Inject
    void despawnFromAll();

    @Inject
    BedrockPacket createSpawnPacket();

    @Inject
    void sendPacketToViewers(BedrockPacket packet);

    @Inject
    void sendPacketToViewersImmediately(BedrockPacket packet);

    @Inject
    void broadcastMoveToViewers(Set<MoveEntityDeltaPacket.Flag> moveFlags, Location3fc newLoc);

    @Inject
    NbtMap save();

    @Inject
    void load(NbtMap nbt);

    @Inject
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
        return loc.world().getChunkService().isChunkLoaded(cx, cz);
    }

    default boolean isYInRange() {
        var loc = getLocation();
        return loc.world().isYInRange(loc.y());
    }

    default boolean isInWorld() {
        return isYInRange() && isCurrentChunkLoaded();
    }

    @Nullable
    default Chunk getCurrentChunk() {
        var loc = getLocation();
        var cx = (int) loc.x() >> 4;
        var cz = (int) loc.z() >> 4;
        return loc.world().getChunkService().getChunk(cx, cz);
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
