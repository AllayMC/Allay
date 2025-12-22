package org.allaymc.api.world;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.player.Player;
import org.allaymc.api.utils.function.QuadConsumer;
import org.allaymc.api.utils.function.TriFunction;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.light.LightEngine;
import org.allaymc.api.world.manager.BlockUpdateManager;
import org.allaymc.api.world.manager.ChunkManager;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBdc;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * Represents a dimension in the world. Each world can have at most three dimensions and at least one
 * overworld dimension.
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface Dimension {

    /**
     * The return value of {@link #getLiquid(int, int, int)} if no liquid is found.
     */
    Pair<Integer, BlockState> PAIR_LIQUID_NOT_FOUND = new Pair<>(-1, null);

    /**
     * Get the chunk manager of this dimension.
     *
     * @return the chunk manager
     */
    ChunkManager getChunkManager();

    /**
     * Get the block update manager of this dimension.
     *
     * @return the block update manager
     */
    BlockUpdateManager getBlockUpdateManager();

    /**
     * Get the entity manager of this dimension.
     *
     * @return the entity manager
     */
    EntityManager getEntityManager();

    /**
     * Adds a debug shape to the dimension.
     *
     * @param debugShape the debug shape to add
     */
    void addDebugShape(DebugShape debugShape);

    /**
     * Removes a debug shape from the dimension.
     *
     * @param debugShape the debug shape to remove
     */
    void removeDebugShape(DebugShape debugShape);

    /**
     * Gets all debug shapes in the dimension.
     *
     * @return a set of debug shapes in the dimension
     */
    @UnmodifiableView
    Set<DebugShape> getDebugShapes();

    /**
     * Removes all debug shapes from the dimension.
     */
    void removeAllDebugShapes();

    /**
     * Get the light engine of this dimension.
     *
     * @return the light engine
     */
    LightEngine getLightEngine();

    /**
     * Get the dimension info of this dimension.
     *
     * @return the dimension info
     */
    DimensionInfo getDimensionInfo();

    /**
     * Get the world which contains this dimension.
     *
     * @return the world which contains this dimension
     */
    World getWorld();

    /**
     * Gets the world generator associated with this dimension.
     *
     * @return the {@code WorldGenerator} used to generate the dimension
     */
    WorldGenerator getWorldGenerator();

    /**
     * Get all the entities in this dimension.
     *
     * @return all the entities in this dimension
     */
    @UnmodifiableView
    default Map<Long, Entity> getEntities() {
        return getEntityManager().getEntities();
    }

    /**
     * Get the entity count of this dimension.
     *
     * @return the entity count of this dimension
     */
    default int getEntityCount() {
        return getEntities().size();
    }

    /**
     * Get all the players in this dimension.
     *
     * @return all the players in this dimension
     */
    @UnmodifiableView
    Set<Player> getPlayers();

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3dc pos, BlockState blockState) {
        return setBlockState(pos, blockState, 0);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3dc pos, BlockState blockState, int layer) {
        pos = pos.floor(new Vector3d());
        return setBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), blockState, layer);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(double x, double y, double z, BlockState blockState) {
        return setBlockState(x, y, z, blockState, 0);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(double x, double y, double z, BlockState blockState, int layer) {
        return setBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), blockState, layer);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState) {
        return setBlockState(x, y, z, blockState, 0, true, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState) {
        return setBlockState(pos, blockState, 0, true, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        return setBlockState(x, y, z, blockState, layer, true, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer) {
        return setBlockState(pos, blockState, layer, true, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        return setBlockState(x, y, z, blockState, layer, send, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send) {
        return setBlockState(pos, blockState, layer, send, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update) {
        return setBlockState(x, y, z, blockState, layer, send, update, true, null);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        return setBlockState(x, y, z, blockState, layer, send, update, callBlockBehavior, null);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, PlayerInteractInfo placementInfo) {
        return setBlockState(x, y, z, blockState, 0, true, true, true, placementInfo);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, PlayerInteractInfo placementInfo) {
        return setBlockState(x, y, z, blockState, layer, true, true, true, placementInfo);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int, boolean, boolean, boolean, PlayerInteractInfo)
     */
    default boolean setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo) {
        return setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior, placementInfo);
    }

    /**
     * Set the block state at the specified pos. This method will have no effect if the chunk is not loaded.
     *
     * @param x                 the x coordinate of the block
     * @param y                 the y coordinate of the block
     * @param z                 the z coordinate of the block
     * @param blockState        the block state to set
     * @param layer             the layer which contains the block
     * @param send              whether to send the block update packet
     * @param update            whether to update the blocks around the block
     * @param callBlockBehavior whether to call the block behavior
     * @param placementInfo     the placement info
     * @return whether the block state was set successfully. Return {@code false} when the block is failed to be set, usually because chunk is unloaded or event is being cancelled
     */
    boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo);

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3dc pos) {
        return getBlockState(pos, 0);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3dc pos, int layer) {
        pos = pos.floor(new Vector3d());
        return getBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), layer);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(double x, double y, double z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(double x, double y, double z, int layer) {
        return getBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), layer);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3ic pos) {
        return getBlockState(pos, 0);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(Vector3ic pos, int layer) {
        return getBlockState(pos.x(), pos.y(), pos.z(), layer);
    }

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Get the block state at the specified pos.
     *
     * @param x     the x coordinate of the block
     * @param y     the y coordinate of the block
     * @param z     the z coordinate of the block
     * @param layer the layer which contains the block
     * @return the block state at the specified pos, or {@code BlockTypes.AIR.getDefaultState()} if not found or the chunk is not loaded
     */
    default BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < this.getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight()) {
            return AIR.getDefaultState();
        }

        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return AIR.getDefaultState();
        }

        return chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    /**
     * @see #forEachBlockStates(int, int, int, int, int, int, int, QuadConsumer)
     */
    default void forEachBlockStates(AABBdc aabb, int layer, QuadConsumer<Integer, Integer, Integer, BlockState> blockStateConsumer) {
        var maxX = (int) Math.ceil(aabb.maxX());
        var maxY = (int) Math.ceil(aabb.maxY());
        var maxZ = (int) Math.ceil(aabb.maxZ());
        var minX = (int) Math.floor(aabb.minX());
        var minY = (int) Math.floor(aabb.minY());
        var minZ = (int) Math.floor(aabb.minZ());
        forEachBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer, blockStateConsumer);
    }

    /**
     * For-each the block states at the specified region. Blocks in unloaded chunks won't be iterated.
     *
     * @param x                  the start x coordinate of the region
     * @param y                  the start y coordinate of the region
     * @param z                  the start z coordinate of the region
     * @param sizeX              the size of the region in the x-axis
     * @param sizeY              the size of the region in the y-axis
     * @param sizeZ              the size of the region in the z-axis
     * @param layer              the layer which contains the block
     * @param blockStateConsumer the block state consumer. The consumer will be called with the global x, y, z coordinates of the pos, and the block state
     */
    default void forEachBlockStates(
            int x, int y, int z,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeX,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeY,
            @Range(from = 1, to = Integer.MAX_VALUE) int sizeZ,
            int layer, QuadConsumer<Integer, Integer, Integer, BlockState> blockStateConsumer) {
        var blockStates = getBlockStates(x, y, z, sizeX, sizeY, sizeZ, layer);
        if (blockStates == null) {
            return;
        }

        for (int offsetX = 0, length0 = blockStates.length; offsetX < length0; offsetX++) {
            var sub1 = blockStates[offsetX];
            for (int offsetY = 0, length1 = sub1.length; offsetY < length1; offsetY++) {
                var sub2 = sub1[offsetY];
                for (int offsetZ = 0, length2 = sub2.length; offsetZ < length2; offsetZ++) {
                    var blockState = sub2[offsetZ];
                    if (blockState == null) {
                        continue;
                    }

                    blockStateConsumer.accept(x + offsetX, y + offsetY, z + offsetZ, blockState);
                }
            }
        }
    }

    /**
     * Get the block state at the specified region.
     *
     * @param x     the start x coordinate of the region
     * @param y     the start y coordinate of the region
     * @param z     the start z coordinate of the region
     * @param sizeX the size of the region in the x-axis
     * @param sizeY the size of the region in the y-axis
     * @param sizeZ the size of the region in the z-axis
     * @param layer the layer which contains the block
     * @return the block states at the specified region, or {@code null} if sizeX/Y/Z is smaller than 1
     */
    default BlockState[][][] getBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) {
            return null;
        }

        var blockStates = new BlockState[sizeX][sizeY][sizeZ];
        var dimensionInfo = getDimensionInfo();
        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startY = y >> 4;
        var endY = (y + sizeY - 1) >> 4;
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

                var chunk = getChunkManager().getChunk(chunkX, chunkZ);
                if (chunk == null) {
                    // Chunk is not loaded.
                    continue;
                }

                for (int sectionY = startY; sectionY <= endY; sectionY++) {
                    if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
                        continue;
                    }

                    var cY = sectionY << 4;
                    var localStartY = Math.max(y - cY, 0);
                    var localEndY = Math.min(y + sizeY - cY, 16);

                    chunk.applyOperationInSection(sectionY, section -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int localY = localStartY; localY < localEndY; localY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalY = cY + localY;
                                    var globalZ = cZ + localZ;
                                    var blockState = section.getBlockState(localX, localY, localZ, layer);
                                    blockStates[globalX - x][globalY - y][globalZ - z] = blockState;
                                }
                            }
                        }
                    }, OperationType.READ, OperationType.NONE);
                }
            }
        }

        return blockStates;
    }

    /**
     * Set the block states at the specified region. Blocks in unloaded chunks won't be set.
     *
     * @param x                  the start x coordinate of the region
     * @param y                  the start y coordinate of the region
     * @param z                  the start z coordinate of the region
     * @param sizeX              the size of the region in the x-axis
     * @param sizeY              the size of the region in the y-axis
     * @param sizeZ              the size of the region in the z-axis
     * @param layer              the layer which the block will be set
     * @param blockStateSupplier the block state supplier. The supplier will be called with the global x, y, z coordinates of the pos,
     *                           and it should return the block state to set. If the supplier returns {@code null}, the block state will keep unchanged.
     */
    default void setBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer, TriFunction<Integer, Integer, Integer, BlockState> blockStateSupplier) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) {
            return;
        }

        var dimensionInfo = getDimensionInfo();
        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startY = y >> 4;
        var endY = (y + sizeY - 1) >> 4;
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

                var chunk = getChunkManager().getChunk(chunkX, chunkZ);
                if (chunk == null) {
                    // Chunk is not loaded
                    continue;
                }

                for (int sectionY = startY; sectionY <= endY; sectionY++) {
                    if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
                        continue;
                    }

                    var cY = sectionY << 4;
                    var localStartY = Math.max(y - cY, 0);
                    var localEndY = Math.min(y + sizeY - cY, 16);

                    chunk.applyOperationInSection(sectionY, section -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int localY = localStartY; localY < localEndY; localY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalY = cY + localY;
                                    var globalZ = cZ + localZ;
                                    var blockState = blockStateSupplier.apply(globalX, globalY, globalZ);
                                    if (blockState != null) {
                                        section.setBlockState(localX, localY, localZ, blockState, layer);
                                    }
                                }
                            }
                        }
                    }, OperationType.WRITE, OperationType.NONE);
                }
            }
        }
    }

    /**
     * @see #updateBlockProperty(BlockPropertyType, Object, int, int, int, int)
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z) {
        updateBlockProperty(propertyType, value, x, y, z, 0);
    }

    /**
     * @see #updateBlockProperty(BlockPropertyType, Object, Vector3ic, int)
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos) {
        updateBlockProperty(propertyType, value, pos, 0);
    }

    /**
     * @see #updateBlockProperty(BlockPropertyType, Object, int, int, int, int)
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos, int layer) {
        updateBlockProperty(propertyType, value, pos.x(), pos.y(), pos.z(), layer);
    }

    /**
     * Update a specific property of a specific block.
     *
     * @param propertyType the property type needs to be updated
     * @param value        the new property value
     * @param x            block's x coordinate
     * @param y            block's y coordinate
     * @param z            block's z coordinate
     * @param layer        the layer which contains the block
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, int layer) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return;
        }

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        var newBlockState = oldBlockState.setPropertyValue(propertyType, value);
        if (oldBlockState == newBlockState) {
            return;
        }

        chunk.setBlockState(xIndex, y, zIndex, newBlockState, layer);
    }

    /**
     * @see #getCollidingBlockStates(AABBdc, int, boolean)
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb) {
        return getCollidingBlockStates(aabb, 0);
    }

    /**
     * @see #getCollidingBlockStates(AABBdc, int, boolean)
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb, int layer) {
        return getCollidingBlockStates(aabb, layer, false);
    }

    /**
     * Get the block states that collide with the specified AABB.
     *
     * @param aabb            the AABB to check
     * @param layer           the layer which contains the block
     * @param ignoreCollision include blocks that don't have collision
     * @return the block states that collide with the specified AABB, or {@code null} if no block collides
     */
    default BlockState[][][] getCollidingBlockStates(AABBdc aabb, int layer, boolean ignoreCollision) {
        var maxX = (int) Math.ceil(aabb.maxX());
        var maxY = (int) Math.ceil(aabb.maxY());
        var maxZ = (int) Math.ceil(aabb.maxZ());
        var minX = (int) Math.floor(aabb.minX());
        var minY = (int) Math.floor(aabb.minY());
        var minZ = (int) Math.floor(aabb.minZ());
        var blockStates = new BlockState[maxX - minX][maxY - minY][maxZ - minZ];
        AtomicBoolean notEmpty = new AtomicBoolean(false);
        forEachBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer, (globalX, globalY, globalZ, blockState) -> {
            var blockStateData = blockState.getBlockStateData();
            if (ignoreCollision) {
                blockStates[globalX - minX][globalY - minY][globalZ - minZ] = blockState;
            } else if (blockStateData.hasCollision() && blockStateData.collisionShape().translate(globalX, globalY, globalZ).intersectsAABB(aabb)) {
                blockStates[globalX - minX][globalY - minY][globalZ - minZ] = blockState;
                notEmpty.set(true);
            }
        });
        return ignoreCollision || notEmpty.get() ? blockStates : null;
    }

    /**
     * @see #updateAroundIgnoreFace(Vector3ic, BlockFace...)
     */
    default void updateAroundIgnoreFace(int x, int y, int z, BlockFace... ignoreFaces) {
        updateAroundIgnoreFace(new Vector3i(x, y, z), ignoreFaces);
    }

    /**
     * Update the blocks around a pos, ignoring some faces.
     *
     * @param pos         the specified pos
     * @param ignoreFaces the faces to ignore
     */
    default void updateAroundIgnoreFace(Vector3ic pos, BlockFace... ignoreFaces) {
        for (var face : BlockFace.values()) {
            if (ignoreFaces != null && ignoreFaces.length > 0) {
                var ignore = Arrays.stream(ignoreFaces).anyMatch(ignoreFace -> ignoreFace == face);
                if (ignore) continue;
            }

            updateAtFace(pos, face);
        }
    }

    /**
     * @see #updateAround(Vector3ic)
     */
    default void updateAround(int x, int y, int z) {
        for (var face : BlockFace.values()) updateAtFace(x, y, z, face);
    }

    /**
     * Update the blocks around a block.
     *
     * @param pos the pos where the block is in
     */
    default void updateAround(Vector3ic pos) {
        for (var face : BlockFace.values()) updateAtFace(pos, face);
    }

    /**
     * Update the block which is at the specified face of the specified block.
     *
     * @param x    the x coordinate of the block
     * @param y    the y coordinate of the block
     * @param z    the z coordinate of the block
     * @param face the face of the block
     */
    default void updateAtFace(int x, int y, int z, BlockFace face) {
        updateAtFace(new Vector3i(x, y, z), face);
    }

    /**
     * Update the block which is at the specified face of the specified block.
     *
     * @param pos  the pos of the block
     * @param face the face of the block
     */
    default void updateAtFace(Vector3ic pos, BlockFace face) {
        var offsetPos = face.offsetPos(pos);
        getBlockUpdateManager().neighborBlockUpdate(offsetPos, pos, face.opposite());
    }

    /**
     * Get blocks around a pos.
     *
     * @param pos the pos
     * @return the blocks around the pos
     */
    default Block[] getNeighborsBlocks(Vector3ic pos) {
        return getNeighborsBlocks(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get blocks around a pos.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the blocks around the pos
     */
    default Block[] getNeighborsBlocks(int x, int y, int z) {
        var result = new Block[6];
        for (int i = 0; i < BlockFace.values().length; i++) {
            var offsetPos = BlockFace.values()[i].offsetPos(x, y, z);
            var neighborBlockState = getBlockState(offsetPos.x(), offsetPos.y(), offsetPos.z(), 0);
            result[i] = new Block(neighborBlockState, new Position3i(offsetPos, this), 0);
        }
        return result;
    }

    /**
     * Check if the y coordinate is in the range of this dimension.
     *
     * @param y the y coordinate
     * @return {@code true} if the y coordinate is in the range of this dimension, otherwise {@code false}.
     */
    default boolean isYInRange(double y) {
        return y >= getDimensionInfo().minHeight() && y <= getDimensionInfo().maxHeight();
    }

    /**
     * Check if the specified pos is in a valid and loaded region.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return {@code true} if the pos is in a valid and loaded region, otherwise {@code false}.
     */
    default boolean isInWorld(double x, double y, double z) {
        return isYInRange(y) && getChunkManager().isChunkLoaded((int) x >> 4, (int) z >> 4);
    }

    /**
     * Check if the aabb is in a valid and loaded region.
     *
     * @param aabb the aabb
     * @return {@code true} if the aabb is in a valid and loaded region, otherwise {@code false}.
     */
    default boolean isAABBInWorld(AABBdc aabb) {
        return isInWorld(aabb.maxX(), aabb.maxY(), aabb.maxZ()) && isInWorld(aabb.minX(), aabb.minY(), aabb.minZ());
    }

    /**
     * Get the block entity at the specified pos.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the block entity at the specified pos. {@code null} will be returned if the block entity is not found or
     * the chunk is not loaded
     */
    default BlockEntity getBlockEntity(int x, int y, int z) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return null;
        }

        return chunk.getBlockEntity(x & 15, y, z & 15);
    }

    /**
     * @see #getBlockEntity(int, int, int)
     */
    default BlockEntity getBlockEntity(Vector3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get all block entities in this dimension.
     *
     * @return a map of block entities, where the key is the block entity ID and the value is the block entity
     */
    @Unmodifiable
    default Map<Integer, BlockEntity> getBlockEntities() {
        return getChunkManager().getLoadedChunks().stream()
                .flatMap(chunk -> chunk.getBlockEntities().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Get the count of block entities in this dimension.
     *
     * @return the count of block entities in this dimension
     */
    default int getBlockEntityCount() {
        return getChunkManager().getLoadedChunks().stream().mapToInt(chunk -> chunk.getBlockEntities().size()).sum();
    }

    /**
     * @see #addBlockAction(int, int, int, BlockAction)
     */
    default void addBlockAction(Vector3ic pos, BlockAction action) {
        addBlockAction(pos.x(), pos.y(), pos.z(), action);
    }

    /**
     * Adds a block action at the pos passed.
     *
     * @param x      the x coordinate of the pos
     * @param y      the y coordinate of the pos
     * @param z      the z coordinate of the pos
     * @param action the action
     */
    default void addBlockAction(int x, int y, int z, BlockAction action) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewBlockAction(new Vector3i(x, y, z), action);
                }
            });
        }
    }

    /**
     * @see #addParticle(double, double, double, Particle)
     */
    default void addParticle(Vector3ic pos, Particle particle) {
        addParticle(pos.x(), pos.y(), pos.z(), particle);
    }

    /**
     * @see #addParticle(double, double, double, Particle)
     */
    default void addParticle(Vector3dc pos, Particle particle) {
        addParticle(pos.x(), pos.y(), pos.z(), particle);
    }

    /**
     * Adds a particle at the specified position.
     *
     * @param x        the x-coordinate of the particle
     * @param y        the y-coordinate of the particle
     * @param z        the z-coordinate of the particle
     * @param particle the particle to be added
     */
    default void addParticle(double x, double y, double z, Particle particle) {
        var chunk = getChunkManager().getChunkByDimensionPos((int) x, (int) z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewParticle(particle, new Vector3d(x, y, z));
                }
            });
        }
    }

    /**
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3dc pos, Sound sound) {
        addSound(pos, sound, true);
    }

    /**
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3dc pos, Sound sound, boolean relative) {
        addSound(pos.x(), pos.y(), pos.z(), sound, relative);
    }

    /**
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3ic pos, Sound sound) {
        addSound(pos, sound, true);
    }

    /**
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(Vector3ic pos, Sound sound, boolean relative) {
        addSound(pos.x(), pos.y(), pos.z(), sound, relative);
    }

    /**
     * @see #addSound(double, double, double, Sound, boolean)
     */
    default void addSound(double x, double y, double z, Sound sound) {
        addSound(x, y, z, sound, true);
    }

    /**
     * Add a sound at the specified pos.
     *
     * @param x        the x coordinate of the pos
     * @param y        the y coordinate of the pos
     * @param z        the z coordinate of the pos
     * @param sound    the sound
     * @param relative whether the sound is relative. If set to {@code false}, the sound will have full volume,
     *                 regardless of where the Position is, whereas if set to {@code true}, the sound's volume
     *                 will be based on the distance to the pos passed
     */
    default void addSound(double x, double y, double z, Sound sound, boolean relative) {
        var chunk = getChunkManager().getChunkByDimensionPos((int) x, (int) z);
        if (chunk != null) {
            chunk.forEachChunkLoaders(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    player.getController().viewSound(sound, new Vector3d(x, y, z), relative);
                }
            });
        }
    }

    /**
     * @see #dropItem(ItemStack, Vector3dc, Vector3dc, int)
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos) {
        var rand = ThreadLocalRandom.current();
        dropItem(itemStack, pos, new Vector3d(rand.nextDouble(0.2) - 0.1, 0.2, rand.nextDouble(0.2) - 0.1));
    }

    /**
     * @see #dropItem(ItemStack, Vector3dc, Vector3dc, int)
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos, Vector3dc motion) {
        dropItem(itemStack, pos, motion, 10);
    }

    /**
     * Drop an item at the specified pos with the specified motion and pickup delay.
     *
     * @param itemStack   the item stack to drop
     * @param pos         the pos to drop the item
     * @param motion      the motion of the item entity
     * @param pickupDelay the pickup delay of the item entity
     */
    default void dropItem(ItemStack itemStack, Vector3dc pos, Vector3dc motion, int pickupDelay) {
        var entityItem = EntityTypes.ITEM.createEntity(
                EntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        .build()
        );
        entityItem.setItemStack(itemStack);
        entityItem.setPickupDelay(pickupDelay);
        getEntityManager().addEntity(entityItem);
    }

    /**
     * Drop a specified amount of xp orbs at the specified pos.
     * <p>
     * This method will split the xp into multiple xp orbs before dropping,
     * which means that it won't drop a single xp orb with a large amount of xp.
     *
     * @param pos the pos to drop the xp orbs
     * @param xp  the amount of xp to drop
     */
    default void splitAndDropXpOrb(Vector3dc pos, int xp) {
        for (var split : EntityXpOrb.splitIntoOrbSizes(xp)) {
            dropXpOrb(pos, split);
        }
    }

    /**
     * Drop a xp orb at the specified pos with the specified xp amount.
     * This method will add a random motion to the xp orb entity.
     *
     * @param pos the pos to drop the xp orb
     * @param xp  the amount of xp to drop
     */
    default void dropXpOrb(Vector3dc pos, int xp) {
        var rand = ThreadLocalRandom.current();
        var motion = new Vector3d(
                (rand.nextDouble() * 0.2 - 0.1) * 2,
                rand.nextDouble() * 0.4,
                (rand.nextDouble() * 0.2 - 0.1) * 2
        );
        dropXpOrb(pos, xp, motion);
    }

    /**
     * Drop a xp orb at the specified pos with the specified xp amount and motion. pickupDelay will be 10.
     *
     * @param pos    the pos to drop the xp orb
     * @param xp     the amount of xp to drop
     * @param motion the motion of the xp orb entity
     */
    default void dropXpOrb(Vector3dc pos, int xp, Vector3dc motion) {
        dropXpOrb(pos, xp, motion, 10);
    }

    /**
     * Drop a xp orb at the specified pos with the specified xp amount, motion and pickup delay.
     *
     * @param pos         the pos to drop the xp orb
     * @param xp          the amount of xp to drop
     * @param motion      the motion of the xp orb entity
     * @param pickupDelay the pickup delay of the xp orb entity
     */
    default void dropXpOrb(Vector3dc pos, int xp, Vector3dc motion, int pickupDelay) {
        var rand = ThreadLocalRandom.current();
        var entityXpOrb = EntityTypes.XP_ORB.createEntity(
                EntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        .rot(rand.nextDouble() * 360, 0)
                        .build()
        );
        entityXpOrb.setExperienceValue(xp);
        entityXpOrb.setPickupDelay(pickupDelay);
        getEntityManager().addEntity(entityXpOrb);
    }

    /**
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos) {
        return breakBlock(pos.x(), pos.y(), pos.z(), null, null);
    }

    /**
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos, ItemStack usedItem, Entity entity) {
        return breakBlock(pos.x(), pos.y(), pos.z(), usedItem, entity);
    }

    /**
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(int x, int y, int z) {
        return breakBlock(x, y, z, null, null);
    }

    /**
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity) {
        return breakBlock(x, y, z, usedItem, entity, true);
    }

    /**
     * @see #breakBlock(int, int, int, ItemStack, Entity, boolean)
     */
    default boolean breakBlock(Vector3ic pos, ItemStack usedItem, Entity entity, boolean hasParticle) {
        return breakBlock(pos.x(), pos.y(), pos.z(), usedItem, entity, hasParticle);
    }

    /**
     * Break a block at the specified position.
     *
     * @param x            The x coordinate of the block
     * @param y            The y coordinate of the block
     * @param z            The z coordinate of the block
     * @param usedItem     The item used to break the block, can be {@code null}
     * @param entity       The player who breaks the block, can be {@code null}
     * @param sendParticle Whether to send the break particle
     * @return Whether the block is successfully broken
     */
    boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity, boolean sendParticle);

    /**
     * Get the height of the highest non-air block at the specified x and z coordinates.
     * If there are no blocks in the (x, z) position, the height will be the min height of the current dimension
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @return the height of the highest non-air block at the specified x and z coordinates
     */
    default int getHeight(int x, int z) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return getDimensionInfo().minHeight();
        }

        return chunk.getHeight(x & 15, z & 15);
    }

    /**
     * Get the highest block at the specified x and z coordinates.
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @return the highest blockstate at the specified x and z coordinates
     */
    default BlockState getHighestBlockState(int x, int z) {
        return getBlockState(x, getHeight(x, z), z);
    }

    /**
     * @see #findSuitableGroundPosAround(Predicate, int, int, int, int)
     */
    default Vector3ic findSuitableGroundPosAround(Predicate<Position3ic> predicate, int x, int z, @Range(from = 0, to = Integer.MAX_VALUE) int range) {
        return findSuitableGroundPosAround(predicate, x, z, range, 10);
    }

    /**
     * Find a safe standing position around the specified x and z coordinates. This method
     * will wait the necessary chunks being loaded and will block the current thread.
     *
     * @param x     the x coordinate
     * @param z     the z coordinate
     * @param range the range to search
     * @return a safe standing position around the specified x and z coordinates, or {@code null} if not found
     */
    default Vector3ic findSuitableGroundPosAround(Predicate<Position3ic> predicate, int x, int z, @Range(from = 0, to = Integer.MAX_VALUE) int range, @Range(from = 0, to = Integer.MAX_VALUE) int attemptCount) {
        var rand = ThreadLocalRandom.current();
        while (attemptCount > 0) {
            attemptCount--;
            var px = x + rand.nextInt(-range, range + 1);
            var pz = z + rand.nextInt(-range, range + 1);
            // Load the chunk if it is not loaded
            getChunkManager().getOrLoadChunk(px >> 4, pz >> 4).join();
            var py = getHeight(px, pz) + 1;
            if (predicate.test(new Position3i(px, py, pz, this))) {
                return new Vector3i(px, py, pz);
            }
        }
        return null;
    }

    /**
     * @see #canPosSeeSky(int, int, int)
     */
    default boolean canPosSeeSky(Vector3ic pos) {
        return canPosSeeSky(pos.x(), pos.y(), pos.z());
    }

    /**
     * Check if the specified pos can see the sky.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return {@code true} if the specified pos can see the sky, otherwise {@code false}.
     */
    default boolean canPosSeeSky(int x, int y, int z) {
        return getHeight(x, z) <= y;
    }

    /**
     * @see #getBiome(int, int, int)
     */
    default BiomeType getBiome(Vector3ic pos) {
        return getBiome(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the biome at the specified pos.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return the biome at the specified pos.{@link BiomeTypes#PLAINS} will be returned if the y coordinate is out of the valid range of this dimension or the chunk is not loaded
     */
    default BiomeType getBiome(int x, int y, int z) {
        if (y < this.getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight())
            return BiomeTypes.PLAINS;

        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return BiomeTypes.PLAINS;
        }

        return chunk.getBiome(x & 15, y, z & 15);
    }

    /**
     * @see #setBiome(int, int, int, BiomeType)
     */
    default void setBiome(Vector3ic pos, BiomeType biome) {
        setBiome(pos.x(), pos.y(), pos.z(), biome);
    }

    /**
     * Set the biome at the specified pos. This method will have no effect if the chunk is not loaded.
     *
     * @param x     the x coordinate of the pos
     * @param y     the y coordinate of the pos
     * @param z     the z coordinate of the pos
     * @param biome the biome to set
     */
    default void setBiome(int x, int y, int z, BiomeType biome) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return;
        }

        chunk.setBiome(x & 15, y, z & 15, biome);
    }

    /**
     * @see #getLiquid(Vector3ic)
     */
    default Pair<Integer, BlockState> getLiquid(int x, int y, int z) {
        return getLiquid(new Vector3i(x, y, z));
    }

    /**
     * Attempts to return a liquid block at the position passed. This
     * liquid may be in the foreground or in any other layer. If found,
     * the liquid is returned. If not, the boolean returned is false.
     *
     * @param pos the position to check for a liquid block
     * @return the liquid block at the position and the layer it is in, or {@link #PAIR_LIQUID_NOT_FOUND} if no liquid is found
     */
    default Pair<Integer, BlockState> getLiquid(Vector3ic pos) {
        var layer0 = getBlockState(pos);
        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new Pair<>(0, layer0);
        }

        if (!layer0.getBlockStateData().canContainLiquid()) {
            return PAIR_LIQUID_NOT_FOUND;
        }

        var layer1 = getBlockState(pos, 1);
        if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
            return new Pair<>(1, layer1);
        }

        return PAIR_LIQUID_NOT_FOUND;
    }

    /**
     * @see #setLiquid(Vector3ic, BlockState)
     */
    default void setLiquid(int x, int y, int z, BlockState liquid) {
        setLiquid(new Vector3i(x, y, z), liquid);
    }

    /**
     * Set a liquid at a specific position in the dimension. Unlike {@link Dimension#setBlockState},
     * this method will not necessarily overwrite any existing blocks. It
     * will instead be in the same position as a block currently there, unless
     * there already is a liquid at that position, in which case it will be
     * overwritten. If null is passed for the liquid, any liquid currently present
     * will be removed.
     *
     * @param pos    the position to set the Liquid at
     * @param liquid the liquid to set at the position, or {@code null} to remove any liquid
     */
    default void setLiquid(Vector3ic pos, BlockState liquid) {
        if (!isInWorld(pos.x(), pos.y(), pos.z())) {
            return;
        }

        if (liquid == null) {
            removeLiquid(pos);
            return;
        }

        var existingBlockState = getBlockState(pos);
        if (!existingBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            var blockStateData = existingBlockState.getBlockStateData();
            if (!(isSource(liquid) ? blockStateData.canContainLiquidSource() : blockStateData.canContainLiquid())) {
                return;
            }
        }

        if (removeLiquid(pos)) {
            setBlockState(pos, liquid, 0);
        } else {
            setBlockState(pos, liquid, 1);
        }
    }

    /**
     * @see #removeLiquid(Vector3ic)
     */
    default boolean removeLiquid(int x, int y, int z) {
        return removeLiquid(new Vector3i(x, y, z));
    }

    /**
     * Remove any liquid blocks that may be present at a specific
     * block position. The bool returned specifies if no blocks
     * were left on layer 0.
     *
     * @param pos the position to remove the liquid from
     * @return {@code true} if no blocks were left on layer 0.
     */
    default boolean removeLiquid(Vector3ic pos) {
        var layer0 = getBlockState(pos);
        if (layer0.getBlockType() == AIR) {
            return true;
        }

        if (layer0.getBehavior() instanceof BlockLiquidBaseComponent) {
            setBlockState(pos, AIR.getDefaultState());
            return true;
        }

        if (layer0.getBlockStateData().canContainLiquid()) {
            var layer1 = getBlockState(pos, 1);
            if (layer1.getBehavior() instanceof BlockLiquidBaseComponent) {
                setBlockState(pos, AIR.getDefaultState(), 1);
                return false;
            }
        }

        return false;
    }
}
