package org.allaymc.api.world.chunk;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Chunk loader that only loads chunks. Useful for loading a range of chunks in some places like spawn point.
 *
 * @author daoge_cmd
 */
public record FakeChunkLoader(Supplier<Location3dc> locationSupplier, int radius) implements ChunkLoader {

    @Override
    public Location3dc getLocation() {
        return locationSupplier.get();
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
        return Integer.MAX_VALUE;
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
    public void viewEntityState(Entity entity) {

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
    public void viewEntityAnimation(Entity entity, EntityAnimation animation) {
    }

    @Override
    public void viewEntityAction(Entity entity, EntityAction action) {
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
    public void viewBlockAction(Vector3ic pos, BlockAction action) {
    }

    @Override
    public void viewSound(Sound sound, Vector3dc pos, boolean relative) {
    }

    @Override
    public void viewParticle(Particle particle, Vector3dc pos) {
    }

    @Override
    public void viewWeather(Weather weather) {
    }

    @Override
    public void viewDebugShape(DebugShape debugShape) {
    }

    @Override
    public void viewDebugShapes(Set<DebugShape> debugShapes) {
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
    }

    @Override
    public void removeDebugShapes(Set<DebugShape> debugShapes) {
    }

    @Override
    public void viewBlockEntity(BlockEntity blockEntity) {
    }
}
