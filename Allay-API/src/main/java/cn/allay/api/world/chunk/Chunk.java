package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.client.Client;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

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
    default Set<Client> getClientChunkLoaders() {
        return getChunkLoaders().stream().filter(loader -> loader instanceof Client).map(loader -> (Client) loader).collect(Collectors.toSet());
    }

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();

    void addChunkPacket(BedrockPacket packet);

    void sendChunkPacket(BedrockPacket packet);

    void sendChunkPackets();

    void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState expectedValue, BlockState newValue, int layer);

    void compareAndSetBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType expectedValue, BiomeType newValue);

    void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int expectedValue, @Range(from = -512, to = 511) int newValue);

    void batchProcess(UnsafeChunkOperate operate);

    UnsafeChunk toUnsafeChunk();

    @ApiStatus.Internal
    LevelChunkPacket createLevelChunkPacket();

    default void save(WorldStorage storage) {
        storage.writeChunk(this).join();
    }
}
