package org.allaymc.server.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.network.processor.impl.login.SetLocalPlayerAsInitializedPacketProcessor;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityService;
import org.allaymc.server.world.service.AllayLightService;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerScriptDebugDrawerPacket;
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
    protected final AllayChunkService chunkService;
    protected final AllayEntityService entityService;
    protected final AllayBlockUpdateService blockUpdateService;
    protected final AllayLightService lightService;
    protected final Set<EntityPlayer> players;
    protected final Set<DebugShape> debugShapes;

    public AllayDimension(AllayWorld world, WorldGenerator worldGenerator, DimensionInfo dimensionInfo) {
        this.world = world;
        this.dimensionInfo = dimensionInfo;
        worldGenerator.setDimension(this);
        this.chunkService = new AllayChunkService(this, worldGenerator, world.getWorldStorage());
        this.entityService = new AllayEntityService(this, world.getWorldStorage());
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.lightService = new AllayLightService(this);
        this.players = new NonBlockingHashSet<>();
        this.debugShapes = new NonBlockingHashSet<>();
    }

    public void startTick() {
        this.lightService.startTick();
        this.chunkService.startTick();
    }

    public void tick(long currentTick) {
        // There may be new chunk packets during sleeping, let's send them first
        this.chunkService.sendChunkPackets();

        // Ticking
        this.entityService.tick(currentTick);
        this.chunkService.tick(currentTick);
        this.blockUpdateService.tick();

        // Send the new chunk packets again after most of the work is done
        this.chunkService.sendChunkPackets();
    }

    public void shutdown() {
        // Shutdown light service first, because when unloading chunks, chunk service
        // will send updates to light service which is meaningless
        this.lightService.shutdown();
        this.chunkService.unloadAllChunks().join();
        // EntityService should be shutdown after chunk service, because it requires
        // the callback AllayEntityService.onChunkUnload() to be called
        this.entityService.shutdown();
    }

    @Override
    public void addPlayer(EntityPlayer player, Runnable runnable) {
        this.players.add(player);
        this.chunkService.addChunkLoader(player);
        this.entityService.addEntity(player, runnable);
        if (player.getClientStatus() == ClientStatus.IN_GAME) {
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
    public void addDebugShapesTo(EntityPlayer player) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            // Let's send all the debug shapes in one packet to improve performance
            debugShape.addViewer(player, false);
            packet.getShapes().add(debugShape.toNetworkData());
        }
        player.sendPacket(packet);
    }

    @Override
    public void removePlayer(EntityPlayer player, Runnable runnable) {
        if (player.isSpawned()) {
            // When the player respawns to another dimension after death, the player entity has already been unloaded
            // Therefore, when unloading the player entity, we need to check if the player entity has been spawned
            this.entityService.removeEntity(player, runnable);
            this.chunkService.removeChunkLoader(player);
            this.players.remove(player);
        } else {
            this.chunkService.removeChunkLoader(player);
            this.players.remove(player);
            // Run the callback directly
            runnable.run();
        }
        removeDebugShapesFrom(player);
    }

    protected void removeDebugShapesFrom(EntityPlayer player) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            // Let's send all the remove notices in one packet to improve performance
            debugShape.removeViewer(player, false);
            packet.getShapes().add(debugShape.createRemovalNotice());
        }
        player.sendPacket(packet);
    }

    @Override
    @UnmodifiableView
    public Set<EntityPlayer> getPlayers() {
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
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo) {
        var chunk = getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return;
        }

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        var blockPos = new Position3i(x, y, z, this);
        var oldBlockStateWithPos = new BlockStateWithPos(oldBlockState, blockPos, layer);
        if (callBlockBehavior) {
            blockState.getBehavior().onPlace(oldBlockStateWithPos, blockState, placementInfo);
            oldBlockState.getBehavior().onReplace(oldBlockStateWithPos, blockState, placementInfo);
        }
        chunk.setBlockState(xIndex, y, zIndex, blockState, layer, send);

        if (update) {
            updateAround(x, y, z);
        }

        if (callBlockBehavior) {
            chunk.getBlockState(xIndex, y, zIndex, layer == 0 ? 1 : 0).getBehavior().afterNeighborLayerReplace(oldBlockStateWithPos, blockState, placementInfo);
            blockState.getBehavior().afterPlaced(oldBlockStateWithPos, blockState, placementInfo);
            oldBlockState.getBehavior().afterReplaced(oldBlockStateWithPos, blockState, placementInfo);
        }
    }

    @Override
    public boolean breakBlock(int x, int y, int z, ItemStack usedItem, Entity entity, boolean sendParticle) {
        var block = getBlockState(x, y, z);
        if (block.getBlockType() == AIR) {
            return false;
        }

        var event = new BlockBreakEvent(
                new BlockStateWithPos(block, new Position3i(x, y, z, this), 0),
                usedItem, entity
        );
        if (!event.call()) {
            return false;
        }

        if (sendParticle) {
            var pk = new LevelEventPacket();
            pk.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
            pk.setPosition(Vector3f.from(x + 0.5f, y + 0.5f, z + 0.5f));
            pk.setData(block.blockStateHash());
            getChunkService().getChunkByDimensionPos(x, z).addChunkPacket(pk);
        }

        block.getBehavior().onBreak(
                new BlockStateWithPos(block, new Position3i(x, y, z, this), 0),
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
