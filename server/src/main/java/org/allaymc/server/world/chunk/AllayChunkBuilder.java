package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.server.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import org.allaymc.server.world.HeightMap;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("UnusedReturnValue")
@Getter
public class AllayChunkBuilder {
    private ChunkState state;
    private int chunkX;
    private int chunkZ;
    private DimensionInfo dimensionInfo;
    private AllayChunkSection[] sections;
    private HeightMap heightMap;
    private List<NbtMap> entitiyList;
    private List<NbtMap> blockEntitiyList;
    private Int2ObjectNonBlockingMap<ScheduledUpdateInfo> scheduledUpdates;

    private static AllayChunkSection[] createEmptySections(DimensionInfo dimensionInfo) {
        var sections = new AllayChunkSection[dimensionInfo.chunkSectionCount()];
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new AllayChunkSection((byte) (i + dimensionInfo.minSectionY()));
        }
        return sections;
    }

    public AllayChunkBuilder chunkX(int chunkX) {
        this.chunkX = chunkX;
        return this;
    }

    public AllayChunkBuilder chunkZ(int chunkZ) {
        this.chunkZ = chunkZ;
        return this;
    }

    public AllayChunkBuilder state(ChunkState state) {
        this.state = state;
        return this;
    }

    public AllayChunkBuilder dimensionInfo(DimensionInfo dimensionInfo) {
        Preconditions.checkArgument(this.dimensionInfo == null);
        this.dimensionInfo = dimensionInfo;
        return this;
    }

    public AllayChunkBuilder sections(AllayChunkSection[] sections) {
        Preconditions.checkNotNull(dimensionInfo);
        Preconditions.checkArgument(sections.length == dimensionInfo.chunkSectionCount());
        for (int index = 0; index < sections.length; index++) {
            var section = sections[index];
            Preconditions.checkNotNull(section);
            Preconditions.checkArgument((section.sectionY() - dimensionInfo.minSectionY()) == index);
        }
        this.sections = sections;
        return this;
    }

    public AllayChunkBuilder heightMap(HeightMap heightMap) {
        this.heightMap = heightMap;
        return this;
    }

    public AllayChunkBuilder entities(List<NbtMap> entities) {
        this.entitiyList = entities;
        return this;
    }

    public AllayChunkBuilder blockEntities(List<NbtMap> blockEntities) {
        this.blockEntitiyList = blockEntities;
        return this;
    }

    public AllayChunkBuilder scheduledUpdates(Int2ObjectNonBlockingMap<ScheduledUpdateInfo> scheduledUpdates) {
        this.scheduledUpdates = scheduledUpdates;
        return this;
    }

    public AllayUnsafeChunk build() {
        Preconditions.checkNotNull(dimensionInfo);

        if (state == null) state = ChunkState.FINISHED;
        if (sections == null) sections = createEmptySections(dimensionInfo);
        if (heightMap == null) heightMap = new HeightMap((short) dimensionInfo.minHeight());
        if (scheduledUpdates == null) scheduledUpdates = new Int2ObjectNonBlockingMap<>();

        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionInfo,
                sections, heightMap, scheduledUpdates,
                state, entitiyList, blockEntitiyList
        );
    }

    public AllayUnsafeChunk newChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionInfo,
                createEmptySections(dimensionInfo),
                new HeightMap((short) dimensionInfo.minHeight()),
                new Int2ObjectNonBlockingMap<>(),
                ChunkState.NEW, null, null
        );
    }
}
