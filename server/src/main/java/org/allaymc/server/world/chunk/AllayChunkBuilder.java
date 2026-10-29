package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.dimension.DimensionType;
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
    private DimensionType dimensionType;
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

    private static AllayChunkSection[] createEmptySections(DimensionType dimensionType) {
        var sections = new AllayChunkSection[dimensionType.chunkSectionCount()];
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new AllayChunkSection((byte) (i + dimensionType.minSectionY()));
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

    public AllayChunkBuilder dimensionType(DimensionType dimensionType) {
        Preconditions.checkArgument(this.dimensionType == null);
        this.dimensionType = dimensionType;
        return this;
    }

    public AllayChunkBuilder sections(AllayChunkSection[] sections) {
        Preconditions.checkNotNull(dimensionType);
        Preconditions.checkArgument(sections.length == dimensionType.chunkSectionCount());
        for (int index = 0; index < sections.length; index++) {
            var section = sections[index];
            Preconditions.checkNotNull(section);
            Preconditions.checkArgument((section.sectionY() - dimensionType.minSectionY()) == index);
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
        Preconditions.checkNotNull(dimensionType);

        if (sections == null) sections = createEmptySections(dimensionType);
        if (heightMap == null) heightMap = new HeightMap((short) dimensionType.getMinHeight());

        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionType,
                sections, heightMap, scheduledUpdates,
                state, blockEntities, poiEntries
        );
    }

    public AllayUnsafeChunk newChunk(int chunkX, int chunkZ, DimensionType dimensionType) {
        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionType,
                createEmptySections(dimensionType),
                new HeightMap((short) dimensionType.getMinHeight()),
                new NonBlockingHashMap<>(),
                ChunkState.NEW, new NonBlockingHashMap<>(),
                new NonBlockingHashMap<>()
        );
    }

    public AllayUnsafeChunk voidChunk(int chunkX, int chunkZ, DimensionType dimensionType) {
        return new AllayUnsafeChunk(
                chunkX, chunkZ, dimensionType,
                createEmptySections(dimensionType),
                new HeightMap((short) dimensionType.getMinHeight()),
                new NonBlockingHashMap<>(),
                ChunkState.FULL, new NonBlockingHashMap<>(),
                new NonBlockingHashMap<>()
        );
    }
}
