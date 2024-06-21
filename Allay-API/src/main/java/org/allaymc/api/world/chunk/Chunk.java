package org.allaymc.api.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;
import java.util.function.Predicate;
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

    boolean isLoaded();

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    @UnmodifiableView
    default Set<EntityPlayer> getPlayerChunkLoaders() {
        return getChunkLoaders().stream().filter(EntityPlayer.class::isInstance).map(EntityPlayer.class::cast).collect(Collectors.toSet());
    }

    /**
     * Set the callback to be called when the chunk is loaded into the world
     * @param callback the callback
     */
    @ApiStatus.Internal
    void setChunkSetCallback(Runnable callback);

    /**
     * Called when the chunk is loaded into the world
     */
    @ApiStatus.Internal
    void onChunkSet();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();

    void addChunkPacket(BedrockPacket packet);

    void addChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate);

    void sendChunkPacket(BedrockPacket packet);

    void sendChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate);

    void sendChunkPackets();

    void compareAndSetBlock(int x, int y, int z, BlockState expectedValue, BlockState newValue, int layer);

    void compareAndSetBiome(int x, int y, int z, BiomeType expectedValue, BiomeType newValue);

    void compareAndSetBlockLight(int x, int y, int z, int expectedValue, int newValue);

    void compareAndSetSkyLight(int x, int y, int z, int expectedValue, int newValue);

    void compareAndSetHeight(int x, int z, int expectedValue, int newValue);

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
