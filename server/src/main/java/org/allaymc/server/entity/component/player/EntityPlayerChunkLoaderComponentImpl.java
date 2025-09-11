package org.allaymc.server.entity.component.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerChunkLoaderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CPlayerChunkInRangeSendEvent;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.ChunkRadiusUpdatedPacket;
import org.cloudburstmc.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerChunkLoaderComponentImpl implements EntityPlayerChunkLoaderComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_world_viewer_component");

    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Manager
    protected ComponentManager manager;
    @Dependency
    protected EntityBaseComponent baseComponent;
    @Dependency
    protected EntityPlayerNetworkComponentImpl networkComponent;

    @Getter
    protected int chunkLoadingRadius;
    @Getter
    @Setter
    protected int chunkMaxSendCountPerTick;

    public EntityPlayerChunkLoaderComponentImpl() {
        this.chunkLoadingRadius = Server.SETTINGS.worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = Server.SETTINGS.worldSettings().chunkMaxSendCountPerTick();
    }

    @Override
    public Location3dc getLocation() {
        // NOTICE: Don't call thisPlayer.getLocation() here which will cause stack over flow error
        return this.baseComponent.getLocation();
    }

    @Override
    public boolean isLoaderActive() {
        return thisPlayer.isSpawned();
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        this.chunkLoadingRadius = Math.min(radius, Server.SETTINGS.worldSettings().viewDistance());
        var packet = new ChunkRadiusUpdatedPacket();
        packet.setRadius(chunkLoadingRadius);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void onChunkPosChanged() {
        var location = thisPlayer.getLocation();
        var packet = new NetworkChunkPublisherUpdatePacket();
        packet.setPosition(org.cloudburstmc.math.vector.Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void onChunkInRangeSend(Chunk chunk) {
        // This method will be called in non-ticking thread if async chunk sending is enabled. Let's
        // send the entities in this chunk to the player next tick in the main thread: use forEachEntitiesInChunk()
        // instead of forEachEntitiesInChunkImmediately()
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(thisPlayer));
        manager.callEvent(CPlayerChunkInRangeSendEvent.INSTANCE);
    }

    @Override
    public void onChunkOutOfRange(long chunkHash) {
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash),
                entity -> entity.despawnFrom(thisPlayer)
        );
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        // TODO: remove
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        // TODO: remove
        this.networkComponent.sendPacketImmediately(packet);
    }


}
