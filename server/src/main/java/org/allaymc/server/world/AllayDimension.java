package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.eventbus.event.block.BlockPlaceEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.Player;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.server.network.processor.login.SetLocalPlayerAsInitializedPacketProcessor;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.light.AllayLightEngine;
import org.allaymc.server.world.manager.AllayBlockUpdateManager;
import org.allaymc.server.world.manager.AllayChunkManager;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.jctools.maps.NonBlockingHashSet;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Collections;
import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.block.type.BlockTypes.POWERED_COMPARATOR;
import static org.allaymc.api.block.type.BlockTypes.POWERED_REPEATER;
import static org.allaymc.api.block.type.BlockTypes.UNPOWERED_COMPARATOR;
import static org.allaymc.api.block.type.BlockTypes.UNPOWERED_REPEATER;

/**
 * @author daoge_cmd | Cool_Loong
 */
@Slf4j
@Getter
public class AllayDimension implements Dimension {

    protected final AllayWorld world;
    protected final DimensionInfo dimensionInfo;
    protected final WorldGenerator worldGenerator;
    protected final AllayChunkManager chunkManager;
    protected final AllayEntityManager entityManager;
    protected final AllayBlockUpdateManager blockUpdateManager;
    protected final Scheduler scheduler;
    protected final AllayLightEngine lightEngine;
    protected final Set<Player> players;
    protected final Set<DebugShape> debugShapes;

    public AllayDimension(AllayWorld world, WorldGenerator worldGenerator, DimensionInfo dimensionInfo) {
        this.world = world;
        this.dimensionInfo = dimensionInfo;
        this.worldGenerator = worldGenerator;
        this.chunkManager = new AllayChunkManager(this, worldGenerator, world.getWorldStorage());
        this.entityManager = new AllayEntityManager(this, world.getWorldStorage());
        this.blockUpdateManager = new AllayBlockUpdateManager(this);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
        this.lightEngine = new AllayLightEngine(this);
        this.players = new NonBlockingHashSet<>();
        this.debugShapes = new NonBlockingHashSet<>();
        worldGenerator.setDimension(this);
    }

    public void startTick() {
        this.lightEngine.startTick();
    }

    public void tick(long currentTick) {
        this.scheduler.tick();
        this.entityManager.tick(currentTick);
        this.chunkManager.tick(currentTick);
        this.blockUpdateManager.tick();
        this.chunkManager.forEachLoadedChunks(c -> {
            var chunk = (AllayUnsafeChunk) c.toUnsafeChunk();
            chunk.sendBlockUpdates();
            chunk.performChunkTasks();
        });
    }

    public void shutdown() {
        this.scheduler.shutdown();
        // Shutdown light service first, because when unloading chunks, chunk service
        // will send updates to light service which is meaningless
        this.lightEngine.shutdown();
        this.chunkManager.shutdown();
        // EntityService should be shutdown after chunk service, because it requires
        // the callback AllayEntityService.onChunkUnload() to be called
        this.entityManager.shutdown();
    }

    public void addPlayer(Player player) {
        addPlayer(player, () -> {
        });
    }

