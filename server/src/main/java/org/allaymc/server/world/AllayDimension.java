package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.eventbus.event.block.BlockPlaceEvent;
import org.allaymc.api.eventbus.event.world.LightningStrikeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.Player;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.server.network.processor.login.SetLocalPlayerAsInitializedPacketProcessor;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.light.AllayLightEngine;
import org.allaymc.server.world.light.NoLightEngine;
import org.allaymc.server.world.manager.AllayBlockUpdateManager;
import org.allaymc.server.world.manager.AllayChunkManager;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.jctools.maps.NonBlockingHashSet;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * {@code AllayDimension} coordinates spatial simulation, player membership,
 * block changes, and manager lifecycle for one world dimension. Its chunk,
 * entity, block update, scheduler, and light components operate on the same
 * dimension identity. World execution serializes dimension ticks with idle
 * entity processing, while the light engine can continue its separate queued
 * calculations. Player membership and chunk loading requests are established
 * separately from queued entity admission, so a player-set entry does not
 * itself prove that entity addition has completed.
 *
 * <p>
 * Block replacement dispatches its event before mutation and runs replacement
 * behavior before placement behavior to avoid retaining an incompatible block
 * entity. Main-layer changes update the point-of-interest index after the
 * block write. Shutdown first rejects new light updates, then batches chunk
 * cleanup before entity cleanup so chunk-unload callbacks can still reach the
 * entity manager. These sequences have no encompassing rollback, and a later
 * exception can leave earlier state changes applied.
 *
 * @author daoge_cmd | Cool_Loong
 */
@Slf4j
@Getter
public class AllayDimension implements Dimension {

    /**
     * {@code LIGHTNING_STRIKE_CHANCE} supplies the random selection bound for
     * one lightning candidate per loaded overworld chunk during each thunder
     * tick.
     */
    protected static final int LIGHTNING_STRIKE_CHANCE = 100000;

    /**
     * World owning this spatial simulation instance.
     */
    protected final AllayWorld world;
    /**
     * Registered dimension identity and height bounds.
     */
    protected final DimensionType dimensionType;
    /**
     * {@code worldGenerator} is attached to this dimension after manager
     * construction; that attachment closes the generator's pre-dimension
     * configuration phase.
     */
    protected final WorldGenerator worldGenerator;
    /**
     * Manager for chunk availability and lifecycle.
     */
    protected final AllayChunkManager chunkManager;
    /**
     * Manager for entity membership and simulation.
     */
    protected final AllayEntityManager entityManager;
    /**
     * Manager for scheduled and neighboring block updates.
     */
    protected final AllayBlockUpdateManager blockUpdateManager;
    /**
     * Scheduler advanced during this dimension's tick.
     */
    protected final Scheduler scheduler;
    /**
     * {@code lightEngine} uses separate calculation workers when enabled;
     * shutdown requests its stop before chunk cleanup sends further light
     * updates.
     */
    protected final AllayLightEngine lightEngine;
    // players and primitiveShapes determine shape viewer registration together.
    // Their concurrent sets support separate membership operations, but changes
    // spanning both sets and viewer callbacks are not one atomic transition.
    /**
     * Controllers currently associated with this dimension.
     */
    protected final Set<Player> players;
    /**
     * Primitive shapes associated with this dimension.
     */
    protected final Set<PrimitiveShape> primitiveShapes;

    /**
     * Creates managers and binds a generator to the supplied dimension
     * identity. The generator attachment occurs after component setup, and
     * {@code enableLightCalculation} selects either queued calculation or a
     * constant full-light engine; callers start workers separately.
     */
    public AllayDimension(AllayWorld world, WorldGenerator worldGenerator, DimensionType dimensionType, boolean enableLightCalculation, boolean useVirtualThread) {
        this.world = world;
        this.dimensionType = dimensionType;
        this.worldGenerator = worldGenerator;
        this.chunkManager = new AllayChunkManager(this, worldGenerator, world.getWorldStorage());
        this.entityManager = new AllayEntityManager(this, world.getWorldStorage());
        this.blockUpdateManager = new AllayBlockUpdateManager(this);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
        this.lightEngine = enableLightCalculation ? new AllayLightEngine(this, useVirtualThread) : new NoLightEngine(this);
        this.players = new NonBlockingHashSet<>();
        this.primitiveShapes = new NonBlockingHashSet<>();
        worldGenerator.setDimension(this);
    }

    /**
     * Starts this dimension's light engine calculation workers when
     * calculation is enabled. Callers invoke this lifecycle hook once because
     * the calculating engine does not guard against starting multiple
     * consumers for its queues.
     */
    public void startTick() {
        this.lightEngine.startTick();
    }

