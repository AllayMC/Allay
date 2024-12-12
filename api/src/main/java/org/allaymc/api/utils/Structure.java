package org.allaymc.api.utils;

import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockStateHelper;
import org.allaymc.api.entity.EntityHelper;
import org.allaymc.api.server.Server;
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
import java.util.concurrent.Future;

import static org.allaymc.api.block.type.BlockTypes.STRUCTURE_VOID;

public record Structure(
        BlockState[][][][] blocks,
        Map<Vector3ic, NbtMap> blockEntities,
        List<NbtMap> entities,
        int sizeX, int sizeY, int sizeZ,
        int x, int y, int z
) {
    static int formatVersion = 1;

    public static Structure getStructure(Dimension dimension, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
        var blockStates = new BlockState[2][sizeX][sizeY][sizeZ];
        var blockEntities = new HashMap<Vector3ic, NbtMap>();
        var entities = new ArrayList<NbtMap>();
        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startZ = z >> 4;
        var endZ = (z + sizeZ - 1) >> 4;
        for (int chunkX = startX; chunkX <= endX; chunkX++) {
            var cX = chunkX << 4;
            var localStartX = Math.max(x - cX, 0);
            var localEndX = Math.min(x + sizeX - cX, 16);
            for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                var cZ = chunkZ << 4;
                var localStartZ = Math.max(z - cZ, 0);
                var localEndZ = Math.min(z + sizeZ - cZ, 16);
                var chunk = dimension.getChunkService().getChunk(chunkX, chunkZ);
                if (chunk != null) {
                    chunk.batchProcess(c -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int globalY = y; globalY < y + sizeY; globalY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalZ = cZ + localZ;
                                    blockStates[0][globalX - x][globalY - y][globalZ - z] = c.getBlockState(localX, globalY, localZ, 0);
                                    blockStates[1][globalX - x][globalY - y][globalZ - z] = c.getBlockState(localX, globalY, localZ, 1);
                                }
                            }
                        }
                        c.getBlockEntities().forEach((hashedPos, value) -> {
                            var entityX = HashUtils.getXFromHashChunkXYZ(hashedPos) + cX;
                            var entityY = HashUtils.getYFromHashChunkXYZ(hashedPos);
                            var entityZ = HashUtils.getZFromHashChunkXYZ(hashedPos) + cZ;
                            if (startX <= entityX && endX > entityX && startZ <= entityZ && endZ > entityZ && y <= entityY && entityY < y + sizeY) {
                                blockEntities.put(new Vector3i(entityX - x, entityY - y, entityZ - z), value.saveNBT());
                            }
                        });
                        c.getEntities().forEach((runtimeID, value) -> {
                            var location = value.getLocation();
                            if (x <= location.x() && x + sizeX > location.x() && y <= location.y() && y + sizeY > location.y() && z <= location.z() && z + sizeZ > location.z()) {
                                entities.add(value.saveNBT());
                            }
                        });
                    });
                } else {
                    var structureVoid = STRUCTURE_VOID.getDefaultState();
                    for (int localX = localStartX; localX < localEndX; localX++) {
                        for (int globalY = y; globalY < y + sizeY; globalY++) {
                            for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                var globalX = cX + localX;
                                var globalZ = cZ + localZ;
                                blockStates[0][globalX - x][globalY - y][globalZ - z] = structureVoid;
                                blockStates[1][globalX - x][globalY - y][globalZ - z] = structureVoid;
                            }
                        }
                    }
                }
            }
        }
        return new Structure(blockStates, blockEntities, entities, sizeX, sizeY, sizeZ, x, y, z);
    }

    public Future<?> place(Dimension dimension, int x, int y, int z){
         return Server.getInstance().getVirtualThreadPool().submit(()-> {
            var startX = x >> 4;
            var endX = (x + sizeX - 1) >> 4;
            var startZ = z >> 4;
            var endZ = (z + sizeZ - 1) >> 4;
            for (int chunkX = startX; chunkX <= endX; chunkX++) {
                var cX = chunkX << 4;
                var localStartX = Math.max(x - cX, 0);
                var localEndX = Math.min(x + sizeX - cX, 16);
                for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                    var cZ = chunkZ << 4;
                    var localStartZ = Math.max(z - cZ, 0);
                    var localEndZ = Math.min(z + sizeZ - cZ, 16);
                    dimension.getChunkService().getOrLoadChunkSync(chunkX, chunkZ).batchProcess(c -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int globalY = y; globalY < y + sizeY; globalY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalZ = cZ + localZ;
                                    if(blocks[0][globalX - x][globalY - y][globalZ - z] != STRUCTURE_VOID.getDefaultState()) {
                                        c.setBlockState(localX, globalY, localZ, blocks[0][globalX - x][globalY - y][globalZ - z], 0);
                                    }
                                    if(blocks[1][globalX - x][globalY - y][globalZ - z] != STRUCTURE_VOID.getDefaultState()) {
                                        c.setBlockState(localX, globalY, localZ, blocks[1][globalX - x][globalY - y][globalZ - z], 0);
                                    }
                                }
                            }
                        }
                    });
                }
                for (var i : blockEntities.entrySet()) {
                    dimension.getBlockEntity(i.getKey()).loadNBT(i.getValue());
                }
                for (var i : entities) {
                    dimension.getEntityService().addEntity(EntityHelper.fromNBT(dimension,i));
                }
            }
        });
    }

    public static Structure formNbt(NbtMap nbt){
        if(nbt.getInt("format_version")!=formatVersion){
            throw new StructureException("format_version should be " + formatVersion);
        }
        if(nbt.getList("size", NbtType.INT).size() != 3){
            throw new StructureException("size of size list should be 3");
        }
        var sizeX = nbt.getList("size", NbtType.INT).get(0);
        var sizeY = nbt.getList("size", NbtType.INT).get(1);
        var sizeZ = nbt.getList("size", NbtType.INT).get(2);
        var blockIndices = nbt.getCompound("structure").getList("block_indices",NbtType.LIST);
        if(blockIndices.size() != 2){
            throw new StructureException("block_indices should have 2 layer");
        }
        if(blockIndices.get(0).size() != sizeX * sizeY * sizeZ){
            throw new StructureException("size of layer0 incorrect, it should be" + sizeX * sizeY * sizeZ);
        }
        if(blockIndices.get(1).size() != sizeX * sizeY * sizeZ){
            throw new StructureException("size of layer1 incorrect, it should be" + sizeX * sizeY * sizeZ);
        }
        var layer0 = (List<Integer>) blockIndices.get(0);
        var layer1 = (List<Integer>) blockIndices.get(1);
        var entities = nbt.getCompound("structure").getList("entities",NbtType.COMPOUND);
        var palette = nbt.getCompound("structure").getCompound("palette").getCompound("default");
        var blockEntitiesNbt = palette.getCompound("block_position_data");
        var blockPalette = palette.getList("block_palette", NbtType.COMPOUND).stream().map(
                BlockStateHelper::fromNbt
        ).toList();
        var structureVoid = STRUCTURE_VOID.getDefaultState();


        var blockStates = new BlockState[2][sizeX][sizeY][sizeZ];
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                for(int z = 0; z < sizeZ; z++){
                    if(layer0.get(indexFormPos(sizeX,sizeY,sizeZ,x,y,z)) == -1){
                        blockStates[0][x][y][z] = structureVoid;
                    }else{
                        blockStates[0][x][y][z] = blockPalette.get(layer0.get(indexFormPos(sizeX,sizeY,sizeZ,x,y,z)));
                    }
                    if(layer1.get(indexFormPos(sizeX,sizeY,sizeZ,x,y,z)) == -1){
                        blockStates[1][x][y][z] = structureVoid;
                    }else{
                        blockStates[1][x][y][z] = blockPalette.get(layer0.get(indexFormPos(sizeX,sizeY,sizeZ,x,y,z)));
                    }
                }
            }
        }
        var blockEntities = new HashMap<Vector3ic, NbtMap>();
        for (var i : blockEntitiesNbt.keySet()) {
            var pos = posFromIndex(sizeX,sizeY,sizeZ,Integer.parseInt(i));
            var entity = blockEntitiesNbt.getCompound(i);
            blockEntities.put(pos, entity);
        }

        if(nbt.getList("structure_world_origin", NbtType.INT).size() != 3){
            throw new StructureException("size of structure_world_origin list should be 3");
        }
        var x = nbt.getList("structure_world_origin", NbtType.INT).get(0);
        var y = nbt.getList("structure_world_origin", NbtType.INT).get(1);
        var z = nbt.getList("structure_world_origin", NbtType.INT).get(2);

        return new Structure(blockStates,blockEntities, entities, sizeX, sizeY, sizeZ, x,y,z);
    }

    public NbtMap toNbt(){
        var layer0 = new ArrayList<Integer>();
        var layer1 = new ArrayList<Integer>();
        var palette = new BlockStatePalette();
        for(int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    layer0.set(indexFormPos(sizeX,sizeY,sizeZ,x,y,z),palette.getIndexOf(blocks[0][x][y][z]));
                    layer1.set(indexFormPos(sizeX,sizeY,sizeZ,x,y,z),palette.getIndexOf(blocks[1][x][y][z]));
                }
            }
        }
        var blockEntitiesNbt = NbtMap.builder();
        for(var i : blockEntities.entrySet()){
            blockEntitiesNbt.putCompound(
                    Integer.toString(indexFormPos(sizeX,sizeY,sizeZ, i.getKey().x(),i.getKey().y(),i.getKey().z())),
                    i.getValue()
            );
        }

        return NbtMap.builder()
                .putInt("format_version", formatVersion)
                .putList("size", NbtType.INT, sizeX, sizeY, sizeZ)
                .putCompound("structure",
                        NbtMap.builder()
                                .putList("block_indices",NbtType.LIST,
                                        new NbtList<>(NbtType.INT,layer0),
                                        new NbtList<>(NbtType.INT,layer1)
                                        )
                                .putList("entities",NbtType.COMPOUND,entities)
                                .putCompound("palette",NbtMap.builder().putCompound(
                                        "default",NbtMap.builder()
                                                .putList("block_palette",NbtType.COMPOUND,
                                                        palette.getPalette().stream().map(BlockState::getBlockStateTag).toList())
                                                .putCompound("block_position_data",blockEntitiesNbt.build())
                                                .build()
                                ).build())
                                .build()
                )
                .putList("structure_world_origin", NbtType.INT,x,y,z)
                .build();
    }

    public static int indexFormPos(int sizeX,int sizeY,int sizeZ,int x,int y,int z) {
        return x*sizeY*sizeZ+y*sizeZ+z;
    }

    public static Vector3i posFromIndex(int sizeX,int sizeY,int sizeZ,int index) {
        return new Vector3i(index / (sizeY*sizeZ) , index % (sizeY*sizeZ) / sizeZ,index % (sizeY*sizeZ) % sizeZ);
    }

    private static class BlockStatePalette {
        @Getter
        private final List<BlockState> palette = new ArrayList<>();
        int getIndexOf(BlockState block) {
            if(block == STRUCTURE_VOID.getDefaultState()){
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
