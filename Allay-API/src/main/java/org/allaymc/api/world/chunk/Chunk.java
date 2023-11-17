package org.allaymc.api.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {
    @ApiStatus.Internal
    void tick();

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    @UnmodifiableView
    default Set<EntityPlayer> getPlayerChunkLoaders() {
        return getChunkLoaders().stream().filter(EntityPlayer.class::isInstance).map(EntityPlayer.class::cast).collect(Collectors.toSet());
    }

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();

    void addChunkPacket(BedrockPacket packet);

    void sendChunkPacket(BedrockPacket packet);

    @ApiStatus.Internal
    void addEntity(Entity entity);

    @Nullable
    @ApiStatus.Internal
    Entity removeEntity(long uniqueId);

    void sendChunkPackets();

    void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState expectedValue, BlockState newValue, int layer);

    void compareAndSetBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType expectedValue, BiomeType newValue);

    void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int expectedValue, @Range(from = -512, to = 511) int newValue);

    void batchProcess(UnsafeChunkOperate operate);

    UnsafeChunk toUnsafeChunk();

    @ApiStatus.Internal
    LevelChunkPacket createSubChunkLevelChunkPacket();

    @ApiStatus.Internal
    LevelChunkPacket createFullLevelChunkPacketChunk();

    default void save(WorldStorage storage) {
        storage.writeChunk(this).join();
    }

    default void spawnEntitiesTo(EntityPlayer player) {
        getEntities().values().forEach(player::spawnEntity);
    }

    default void despawnEntitiesFrom(EntityPlayer player) {
        getEntities().values().forEach(player::despawnEntity);
    }
}
