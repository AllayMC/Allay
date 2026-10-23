package org.allaymc.api.world;

import lombok.Getter;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.NBTIO;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;

/**
 * {@code StructureFile} represents a captured block region, its block
 * entities, nonplayer entities, and original world coordinates. The block
 * arrays use layer, local x, local y, and local z indices. Block entity keys
 * are local coordinates, while entity NBT retains world positions; the stored
 * origin lets placement translate entities into the requested destination. The
 * record retains its arrays, map, and list without defensive copies, so
 * callers coordinate access to this mutable content.
 *
 * <p>
 * NBT conversion flattens both block layers in x-major, then y, then z order
 * and shares one block-state palette. Palette index {@code -1} represents
 * structure void, which placement skips rather than overwriting destination
 * blocks. Placement writes blocks before applying block entity data, removes
 * obsolete absolute block entity coordinates, and offsets entity positions by
 * the difference between stored and target origins. Failures can leave earlier
 * placements applied because the operation has no rollback.
 *
 * @param blockStates retained layer-x-y-z block state arrays
 * @param blockEntities retained map from local coordinates to block entity NBT
 * @param entities retained nonplayer entity NBT list
 * @param sizeX region x extent in blocks
 * @param sizeY region y extent in blocks
 * @param sizeZ region z extent in blocks
 * @param x original world x coordinate
 * @param y original world y coordinate
 * @param z original world z coordinate
 *
 * @author harry-xi | daoge_cmd
 */