    /**
     * Advances lightning, scheduled tasks, entities, chunks, and block updates
     * in order. The owning world serializes this operation with idle lifecycle
     * processing; loaded chunks then send accumulated block updates before
     * performing queued chunk tasks.
     */
    public void tick(long currentTick) {
        tickLightning();
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

    /**
     * Stops scheduling and requests light engine shutdown before unloading
     * managed data. The owning world calls this operation after dimension
     * ticking has stopped, so direct cleanup does not overlap its normal
     * manager ticks. Lighting shutdown is a stop request and does not join
     * calculation workers.
     *
     * <p>
     * Chunk cleanup precedes entity cleanup because chunk-unload callbacks
     * enqueue entity-related storage work. Both phases run inside the storage
     * backend's batch hooks, with flushing attempted from {@code finally} even
     * if cleanup fails. Backend batching determines actual write behavior;
     * this method provides no rollback after partial cleanup or a failed
     * flush.
     */
    public void shutdown() {
        this.scheduler.shutdown();
        // Reject new light update requests before chunk unload hooks run, because
        // those hooks otherwise enqueue recalculation for data this dimension
        // is releasing during shutdown.
        this.lightEngine.shutdown();
        // Keep chunk and entity cleanup inside the backend batch interval. The
        // finally block attempts its flush after either phase fails, preserving
        // submissions already admitted by the storage implementation.
        world.getWorldStorage().startBatchWrite();
        try {
            this.chunkManager.shutdown();
            // Chunk unload hooks queue entity persistence checks. Shut down the
            // entity manager afterward so its queue drain can include those
            // requests before the backend batch is flushed.
            this.entityManager.shutdown();
        } finally {
            world.getWorldStorage().flushBatchWrite();
        }
    }

    /**
     * Associates a player and requests entity addition with an empty
     * completion callback. The controlled entity must exist; membership and
     * chunk-loader changes follow the full addition path without waiting for
     * entity admission.
     */
    public void addPlayer(Player player) {
        addPlayer(player, () -> {
        });
    }

    /**
     * Associates a player, installs its chunk loader, and requests queued
     * entity addition. The controlled entity must exist before this operation.
     * The supplied callback is passed to the entity manager and runs only if
     * its accepted queued addition reaches the callback.
     *
     * <p>
     * The player set and chunk loader are changed before entity admission
     * completes, with no compensation if that request is rejected or later
     * fails. Primitive shapes are sent immediately only for an {@code IN_GAME}
     * client; login completion performs the corresponding presentation for
     * joining players.
     */
    public void addPlayer(Player player, Runnable runnable) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        this.players.add(player);
        this.chunkManager.addChunkLoader(entity);
        this.entityManager.addEntity(entity, runnable);
        if (player.getClientState() == ClientState.IN_GAME) {
            // Joining clients receive these shapes from their initialized packet
            // processor later; sending only to IN_GAME clients here keeps shape
            // presentation after the client's initial world setup.
            addPrimitiveShapesTo(player);
        }
    }

    /**
     * Registers a viewer on every retained shape and sends one combined
     * presentation. Login completion uses this operation after the client
     * reaches the presentation phase where primitive shapes can be displayed.
     */
    public void addPrimitiveShapesTo(WorldViewer viewer) {
        for (var primitiveShape : primitiveShapes) {
            // Register without individual sends so the combined presentation below
            // carries all current shapes rather than duplicating each viewer update.
            primitiveShape.addViewer(viewer, false);
        }
        viewer.viewPrimitiveShapes(primitiveShapes);
    }

    /**
     * Removes a player through the full removal path with an empty callback.
     * The controlled entity must exist, and any deferred entity cleanup
     * follows the entity manager's normal request admission.
     */
    public void removePlayer(Player player) {
        removePlayer(player, () -> {
        });
    }

