package org.allaymc.api.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockStateSafeGetter;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityHelper;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final BlockState STRUCTURE_VOID_DEFAULT_STATE = BlockTypes.STRUCTURE_VOID.getDefaultState();

    public static Structure interceptStructure(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
        return interceptStructure(dimension, x, y, z, sizeX, sizeY, sizeZ, true);
    }

    public static Structure interceptStructure(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ, boolean saveEntities) {
        var blockStates = new BlockState[2][sizeX][sizeY][sizeZ];
        var blockEntities = new HashMap<Vector3ic, NbtMap>();
        var entities = new ArrayList<NbtMap>();

        for (int lx = 0; lx < sizeX; lx++) {
            for (int ly = 0; ly < sizeY; ly++) {
                for (int lz = 0; lz < sizeZ; lz++) {
                    blockStates[0][lx][ly][lz] = dimension.getBlockState(x + lx, y + ly, z + lz, 0);
                    blockStates[1][lx][ly][lz] = dimension.getBlockState(x + lx, y + ly, z + lz, 1);
                    blockEntities.put(new Vector3i(lx, ly, lz), dimension.getBlockEntity(x + lx, y + ly, z + lz).saveNBT());
                }
            }
        }

        if (saveEntities) {
            dimension.getEntities().forEach((runtimeID, entity) -> {
                var location = entity.getLocation();
                if (x <= location.x() && x + sizeX > location.x() &&
                    y <= location.y() && y + sizeY > location.y() &&
                    z <= location.z() && z + sizeZ > location.z()) {
                    entities.add(entity.saveNBT());
                }
            });
        }
        return new Structure(blockStates, blockEntities, entities, sizeX, sizeY, sizeZ, x, y, z);
    }

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
        var blockPalette = palette.getList("block_palette", NbtType.COMPOUND).stream().map(BlockStateSafeGetter::fromNBT).toList();

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
                        blockStates[1][lx][ly][lz] = blockPalette.get(layer0.get(indexFormPos(sizeX, sizeY, sizeZ, lx, ly, lz)));
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
                structureNBT.getList("entityNBT", NbtType.COMPOUND),
                sizeX, sizeY, sizeZ,
                origin.get(0), origin.get(1), origin.get(2)
        );
    }

    public void place(Dimension dimension, int x, int y, int z) {
        for (int lx = 0; lx < sizeX; lx++) {
            for (int ly = 0; ly < sizeY; ly++) {
                for (int lz = 0; lz < sizeZ; lz++) {
                    if (blockStates[0][lx][ly][lz] != STRUCTURE_VOID_DEFAULT_STATE) {
                        dimension.setBlockState(x + lx, y + ly, z + lz, blockStates[0][lx][ly][lz], 0);
                    }
                    if (blockStates[1][lx][ly][lz] != STRUCTURE_VOID_DEFAULT_STATE) {
                        dimension.setBlockState(x + lx, y + ly, z + lz, blockStates[1][lx][ly][lz], 1);
                    }
                }
            }
        }

        for (var entry : blockEntities.entrySet()) {
            // Block entity should also being spawned when placing block
            // if the block entity is implemented
            var blockEntity = dimension.getBlockEntity(entry.getKey());
            if (blockEntity == null) {
                // Block entity not implemented maybe
                continue;
            }
            blockEntity.loadNBT(entry.getValue());
        }
        for (var nbt : entities) {
            dimension.getEntityService().addEntity(EntityHelper.fromNBT(dimension, nbt));
        }
    }

    public NbtMap toNBT() {
        var layer0 = new ArrayList<Integer>();
        var layer1 = new ArrayList<Integer>();
        var palette = new BlockStatePalette();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    layer0.set(indexFormPos(sizeX, sizeY, sizeZ, x, y, z), palette.getIndexOf(blockStates[0][x][y][z]));
                    layer1.set(indexFormPos(sizeX, sizeY, sizeZ, x, y, z), palette.getIndexOf(blockStates[1][x][y][z]));
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
                                        new NbtList<>(NbtType.INT, layer0),
                                        new NbtList<>(NbtType.INT, layer1))
                                .putList("entities", NbtType.COMPOUND, entities)
                                .putCompound("palette", NbtMap.builder().putCompound(
                                        "default", NbtMap.builder()
                                                .putList(
                                                        "block_palette",
                                                        NbtType.COMPOUND,
                                                        palette.getPalette()
                                                                .stream()
                                                                .map(BlockState::getBlockStateTag)
                                                                .toList())
                                                .putCompound("block_position_data", blockEntityNBT.build())
                                                .build()
                                ).build())
                                .build()
                )
                .putList("structure_world_origin", NbtType.INT, x, y, z)
                .build();
    }

    private static int indexFormPos(int sizeX, int sizeY, int sizeZ, int x, int y, int z) {
        return x * sizeY * sizeZ + y * sizeZ + z;
    }

    private static Vector3i posFromIndex(int sizeX, int sizeY, int sizeZ, int index) {
        return new Vector3i(index / (sizeY * sizeZ), index % (sizeY * sizeZ) / sizeZ, index % (sizeY * sizeZ) % sizeZ);
    }

    private static class BlockStatePalette {
        @Getter
        private final List<BlockState> palette = new ArrayList<>();

        public int getIndexOf(BlockState block) {
            if (block == STRUCTURE_VOID_DEFAULT_STATE) {
                return -1;
            }
            if (!palette.contains(block)) {
                return palette.indexOf(block);
            } else {
                var index = palette.size();
                palette.add(block);
                return index;
            }
        }
    }
}