package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.poi.PoiType;
import org.jctools.maps.NonBlockingHashMap;

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
    private NonBlockingHashMap<Integer, BlockEntity> blockEntities;
    private NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates;
    private NonBlockingHashMap<Integer, PoiType> poiEntries;

    public AllayChunkBuilder() {
        this.state = ChunkState.FULL;
        this.blockEntities = new NonBlockingHashMap<>();
        this.scheduledUpdates = new NonBlockingHashMap<>();
        this.poiEntries = new NonBlockingHashMap<>();
    }

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

    public AllayChunkBuilder blockEntities(NonBlockingHashMap<Integer, BlockEntity> blockEntities) {
        this.blockEntities = blockEntities;
        return this;
    }

    public AllayChunkBuilder scheduledUpdates(NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates) {
        this.scheduledUpdates = scheduledUpdates;
        return this;
    }

    public AllayChunkBuilder poiEntries(NonBlockingHashMap<Integer, PoiType> poiEntries) {
        this.poiEntries = poiEntries;
        return this;
    }

    public AllayUnsafeChunk build() {
        Preconditions.checkNotNull(dimensionInfo);

        if (sections == null) sections = createEmptySections(dimensionInfo);
        if (heightMap == null) heightMap = new HeightMap((short) dimensionInfo.minHeight());

        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionInfo,
                sections, heightMap, scheduledUpdates,
                state, blockEntities, poiEntries
        );
    }

    public AllayUnsafeChunk newChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionInfo,
                createEmptySections(dimensionInfo),
                new HeightMap((short) dimensionInfo.minHeight()),
                new NonBlockingHashMap<>(),
                ChunkState.NEW, new NonBlockingHashMap<>(),
                new NonBlockingHashMap<>()
        );
    }

    public AllayUnsafeChunk voidChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionInfo,
                createEmptySections(dimensionInfo),
                new HeightMap((short) dimensionInfo.minHeight()),
                new NonBlockingHashMap<>(),
                ChunkState.FULL, new NonBlockingHashMap<>(),
                new NonBlockingHashMap<>()
        );
    }
}
