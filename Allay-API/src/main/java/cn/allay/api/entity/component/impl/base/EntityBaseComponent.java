package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.Location3dc;
import cn.allay.api.player.Client;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;
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
    Vector3dc getSpeed();

    @Inject
    void setSpeed(Vector3dc speed);

    @Inject
    Vector3dc getMotion();

    @Inject
    void setMotion(Vector3dc motion);

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
}
