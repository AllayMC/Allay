package cn.allay.api.world.chunk;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockPos;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Set;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public interface UnsafeChunk extends SectionOperate, HeightOperate, LightOperate {
    DimensionInfo getDimensionInfo();

    World getWorld();

    int getChunkX();

    void setChunkX(int chunkX);

    int getChunkZ();

    void setChunkZ(int chunkZ);

    @ApiStatus.Internal
    @Nullable
    ChunkSection getSection(int y);

    @ApiStatus.Internal
    @NotNull
    ChunkSection createAndGetSection(int y);

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();

    void addChunkPacket(BedrockPacket packet);

    void sendChunkPacket(BedrockPacket packet);

    void sendChunkPackets();

    default long computeChunkHash() {
        return HashUtils.hashXZ(getChunkX(), getChunkZ());
    }

    default void neighborUpdateAroundIgnoreFace(int x, int y, int z, BlockFace... ignoreFaces) {
        for (var face : BlockFace.values()) {
            if (ignoreFaces != null && ignoreFaces.length > 0) {
                var ignore = false;
                for (var ignoreFace : ignoreFaces) {
                    if (ignoreFace == face) {
                        ignore = true;
                        break;
                    }
                }
                if (ignore) {
                    continue;
                }
            }
            neighborUpdateAtFace(x, y, z, face);
        }
    }

    default void neighborUpdateAround(int x, int y, int z) {
        for (var face : BlockFace.values()) {
            neighborUpdateAtFace(x, y, z, face);
        }
    }

    default void neighborUpdateAtFace(int x, int y, int z, BlockFace face) {
        var offsetPos = face.offsetPos(x, y, z);
        var blockState = getBlockState(x, y, z, false);
        blockState.getBehavior().onNeighborChanged(
                new BlockStateWithPos(
                        blockState,
                        new BlockPos(getWorld(), offsetPos.x(), offsetPos.y(), offsetPos.z(), false)
                ),
                new BlockStateWithPos(
                        blockState,
                        new BlockPos(getWorld(), x, y, z, false)
                ),
                face.opposite()
        );
    }
}
