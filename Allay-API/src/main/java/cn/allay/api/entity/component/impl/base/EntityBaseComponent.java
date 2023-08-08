package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.Location3dc;
import cn.allay.api.client.Client;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent {
    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    Location3dc getLocation();

    @Inject
    void setLocation(Location3dc location);

    @Inject
    long getUniqueId();

    @Inject
    Metadata getMetadata();

    @Inject
    AABBdc getAABB();

    @Inject
    void setAABB(AABBd aabb);

    @Inject
    boolean hasCollision();

    @Inject
    void setHasCollision(boolean hasCollision);

    @Inject
    @UnmodifiableView
    Map<Long, Client> getViewers();

    @Inject
    Vector3dc getMotion();

    @Inject
    void setMotion(Vector3dc motion);

    default void addMotion(Vector3dc add) {
        setMotion(getMotion().add(add, new Vector3d()));
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
    void broadcastMoveToViewers(Set<MoveEntityDeltaPacket.Flag> moveFlags, Location3dc newLoc);

    default boolean enableHeadYaw() {
        return false;
    }

    default double getBaseOffset() {
        return 0;
    }

    default AABBdc getOffsetAABB() {
        return new AABBd(getAABB()).translate(getLocation());
    }

    default boolean computeMovementServerSide() {
        return true;
    }

    default double getStepHeight() {
        return 0.6;
    }

    default double getGravity() {
        return 0.08;
    }

    double SPRINTING_MOVEMENT_FACTOR = 1.3;
    double WALKING_MOVEMENT_FACTOR = 1;
    double SNEAKING_MOVEMENT_FACTOR = 0.3;
    double STOP_MOVEMENT_FACTOR = 0;

    /**
     * 给定yaw，若移动乘数不为0实体将往yaw指定的方向前进 <p>
     * 参见：<a href="https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas/zh">...</a>
     */
    default double getMovementFactor() {
        return STOP_MOVEMENT_FACTOR;
    }

    double DEFAULT_PUSH_SPEED_REDUCTION = 1;

    default double getPushSpeedReduction() {
        return DEFAULT_PUSH_SPEED_REDUCTION;
    }
}