    public void addPlayer(Player player, Runnable runnable) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        this.players.add(player);
        this.chunkManager.addChunkLoader(entity);
        this.entityManager.addEntity(entity, runnable);
        if (player.getClientState() == ClientState.IN_GAME) {
            // Only send debug shapes to the players when they are in-game. This
            // solves the issue that debug shapes won't be displayed if the player
            // haven't fully joined. When the player join, the debug shapes will be
            // sent to the player after the player is fully joined.
            // See SetLocalPlayerAsInitializedPacketProcessor.
            addDebugShapesTo(player);
        }
    }

    /**
     * Set this method to public because it is used in {@link SetLocalPlayerAsInitializedPacketProcessor}
     */
    public void addDebugShapesTo(WorldViewer viewer) {
        for (var debugShape : debugShapes) {
            // Let's send all the debug shapes in one packet to improve performance
            debugShape.addViewer(viewer, false);
        }
        viewer.viewDebugShapes(debugShapes);
    }

    public void removePlayer(Player player) {
        removePlayer(player, () -> {
        });
    }

    public void removePlayer(Player player, Runnable runnable) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        if (entity.isSpawned()) {
            // When the player respawns to another dimension after death, the player entity has already been unloaded
            // Therefore, when unloading the player entity, we need to check if the player entity has been spawned
            this.entityManager.removeEntity(entity, runnable);
            this.chunkManager.removeChunkLoader(entity);
            this.players.remove(player);
        } else {
            this.chunkManager.removeChunkLoader(entity);
            this.players.remove(player);
            // Run the callback directly
            runnable.run();
        }
        removeDebugShapesFrom(player);
    }

    protected void removeDebugShapesFrom(WorldViewer viewer) {
        for (var debugShape : debugShapes) {
            // Let's send all the remove notices in one packet to improve performance
            debugShape.removeViewer(viewer, false);
        }
        viewer.removeDebugShapes(debugShapes);
    }

    @Override
    @UnmodifiableView
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    @Override
    public void addDebugShape(DebugShape debugShape) {
        this.debugShapes.add(debugShape);
        for (var player : this.players) {
            debugShape.addViewer(player);
        }
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
        this.debugShapes.remove(debugShape);
        for (var player : this.players) {
            debugShape.removeViewer(player);
        }
    }

    @Override
    @UnmodifiableView
    public Set<DebugShape> getDebugShapes() {
        return Collections.unmodifiableSet(this.debugShapes);
    }

    @Override
    public void removeAllDebugShapes() {
        for (var player : players) {
            removeDebugShapesFrom(player);
        }
        this.debugShapes.clear();
    }

    @Override
    public boolean setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo) {
        var chunk = getChunkManager().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return false;
        }

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        var event = new BlockPlaceEvent(new Block(blockState, new Position3i(x, y, z, this), layer), oldBlockState, placementInfo);
        if (!event.call()) {
            return false;
        }

        var blockPos = new Position3i(x, y, z, this);
        var oldBlock = new Block(oldBlockState, blockPos, layer);
        if (callBlockBehavior) {
            // onReplace must be called before onPlace to ensure the old block entity
            // is removed before the new one is created. This prevents block entity type
            // mismatch issues (e.g., when a piston replaces MovingBlock with the original block)
            oldBlockState.getBehavior().onReplace(oldBlock, blockState, placementInfo);
            blockState.getBehavior().onPlace(oldBlock, blockState, placementInfo);
        }
        chunk.setBlockState(xIndex, y, zIndex, blockState, layer, send);

        if (update) {
            updateAround(x, y, z);
        }

        if (callBlockBehavior) {
            chunk.getBlockState(xIndex, y, zIndex, layer == 0 ? 1 : 0).getBehavior().afterNeighborLayerReplace(oldBlock, blockState, placementInfo);
            blockState.getBehavior().afterPlaced(oldBlock, blockState, placementInfo);
            oldBlockState.getBehavior().afterReplaced(oldBlock, blockState, placementInfo);
        }

        return true;
    }

    @Override
    public boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity, boolean sendParticle) {
        var block = getBlockState(x, y, z);
        if (block.getBlockType() == AIR) {
            return false;
        }

        var event = new BlockBreakEvent(
                new Block(block, new Position3i(x, y, z, this), 0),
                usedItem, entity
        );
        if (!event.call()) {
            return false;
        }

        if (sendParticle) {
            addParticle(x + 0.5f, y + 0.5f, z + 0.5f, new BlockBreakParticle(block));
        }

        block.getBehavior().onBreak(
                new Block(block, new Position3i(x, y, z, this), 0),
                usedItem, entity
        );

        setBlockState(x, y, z, AIR.getDefaultState());

        if (entity instanceof EntityPlayer player) {
            player.exhaust(0.005f);
        }

        return true;
    }

    @Override
    public int getPowerAt(Vector3ic pos) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = this.getBlockState(neighborPos);

            // Check direct weak power from neighbor
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, this));
            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            // Check strong power through solid blocks
            // Exclude face.opposite() because that's the direction pointing back to pos
            if (neighborState.getBlockStateData().isSolid()) {
                int strongPower = this.getStrongPowerAt(neighborPos, face.opposite());
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    @Override
    public int getStrongPowerAt(Vector3ic pos, BlockFace... excludeFaces) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            if (excludeFaces.length > 0 && containsFace(excludeFaces, face)) continue;

            Vector3ic checkPos = face.offsetPos(pos);
            BlockState state = this.getBlockState(checkPos);

            Block checkBlock = new Block(state, new Position3i(checkPos, this));
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }

        return maxPower;
    }

    @Override
    public int getWeakPowerAt(Vector3ic pos, BlockFace... excludeFaces) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            if (excludeFaces.length > 0 && containsFace(excludeFaces, face)) continue;

            Vector3ic checkPos = face.offsetPos(pos);
            BlockState state = this.getBlockState(checkPos);

            Block checkBlock = new Block(state, new Position3i(checkPos, this));
            int weakPower = state.getBehavior().getWeakPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, weakPower);
        }

        return maxPower;
    }

    private static boolean containsFace(BlockFace[] faces, BlockFace target) {
        for (BlockFace face : faces) {
            if (face == target) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateComparatorOutputLevel(int x, int y, int z) {
        var sourcePos = new Vector3i(x, y, z);
        // Check horizontal neighbors for diodes
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            var offset = face.getOffset();
            int nx = x + offset.x();
            int ny = y + offset.y();
            int nz = z + offset.z();

            if (!getChunkManager().isChunkLoaded(nx >> 4, nz >> 4)) {
                continue;
            }

            BlockState neighborState = getBlockState(nx, ny, nz);
            var neighborPos = new Vector3i(nx, ny, nz);

            if (isDiode(neighborState)) {
                // Direct neighbor is a diode, update it
                blockUpdateManager.neighborBlockUpdate(neighborPos, sourcePos, face.opposite());
            } else if (neighborState.getBlockStateData().isSolid()) {
                // Neighbor is solid, check if there's a diode on the other side
                int nx2 = nx + offset.x();
                int nz2 = nz + offset.z();

                if (!getChunkManager().isChunkLoaded(nx2 >> 4, nz2 >> 4)) {
                    continue;
                }

                BlockState behindState = getBlockState(nx2, ny, nz2);
                if (isDiode(behindState)) {
                    var behindPos = new Vector3i(nx2, ny, nz2);
                    blockUpdateManager.neighborBlockUpdate(behindPos, neighborPos, face.opposite());
                }
            }
        }
    }

    /**
     * Checks if a block state is a redstone diode (repeater or comparator).
     */
    private boolean isDiode(BlockState state) {
        var type = state.getBlockType();
        return type == POWERED_REPEATER ||
               type == UNPOWERED_REPEATER ||
               type == POWERED_COMPARATOR ||
               type == UNPOWERED_COMPARATOR;
    }

    @Override
    public String toString() {
        return "world=" + this.world.getName() + " dimId=" + this.dimensionInfo.dimensionId();
    }
}
