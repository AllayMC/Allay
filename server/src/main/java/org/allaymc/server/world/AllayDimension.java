package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.server.network.processor.login.SetLocalPlayerAsInitializedPacketProcessor;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.light.AllayLightEngine;
import org.allaymc.server.world.manager.AllayBlockUpdateManager;
import org.allaymc.server.world.manager.AllayChunkManager;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.jctools.maps.NonBlockingHashSet;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.AIR;

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
        this.lightEngine = new AllayLightEngine(this);
        this.players = new NonBlockingHashSet<>();
        this.debugShapes = new NonBlockingHashSet<>();
        worldGenerator.setDimension(this);
    }

    public void startTick() {
        this.lightEngine.startTick();
    }

    public void tick(long currentTick) {
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
            blockState.getBehavior().onPlace(oldBlock, blockState, placementInfo);
            oldBlockState.getBehavior().onReplace(oldBlock, blockState, placementInfo);
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
    public String toString() {
        return "world=" + this.world.getName() + " dimId=" + this.dimensionInfo.dimensionId();
    }
}