@Slf4j
public record StructureFile(
        BlockState[][][][] blockStates,
        Map<Vector3ic, NbtMap> blockEntities,
        List<NbtMap> entities,
        int sizeX, int sizeY, int sizeZ,
        int x, int y, int z
) {
    /**
     * {@code FORMAT_VERSION} selects the structure NBT version accepted on
     * input and written by {@code toNBT}.
     */
    private static final int FORMAT_VERSION = 1;
    /**
     * {@code STRUCTURE_VOID_DEFAULT_STATE} marks palette omissions and
     * destination blocks that {@code place} must leave unchanged when
     * restoring a structure.
     */
    private static final BlockState STRUCTURE_VOID_DEFAULT_STATE = BlockTypes.STRUCTURE_VOID.getDefaultState();

    /**
     * Captures a region together with the nonplayer entities inside its
     * bounds. This overload enables entity capture and delegates coordinate
     * handling to the full operation; callers coordinate capture with
     * dimension mutations.
     */
    public static StructureFile pick(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
        return pick(dimension, x, y, z, sizeX, sizeY, sizeZ, true);
    }

    /**
     * Captures two block layers, block entity NBT, and optionally nonplayer
     * entities in a region. Callers supply nonnegative sizes and coordinate
     * capture with dimension changes; reads of unloaded blocks follow the
     * dimension's ordinary fallback behavior.
     *
     * <p>
     * Block entity keys are relative to the region origin, while saved entity
     * positions remain absolute. The returned record stores that origin so
     * later placement can translate entities without changing their offsets
     * inside the structure. Player entities are excluded even when {@code
     * saveEntities} is {@code true}.
     */
    public static StructureFile pick(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ, boolean saveEntities) {
        var blockStates = new BlockState[2][sizeX][sizeY][sizeZ];
        var blockEntities = new HashMap<Vector3ic, NbtMap>();
        var entities = new ArrayList<NbtMap>();

        for (int lx = 0; lx < sizeX; lx++) {
            for (int ly = 0; ly < sizeY; ly++) {
                for (int lz = 0; lz < sizeZ; lz++) {
                    blockStates[0][lx][ly][lz] = dimension.getBlockState(x + lx, y + ly, z + lz, 0);
                    blockStates[1][lx][ly][lz] = dimension.getBlockState(x + lx, y + ly, z + lz, 1);
                    var blockEntity = dimension.getBlockEntity(x + lx, y + ly, z + lz);
                    if (blockEntity != null) {
                        // Keep complete block entity NBT during capture. Placement removes its old
                        // absolute coordinates before loading, preserving the destination instance's
                        // position without discarding other saved fields here.
                        blockEntities.put(new Vector3i(lx, ly, lz), blockEntity.saveNBT());
                    }
                }
            }
        }

        if (saveEntities) {
            dimension.getEntities().forEach((runtimeID, entity) -> {
                if (entity.getEntityType() == EntityTypes.PLAYER) {
                    // Player state belongs to the player lifecycle; copying it with a
                    // structure would duplicate a connected player as saved entity content.
                    return;
                }
                var location = entity.getLocation();
                if (x <= location.x() && x + sizeX > location.x() &&
                    y <= location.y() && y + sizeY > location.y() &&
                    z <= location.z() && z + sizeZ > location.z()) {
                    // Retain the entity's absolute position with the capture origin so
                    // placement can translate its offset into the destination region.
                    entities.add(entity.saveNBT());
                }
            });
        }

        return new StructureFile(blockStates, blockEntities, entities, sizeX, sizeY, sizeZ, x, y, z);
    }

    /**
     * Decodes a versioned structure NBT payload into the two-layer
     * representation. The decoder rejects an unexpected version, incorrect
     * size or origin list lengths, and layer lengths inconsistent with the
     * region volume by throwing {@code StructureException}. Other malformed
     * palette references or data can fail through their underlying accessors.
     *
     * <p>
     * Palette index {@code -1} becomes structure void so omitted blocks remain
     * distinguishable from air. Block entity indices decode into local
     * coordinates using the same flattening order as {@code toNBT}, while the
     * original world origin is retained for later entity translation.
     */
    public static StructureFile fromNBT(NbtMap nbt) {
        if (nbt.getInt("format_version") != FORMAT_VERSION) {
            throw new StructureException("format_version should be " + FORMAT_VERSION);
        }

        if (nbt.getList("size", NbtType.INT).size() != 3) {
            throw new StructureException("size of size list should be 3");
        }

        var size = nbt.getList("size", NbtType.INT);
        var sizeX = size.get(0);
        var sizeY = size.get(1);
        var sizeZ = size.get(2);
        var structureNBT = nbt.getCompound("structure");
        var blockIndices = structureNBT.getList("block_indices", NbtType.LIST);

        if (blockIndices.size() != 2) {
            throw new StructureException("block_indices should have 2 layer");
        }

        if (blockIndices.get(0).size() != sizeX * sizeY * sizeZ) {
            throw new StructureException("size of layer0 incorrect, it should be" + sizeX * sizeY * sizeZ);
        }

        if (blockIndices.get(1).size() != sizeX * sizeY * sizeZ) {
            throw new StructureException("size of layer1 incorrect, it should be" + sizeX * sizeY * sizeZ);
        }

        var layer0 = (List<Integer>) blockIndices.get(0);
        var layer1 = (List<Integer>) blockIndices.get(1);
        var palette = structureNBT.getCompound("palette").getCompound("default");
        var blockEntityNBT = palette.getCompound("block_position_data");
        var blockPalette = palette.getList("block_palette", NbtType.COMPOUND).stream().map(NBTIO.getAPI()::fromBlockStateNBT).toList();

        var blockStates = new BlockState[2][sizeX][sizeY][sizeZ];
        for (int lx = 0; lx < sizeX; lx++) {
            for (int ly = 0; ly < sizeY; ly++) {
                for (int lz = 0; lz < sizeZ; lz++) {
                    // Decode omitted entries as structure void rather than air; the
                    // placement path must distinguish untouched blocks from explicit clears.
                    if (layer0.get(indexFormPos(sizeX, sizeY, sizeZ, lx, ly, lz)) == -1) {
                        blockStates[0][lx][ly][lz] = STRUCTURE_VOID_DEFAULT_STATE;
                    } else {
                        blockStates[0][lx][ly][lz] = blockPalette.get(layer0.get(indexFormPos(sizeX, sizeY, sizeZ, lx, ly, lz)));
                    }
                    if (layer1.get(indexFormPos(sizeX, sizeY, sizeZ, lx, ly, lz)) == -1) {
                        blockStates[1][lx][ly][lz] = STRUCTURE_VOID_DEFAULT_STATE;
                    } else {
                        blockStates[1][lx][ly][lz] = blockPalette.get(layer1.get(indexFormPos(sizeX, sizeY, sizeZ, lx, ly, lz)));
                    }
                }
            }
        }

        var blockEntities = new HashMap<Vector3ic, NbtMap>();
        for (var index : blockEntityNBT.keySet()) {
            blockEntities.put(
                    posFromIndex(sizeX, sizeY, sizeZ, Integer.parseInt(index)),
                    blockEntityNBT.getCompound(index)
            );
        }

        if (nbt.getList("structure_world_origin", NbtType.INT).size() != 3) {
            throw new StructureException("size of structure_world_origin list should be 3");
        }

        var origin = nbt.getList("structure_world_origin", NbtType.INT);
        return new StructureFile(
                blockStates, blockEntities,
                structureNBT.getList("entities", NbtType.COMPOUND),
                sizeX, sizeY, sizeZ,
                origin.get(0), origin.get(1), origin.get(2)
        );
    }

    /**
     * Returns the flattened x-major index for a local block position. {@code
     * sizeY} and {@code sizeZ} define the strides; {@code sizeX} remains a
     * symmetric dimension argument and is not used for bounds validation.
     */
    private static int indexFormPos(int sizeX, int sizeY, int sizeZ, int x, int y, int z) {
        return x * sizeY * sizeZ + y * sizeZ + z;
    }

    /**
     * Returns local coordinates decoded from the flattened x-major index.
     * Callers provide positive {@code sizeY} and {@code sizeZ}; {@code sizeX}
     * preserves the matching conversion signature without validating the
     * resulting coordinate.
     */
    private static Vector3i posFromIndex(int sizeX, int sizeY, int sizeZ, int index) {
        return new Vector3i(index / (sizeY * sizeZ), index % (sizeY * sizeZ) / sizeZ, index % (sizeY * sizeZ) % sizeZ);
    }

    /**
     * Places the captured region at a target origin with block notifications
     * enabled and neighbor updates disabled. Structure void entries leave
     * destination blocks unchanged, and individual block placement results are
     * not checked. Callers coordinate the operation with dimension mutations;
     * failures do not undo earlier writes.
     *
     * <p>
     * Blocks are placed first so their block entity instances can receive
     * saved data. The loader removes old absolute block entity coordinates to
     * retain the destination position and translates each ordinary entity by
     * the difference between the stored and target origins before requesting
     * addition.
     */
    public void place(Dimension dimension, int x, int y, int z) {
        for (int lx = 0; lx < sizeX; lx++) {
            for (int ly = 0; ly < sizeY; ly++) {
                for (int lz = 0; lz < sizeZ; lz++) {
                    if (!blockStates[0][lx][ly][lz].equals(STRUCTURE_VOID_DEFAULT_STATE)) {
                        dimension.setBlockState(x + lx, y + ly, z + lz, blockStates[0][lx][ly][lz], 0, true, false);
                    }
                    if (!blockStates[1][lx][ly][lz].equals(STRUCTURE_VOID_DEFAULT_STATE)) {
                        dimension.setBlockState(x + lx, y + ly, z + lz, blockStates[1][lx][ly][lz], 1, true, false);
                    }
                }
            }
        }

        for (var entry : blockEntities.entrySet()) {
            // Block placement creates supported block entities before their saved data
            // is applied, so restoration targets the instance at the destination coordinates.
            var blockEntity = dimension.getBlockEntity(entry.getKey().x() + x, entry.getKey().y() + y, entry.getKey().z() + z);
            if (blockEntity == null) {
                // A skipped or unsupported block has no destination block entity;
                // omit its saved data rather than creating an unattached instance.
                continue;
            }
            // loadNBT preserves position components absent from the payload. Removing
            // saved coordinates keeps the destination position while restoring the
            // remaining block entity fields after block placement.
            var oldNbt = entry.getValue();
            var builder = oldNbt.toBuilder();
            builder.remove("x");
            builder.remove("y");
            builder.remove("z");
            blockEntity.loadNBT(builder.build());
        }
        for (var nbt : entities) {
            var builder = nbt.toBuilder();
            var oldPos = AllayNBTUtils.readVector3f(nbt, "Pos");
            // Preserve each entity's displacement from the capture origin when
            // translating its saved absolute position into the destination region.
            AllayNBTUtils.writeVector3f(
                    builder, "Pos",
                    oldPos.x - this.x + x,
                    oldPos.y - this.y + y,
                    oldPos.z - this.z + z
            );
            dimension.getEntityManager().addEntity(NBTIO.getAPI().fromEntityNBT(dimension, builder.build()));
        }
    }

    /**
     * Encodes the captured region using the supported structure NBT format.
     * Both layers share one block palette, and local block entity positions
     * use the same flattened indices as block data. Callers keep the retained
     * content stable while the method reads it.
     *
     * <p>
     * Structure void uses index {@code -1} rather than occupying the palette,
     * preserving the distinction between an omitted destination block and an
     * explicit air block. The payload retains original world coordinates
     * because entity NBT still contains absolute positions that placement must
     * translate.
     */
    public NbtMap toNBT() {
        var capacity = sizeX * sizeY * sizeZ;
        var layer0 = new Integer[capacity];
        var layer1 = new Integer[capacity];
        var palette = new BlockStatePalette();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    layer0[indexFormPos(sizeX, sizeY, sizeZ, x, y, z)] = palette.getIndexOf(blockStates[0][x][y][z]);
                    layer1[indexFormPos(sizeX, sizeY, sizeZ, x, y, z)] = palette.getIndexOf(blockStates[1][x][y][z]);
                }
            }
        }
        var blockEntityNBT = NbtMap.builder();
        for (var index : blockEntities.entrySet()) {
            var pos = index.getKey();
            blockEntityNBT.putCompound(
                    Integer.toString(indexFormPos(sizeX, sizeY, sizeZ, pos.x(), pos.y(), pos.z())),
                    index.getValue()
            );
        }

        return NbtMap.builder()
                .putInt("format_version", FORMAT_VERSION)
                .putList("size", NbtType.INT, sizeX, sizeY, sizeZ)
                .putCompound("structure",
                        NbtMap.builder()
                                .putList(
                                        "block_indices",
                                        NbtType.LIST,
                                        new NbtList<>(NbtType.INT, Arrays.asList(layer0)),
                                        new NbtList<>(NbtType.INT, Arrays.asList(layer1)))
                                .putList("entities", NbtType.COMPOUND, entities)
                                .putCompound("palette", NbtMap.builder().putCompound(
                                        "default", NbtMap.builder()
                                                .putList(
                                                        "block_palette",
                                                        NbtType.COMPOUND,
                                                        palette.getPalette()
                                                                .stream()
                                                                .map(BlockState::getBlockStateNBT)
                                                                .toList())
                                                .putCompound("block_position_data", blockEntityNBT.build())
                                                .build()
                                ).build())
                                .build()
                )
                .putList("structure_world_origin", NbtType.INT, x, y, z)
                .build();
    }

    /**
     * {@code BlockStatePalette} assigns shared indices to states across both
     * serialized block layers.
     */
    private static class BlockStatePalette {
        /**
         * {@code palette} retains insertion order for NBT serialization; the
         * generated getter exposes the same mutable list used by index
         * assignment.
         */
        @Getter
        private final List<BlockState> palette = new ArrayList<>();

        /**
         * Returns a stable palette index, adding an unseen state when needed.
         * Structure void returns {@code -1} without entering the palette, and
         * subsequent equal states reuse their existing index.
         */
        public int getIndexOf(BlockState block) {
            if (block.equals(STRUCTURE_VOID_DEFAULT_STATE)) {
                return -1;
            }
            if (palette.contains(block)) {
                return palette.indexOf(block);
            } else {
                var index = palette.size();
                palette.add(block);
                return index;
            }
        }
    }

    /**
     * {@code StructureException} reports explicit structure format checks that
     * reject an NBT payload.
     */
    @StandardException
    private static class StructureException extends RuntimeException {
    }
}