    /**
     * Removes player membership and its chunk loader while arranging entity
     * cleanup. A spawned entity uses the manager's deferred removal and
     * callback path; an already unspawned entity invokes the callback directly
     * after membership cleanup. Callers provide an existing controlled entity
     * and respect dimension execution ordering.
     *
     * <p>
     * The direct path supports respawn transitions where death has already
     * unloaded the old entity. Rejected deferred removal does not guarantee
     * callback invocation, and failures do not restore player membership,
     * loader state, or shape registrations already changed by this sequence.
     */
    public void removePlayer(Player player, Runnable runnable) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        if (entity.isSpawned()) {
            // Respawn can reach this method after death already unloaded the entity.
            // Only a still-spawned entity needs deferred removal through the
            // manager; the other path must invoke its callback directly.
            this.entityManager.removeEntity(entity, runnable);
            this.chunkManager.removeChunkLoader(entity);
            this.players.remove(player);
        } else {
            this.chunkManager.removeChunkLoader(entity);
            this.players.remove(player);
            runnable.run();
        }
        removePrimitiveShapesFrom(player);
    }

    /**
     * Removes a viewer from every retained shape and sends one combined
     * removal. Registration changes suppress per-shape presentation so the
     * final viewer call carries the batch for this dimension.
     */
    protected void removePrimitiveShapesFrom(WorldViewer viewer) {
        for (var primitiveShape : primitiveShapes) {
            // Unregister without individual sends so the combined removal below
            // represents this dimension's shapes without duplicate per-shape notices.
            primitiveShape.removeViewer(viewer, false);
        }
        viewer.removePrimitiveShapes(primitiveShapes);
    }

    /**
     * Returns an unmodifiable view backed by the dimension's player set. Later
     * membership changes remain visible through the view, while the player
     * references continue to be shared with their controllers.
     */
    @Override
    @UnmodifiableView
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    /**
     * Associates a shape with this dimension and registers every current
     * player as a viewer. Set insertion does not gate the viewer loop, so
     * repeated calls can repeat viewer registration requests.
     */
    @Override
    public void addPrimitiveShape(PrimitiveShape primitiveShape) {
        this.primitiveShapes.add(primitiveShape);
        for (var player : this.players) {
            primitiveShape.addViewer(player);
        }
    }

    /**
     * Removes a shape from this dimension and unregisters every current
     * player. Viewer removal is requested even when the set did not contain
     * the shape, and callback failures do not restore membership.
     */
    @Override
    public void removePrimitiveShape(PrimitiveShape primitiveShape) {
        this.primitiveShapes.remove(primitiveShape);
        for (var player : this.players) {
            primitiveShape.removeViewer(player);
        }
    }

    /**
     * Returns an unmodifiable view backed by the retained shape set. Later
     * additions and removals remain visible, and each shape instance remains
     * shared with its registered viewers and callers.
     */
    @Override
    @UnmodifiableView
    public Set<PrimitiveShape> getPrimitiveShapes() {
        return Collections.unmodifiableSet(this.primitiveShapes);
    }

    /**
     * Removes every current player from retained shapes before clearing the
     * set. A viewer failure can stop the sequence before clearing, leaving
     * earlier registration removals already applied without rollback.
     */
    @Override
    public void removeAllPrimitiveShapes() {
        for (var player : players) {
            removePrimitiveShapesFrom(player);
        }
        this.primitiveShapes.clear();
    }

    /**
     * Replaces a block in a loaded chunk after accepted placement-event
     * dispatch. Missing chunks and cancellation return {@code false}; callers
     * supply a valid height and layer. The requested state is retained for
     * placement rather than read back as a replacement from the event.
     *
     * <p>
     * When behavior callbacks are enabled, old replacement behavior runs
     * before new placement behavior so old block entity cleanup precedes
     * creation of the new one. The block write then updates main-layer
     * point-of-interest membership, optional neighboring updates, and
     * post-placement callbacks. Failures can leave earlier steps applied
     * because this sequence has no rollback.
     */
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
            // Remove the old block entity before placement creates the replacement.
            // Reversing these callbacks can leave a block entity whose type
            // belongs to the state that was just replaced.
            oldBlockState.getBehavior().onReplace(oldBlock, blockState, placementInfo);
            blockState.getBehavior().onPlace(oldBlock, blockState, placementInfo);
        }
        chunk.setBlockState(xIndex, y, zIndex, blockState, layer, send);

        // Points of interest describe main-layer blocks. Updating their index
        // after the block write keeps later searches aligned with the stored
        // state rather than treating an extra-layer replacement as a POI change.
        if (layer == 0) {
            var oldPoi = Registries.POI_TYPES.get(oldBlockState.getBlockType());
            var newPoi = Registries.POI_TYPES.get(blockState.getBlockType());
            if (oldPoi != newPoi) {
                if (oldPoi != null) chunk.removePoi(xIndex, y, zIndex);
                if (newPoi != null) chunk.addPoi(xIndex, y, zIndex, newPoi);
            }
        }

        if (update) {
            updateAround(x, y, z, oldBlockState);
        }

        if (callBlockBehavior) {
            chunk.getBlockState(xIndex, y, zIndex, layer == 0 ? 1 : 0).getBehavior().afterNeighborLayerReplace(oldBlock, blockState, placementInfo);
            blockState.getBehavior().afterPlaced(oldBlock, blockState, placementInfo);
            oldBlockState.getBehavior().afterReplaced(oldBlock, blockState, placementInfo);
        }

        return true;
    }

    /**
     * Returns a copied position for the nearest matching point of interest in
     * loaded chunks. The radius selects a square of chunk coordinates around
     * the query, and unloaded chunks are skipped rather than requested from
     * storage. No match returns {@code null}.
     *
     * <p>
     * Selection compares squared three-dimensional distance after translating
     * each chunk-local key to dimension coordinates. Equal distances retain
     * the first encountered entry, so callers must not rely on a stable tie
     * choice from the underlying map iteration order.
     */
    @Override
    public Vector3ic findNearestPoi(PoiType type, int x, int y, int z, int chunkRadius) {
        int cx = x >> 4, cz = z >> 4;
        Vector3ic nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            for (int dz = -chunkRadius; dz <= chunkRadius; dz++) {
                var chunk = chunkManager.getChunk(cx + dx, cz + dz);
                if (chunk == null) continue;
                for (var entry : chunk.getPoiEntries().entrySet()) {
                    if (entry.getValue() != type) continue;
                    int hash = entry.getKey();
                    int px = HashUtils.getXFromHashChunkXYZ(hash) + ((cx + dx) << 4);
                    int py = HashUtils.getYFromHashChunkXYZ(hash);
                    int pz = HashUtils.getZFromHashChunkXYZ(hash) + ((cz + dz) << 4);
                    double distSq = (double) (px - x) * (px - x) + (double) (py - y) * (py - y) + (double) (pz - z) * (pz - z);
                    if (distSq < nearestDistSq) {
                        nearestDistSq = distSq;
                        nearest = new Vector3i(px, py, pz);
                    }
                }
            }
        }
        return nearest;
    }

    /**
     * Dispatches a break event and invokes block break behavior before
     * requesting air placement. Air and cancelled break events return {@code
     * false}; a later rejected placement is not checked, so {@code true}
     * reports this break path's acceptance rather than proof that air was
     * stored.
     *
     * <p>
     * Optional particles are emitted before break behavior, and player
     * exhaustion follows the placement request. These effects are not rolled
     * back if a later operation fails or another placement listener rejects
     * the air change; callers coordinate the operation with dimension
     * mutation.
     */
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

    /**
     * Returns the strongest neighboring weak signal, including conduction
     * through solid neighbors. Each neighbor's behavior is queried on the face
     * pointing back toward the target, keeping direction relative to the
     * emitting block rather than the query position. Missing blocks follow
     * ordinary dimension lookup behavior.
     *
     * <p>
     * A solid neighbor also contributes strong power received from its other
     * faces. Excluding the face toward the target avoids feeding the queried
     * position directly back into the conducting neighbor's contribution; the
     * maximum across direct and conducted values becomes the result.
     */
    @Override
    public int getPowerAt(Vector3ic pos) {
        int maxPower = 0;

        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = this.getBlockState(neighborPos);

            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, this));
            int signal = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            maxPower = Math.max(maxPower, signal);

            // Exclude the direction back toward pos when checking conduction,
            // so the solid neighbor does not directly reuse the target's
            // own signal as its incoming contribution.
            if (neighborState.getBlockStateData().isSolid()) {
                int strongPower = this.getStrongPowerAt(neighborPos, face.opposite());
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    /**
     * Returns the greatest strong signal emitted toward a position by its
     * neighbors. {@code excludeFaces} removes selected directions from the
     * scan, allowing callers that inspect conduction through a solid block to
     * omit the return path toward their original query.
     *
     * <p>
     * The method samples each remaining neighbor's behavior on its opposite
     * face and compares the resulting values. An empty set of contributing
     * signals leaves zero. Callers provide a non-{@code null} face array; the
     * method performs no clamping beyond selecting the maximum.
     */
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

    /**
     * Returns the greatest direct or solid-conducted weak signal around a
     * position. {@code excludeFaces} removes selected neighbor directions
     * before evaluation. Each remaining neighbor is queried on the face
     * pointing toward the target, and a solid neighbor can additionally
     * conduct strong power from its other sides.
     *
     * <p>
     * The strong-power scan excludes the face leading back toward this query,
     * preventing that immediate return path from contributing to the
     * neighbor's conduction result. The maximum comparison keeps stronger
     * independent contributions intact, and no contributing signal leaves
     * zero.
     */
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

            // Strong input through other sides can make this solid block
            // contribute weak power. Exclude the return path toward pos to
            // avoid immediately feeding the query back into that input.
            if (state.getBlockStateData().isSolid()) {
                int strongPower = this.getStrongPowerAt(checkPos, face.opposite());
                maxPower = Math.max(maxPower, strongPower);
            }
        }

        return maxPower;
    }

    /**
     * Returns whether the supplied array contains the requested face identity.
     */
    private static boolean containsFace(BlockFace[] faces, BlockFace target) {
        for (BlockFace face : faces) {
            if (face == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Queues neighboring updates for horizontal diodes affected by a
     * comparator-producing block. Direct diode neighbors receive the source
     * position, while a solid neighbor allows one more position in the same
     * direction to be checked. Both positions must belong to loaded chunks.
     *
     * <p>
     * The indirect path uses the solid neighbor as the reported update source,
     * preserving which block is immediately adjacent to the diode. Unloaded
     * chunks are skipped rather than loaded for this notification, and the
     * queued update manager determines when diode behavior is evaluated.
     */
    @Override
    public void updateComparatorOutputLevel(int x, int y, int z) {
        var sourcePos = new Vector3i(x, y, z);
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
                blockUpdateManager.neighborBlockUpdate(neighborPos, sourcePos, face.opposite(), null);
            } else if (neighborState.getBlockStateData().isSolid()) {
                // A solid neighbor can relay the comparator output to a diode
                // directly behind it; retain that neighbor as the reported
                // source when enqueueing the indirect update.
                int nx2 = nx + offset.x();
                int nz2 = nz + offset.z();

                if (!getChunkManager().isChunkLoaded(nx2 >> 4, nz2 >> 4)) {
                    continue;
                }

                BlockState behindState = getBlockState(nx2, ny, nz2);
                if (isDiode(behindState)) {
                    var behindPos = new Vector3i(nx2, ny, nz2);
                    blockUpdateManager.neighborBlockUpdate(behindPos, neighborPos, face.opposite(), null);
                }
            }
        }
    }

    /**
     * Returns whether the state is a powered or unpowered redstone diode.
     */
    private boolean isDiode(BlockState state) {
        var type = state.getBlockType();
        return type == POWERED_REPEATER ||
               type == UNPOWERED_REPEATER ||
               type == POWERED_COMPARATOR ||
               type == UNPOWERED_COMPARATOR;
    }

    /**
     * Attempts natural lightning in loaded overworld chunks during thunder
     * weather. Each chunk has a one-in-{@code 100000} selection chance per
     * call, and a selected sky-visible position passes through the cancellable
     * strike operation.
     */
    protected void tickLightning() {
        if (world.getWeather() != Weather.THUNDER) {
            return;
        }

        if (dimensionType != DimensionTypes.OVERWORLD) {
            return;
        }

        var rand = ThreadLocalRandom.current();
        var loadedChunks = chunkManager.getLoadedChunks();
        for (var chunk : loadedChunks) {
            // Sample each loaded chunk independently to select rare natural
            // strike candidates without weighting the chance by column count.
            if (rand.nextInt(LIGHTNING_STRIKE_CHANCE) != 0) {
                continue;
            }

            int x = (chunk.getX() << 4) + rand.nextInt(16);
            int z = (chunk.getZ() << 4) + rand.nextInt(16);
            int y = getHeight(x, z) + 1;

            if (!canPosSeeSky(x, y, z)) {
                continue;
            }

            strikeLightning(x, y, z, LightningStrikeEvent.Cause.WEATHER);
        }
    }

    /**
     * Creates a lightning entity and proposes its admission through the strike
     * event. Cancellation returns {@code false}; accepted dispatch requests
     * entity-manager addition and returns {@code true} without waiting for
     * that queued admission to complete.
     */
    @Override
    public boolean strikeLightning(double x, double y, double z, LightningStrikeEvent.Cause cause) {
        var lightningBolt = EntityTypes.LIGHTNING_BOLT.createEntity(
                EntityInitInfo.builder()
                        .dimension(this)
                        .pos(x, y, z)
                        .build()
        );

        var event = new LightningStrikeEvent(this, lightningBolt, cause);
        if (!event.call()) {
            return false;
        }

        entityManager.addEntity(lightningBolt);
        return true;
    }

    /**
     * Returns the owning world name and registered dimension identifier for
     * diagnostics.
     */
    @Override
    public String toString() {
        return "world=" + this.world.getName() + " dimension=" + this.dimensionType.getIdentifier();
    }
}
