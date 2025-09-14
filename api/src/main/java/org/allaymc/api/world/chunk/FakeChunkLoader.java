package org.allaymc.api.world.chunk;

import io.netty.util.AbstractReferenceCounted;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.AnimateAction;
import org.allaymc.api.entity.data.EntityEvent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.sound.Sound;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Chunk loader that only loads chunks and do not handle data packets. Useful
 * for loading a range of chunks in some places like spawn point.
 *
 * @author daoge_cmd
 */
public record FakeChunkLoader(Supplier<Location3dc> locationSupplier, int radius) implements ChunkLoader {

    @Override
    public Location3dc getLocation() {
        return locationSupplier.get();
    }

    @Override
    public boolean isLoaderActive() {
        return true;
    }

    @Override
    public int getChunkLoadingRadius() {
        return radius;
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
    }

    @Override
    public int getChunkMaxSendCountPerTick() {
        return Server.SETTINGS.worldSettings().chunkMaxSendCountPerTick();
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        // TODO: remove it
        if (packet instanceof AbstractReferenceCounted lcp) {
            lcp.release();
        }
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        // TODO: remove it
        if (packet instanceof AbstractReferenceCounted lcp) {
            lcp.release();
        }
    }

    @Override
    public void onLoaderChunkPosChange() {
    }

    @Override
    public void viewEntity(Entity entity) {
    }

    @Override
    public void removeEntity(Entity entity) {
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
    }

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
    }

    @Override
    public void viewEntityMetadata(Entity entity) {
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
    }

    @Override
    public void viewEntityEvent(Entity entity, EntityEvent event, int data) {
    }

    @Override
    public void viewEntityAction(Entity entity, AnimateAction action, double rowingTime) {
    }

    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
    }

    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId) {
    }

    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
    }

    @Override
    public void viewChunk(Chunk chunk) {
    }

    @Override
    public void removeChunk(long chunkHash) {
    }

    @Override
    public void viewTime(int timeOfDay) {
    }

    @Override
    public void viewGameRules(GameRules gameRules) {
    }

    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
    }

    @Override
    public void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates) {
    }

    @Override
    public void viewSound(Sound sound, Vector3dc pos, boolean relative) {
    }

    @Override
    public void viewWeather(Weather weather) {
    }
}
