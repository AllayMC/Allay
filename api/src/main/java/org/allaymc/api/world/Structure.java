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
 * @author harry-xi | daoge_cmd
 */
@Slf4j
public record Structure(
        // layer-x-y-z
        BlockState[][][][] blockStates,
        Map<Vector3ic, NbtMap> blockEntities,
        List<NbtMap> entities,
        int sizeX, int sizeY, int sizeZ,
        int x, int y, int z
) {
    private static final int FORMAT_VERSION = 1;
    private static final BlockState STRUCTURE_VOID_DEFAULT_STATE = BlockTypes.STRUCTURE_VOID.getDefaultState();

    /**
     * @see #pick(Dimension, int, int, int, int, int, int, boolean)
     */
    public static Structure pick(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
        return pick(dimension, x, y, z, sizeX, sizeY, sizeZ, true);
    }

    /**
     * Pick a structure from the dimension.
     *
     * @param dimension    the dimension to pick the structure from
     * @param x            the x coordinate of the structure
     * @param y            the y coordinate of the structure
     * @param z            the z coordinate of the structure
     * @param sizeX        the size of the structure in x direction
     * @param sizeY        the size of the structure in y direction
     * @param sizeZ        the size of the structure in z direction
     * @param saveEntities whether to save the entities in the structure
     * @return the picked structure
     */
    public static Structure pick(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ, boolean saveEntities) {
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
                        // Vanilla save the original position data for block entity (and entity),
                        // which is useless as when we place the structure in different position,
                        // the old position data is not useful anymore. However, we still save it
                        // to follow the vanilla behavior for best compatibility.
                        blockEntities.put(new Vector3i(lx, ly, lz), blockEntity.saveNBT());
                    }
                }
            }
        }

        if (saveEntities) {
            dimension.getEntities().forEach((runtimeID, entity) -> {
                if (entity.getEntityType() == EntityTypes.PLAYER) {
                    // Skip player entity
                    return;
                }
                var location = entity.getLocation();
                if (x <= location.x() && x + sizeX > location.x() &&
                    y <= location.y() && y + sizeY > location.y() &&
                    z <= location.z() && z + sizeZ > location.z()) {
                    // Position data for entity is also saved, see the comment above
                    entities.add(entity.saveNBT());
                }
            });
        }

        return new Structure(blockStates, blockEntities, entities, sizeX, sizeY, sizeZ, x, y, z);
    }

    /**
     * Load structure data from nbt.
     *
     * @param nbt the nbt data to load
     * @return the loaded structure
     */
    public static Structure formNBT(NbtMap nbt) {
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
        return new Structure(
                blockStates, blockEntities,
                structureNBT.getList("entities", NbtType.COMPOUND),
                sizeX, sizeY, sizeZ,
                origin.get(0), origin.get(1), origin.get(2)
        );
    }

    private static int indexFormPos(int sizeX, int sizeY, int sizeZ, int x, int y, int z) {
        // sizeX is kept for better looking
        return x * sizeY * sizeZ + y * sizeZ + z;
    }

    private static Vector3i posFromIndex(int sizeX, int sizeY, int sizeZ, int index) {
        // sizeX is kept for better looking
        return new Vector3i(index / (sizeY * sizeZ), index % (sizeY * sizeZ) / sizeZ, index % (sizeY * sizeZ) % sizeZ);
    }

    /**
     * Place the structure in the dimension.
     *
     * @param dimension the dimension to place the structure in
     * @param x         the x coordinate to place the structure
     * @param y         the y coordinate to place the structure
     * @param z         the z coordinate to place the structure
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
            // Block entity should also being spawned when placing block
            // if the block entity is implemented
            var blockEntity = dimension.getBlockEntity(entry.getKey().x() + x, entry.getKey().y() + y, entry.getKey().z() + z);
            if (blockEntity == null) {
                // Block entity not implemented maybe
                continue;
            }
            // No need to put the new position data into the nbt, as
            // the block entity have spawned and already have the new
            // position data, so just remove the old position data.
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
            // Calculate the new position for this entity
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
     * Save the structure data to nbt.
     *
     * @return the nbt data of the structure
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

    private static class BlockStatePalette {
        @Getter
        private final List<BlockState> palette = new ArrayList<>();

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

    @StandardException
    private static class StructureException extends RuntimeException {
    }
}
