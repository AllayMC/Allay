package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.poi.PoiType;
import org.jctools.maps.NonBlockingHashMap;

/**
 * {@code AllayChunkBuilder} assembles
 * unsafe chunks from dimension-aligned sections and shared mutable
 * chunk data. Builder-based construction defaults to a fully generated state,
 * while the direct new-chunk and void-chunk factories create their own empty
 * data with explicitly different generation states.
 *
 * <p>
 * The dimension must be selected before supplied sections can be validated
 * against its vertical range. {@code build()} fills missing sections and heights but
 * retains arrays, maps, and height objects rather than copying them. Reusing
 * the builder can therefore create chunks sharing mutable data. Callers
 * restoring storage must provide maps using chunk-local position hashes and
 * ensure that supplied sections belong to the selected dimension.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("UnusedReturnValue")
@Getter
public class AllayChunkBuilder {
    /**
     * Generation state for builder-based construction.
     */
    private ChunkState state;
    /**
     * Absolute chunk X coordinate.
     */
    private int chunkX;
    /**
     * Absolute chunk Z coordinate.
     */
    private int chunkZ;
    /**
     * {@code dimensionType} defines the vertical range used by supplied
     * sections. Once {@code dimensionType} is non-{@code null},
     * {@code dimensionType(DimensionType)} rejects another selection.
     */
    private DimensionType dimensionType;
    /**
     * {@code sections} holds the retained section array shared by built
     * chunks. {@code build()} allocates the array from the dimension when absent.
     */
    private AllayChunkSection[] sections;
    /**
     * {@code heightMap} holds the retained height cache shared by built chunks.
     * {@code build()} initializes absent storage to the dimension minimum.
     */
    private HeightMap heightMap;
    /**
     * {@code blockEntities} stores block entities by local position hash. Built
     * chunks share this mutable map with the builder and its generated getter.
     */
    private NonBlockingHashMap<Integer, BlockEntity> blockEntities;
    /**
     * {@code scheduledUpdates} stores updates by local position hash. Built
     * chunks share this mutable map with the builder and its generated getter.
     */
    private NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates;
    /**
     * {@code poiEntries} stores points of interest by local position hash. Built
     * chunks share this mutable map with the builder and its generated getter.
     */
    private NonBlockingHashMap<Integer, PoiType> poiEntries;

    /**
     * Creates a builder with {@code FULL} state, zero chunk coordinates, and
     * independent empty data maps. A dimension must be supplied before
     * building; sections and heights are created lazily when absent.
     */
    public AllayChunkBuilder() {
        this.state = ChunkState.FULL;
        this.blockEntities = new NonBlockingHashMap<>();
        this.scheduledUpdates = new NonBlockingHashMap<>();
        this.poiEntries = new NonBlockingHashMap<>();
    }

    /**
     * Creates independent air sections ordered across the dimension's complete
     * vertical range. Each section stores its absolute Y coordinate and
     * receives separate block palettes plus a plains biome palette.
     */
    private static AllayChunkSection[] createEmptySections(DimensionType dimensionType) {
        var sections = new AllayChunkSection[dimensionType.chunkSectionCount()];
        for (int i = 0; i < sections.length; i++) {
            // Adding the minimum section coordinate maps array indices to
            // absolute section heights.
            sections[i] = new AllayChunkSection((byte) (i + dimensionType.minSectionY()));
        }
        return sections;
    }

    /**
     * Sets the absolute chunk X coordinate and returns this mutable builder.
     */
    public AllayChunkBuilder chunkX(int chunkX) {
        this.chunkX = chunkX;
        return this;
    }

    /**
     * Sets the absolute chunk Z coordinate and returns this mutable builder.
     */
    public AllayChunkBuilder chunkZ(int chunkZ) {
        this.chunkZ = chunkZ;
        return this;
    }

    /**
     * Sets the generation state and returns this mutable builder.
     */
    public AllayChunkBuilder state(ChunkState state) {
        this.state = state;
        return this;
    }

    /**
     * Selects the dimension used to validate sections and allocate default
     * chunk data. A previously selected dimension other than {@code null} causes {@link
     * IllegalArgumentException}; this restriction prevents changing the
     * vertical basis after selection.
     */
    public AllayChunkBuilder dimensionType(DimensionType dimensionType) {
        Preconditions.checkArgument(this.dimensionType == null);
        this.dimensionType = dimensionType;
        return this;
    }

    /**
     * Retains a complete section array after checking its correspondence to the
     * selected dimension. The dimension must already be set, the array length
     * must equal its section count, and every entry must differ from
     * {@code null} with the expected absolute Y coordinate. These checks
     * prevent the array index and encoded section identity from disagreeing.
     * No copy is made, so later external changes remain visible to built chunks.
     */
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

    /**
     * Retains the supplied height map and returns this builder for further
     * configuration. Built chunks share the same map; {@code null} requests the
     * dimension-minimum default during construction.
     */
    public AllayChunkBuilder heightMap(HeightMap heightMap) {
        this.heightMap = heightMap;
        return this;
    }

    /**
     * Retains the supplied block-entity map and returns this mutable builder.
     * Built chunks share its entries directly, and callers must supply a
     * map other than {@code null} keyed by chunk-local position hashes.
     */
    public AllayChunkBuilder blockEntities(NonBlockingHashMap<Integer, BlockEntity> blockEntities) {
        this.blockEntities = blockEntities;
        return this;
    }

    /**
     * Retains the supplied scheduled-update map and returns this mutable
     * builder. Built chunks share its entries directly, and callers must supply
     * a map other than {@code null} keyed by chunk-local position hashes.
     */
    public AllayChunkBuilder scheduledUpdates(NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates) {
        this.scheduledUpdates = scheduledUpdates;
        return this;
    }

    /**
     * Retains the supplied point-of-interest map and returns this mutable
     * builder. Built chunks share its entries directly, and callers must supply
     * a map other than {@code null} keyed by chunk-local position hashes.
     */
    public AllayChunkBuilder poiEntries(NonBlockingHashMap<Integer, PoiType> poiEntries) {
        this.poiEntries = poiEntries;
        return this;
    }

    /**
     * Creates a chunk using the selected coordinates, generation state,
     * dimension, and retained data. A dimension is required; absent sections
     * become air with plains biomes, and absent heights become the dimension
     * minimum. Those defaults are stored back into this builder before
     * construction, so later builds reuse them. Arrays, maps, and height
     * storage are not copied, and repeated builds therefore require callers to
     * manage shared mutable ownership.
     */
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

    /**
     * Creates an independent empty chunk in {@code NEW} state for the supplied
     * coordinates and dimension. This factory ignores the builder's configured
     * fields and allocates fresh sections, heights, and data maps.
     */
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

    /**
     * Creates an independent empty chunk in {@code FULL} state for the supplied
     * coordinates and dimension. This factory ignores the builder's configured
     * fields and allocates fresh sections, heights, and data maps.
     */
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
