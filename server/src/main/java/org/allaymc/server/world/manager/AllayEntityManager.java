package org.allaymc.server.world.manager;

import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.component.EntityParallelTickComponent;
import org.allaymc.api.entity.component.EntitySleepableComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.entity.EntitySpawnEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.physics.AllayEntityPhysicsEngine;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * {@code AllayEntityManager} coordinates
 * entity membership, ticking, physics registration, and persistence
 * for one dimension. Requests enter a concurrent task queue, while the live
 * runtime-ID map and immediate lifecycle operations belong to the dimension's
 * ticking context.
 *
 * <p>
 * Each tick checks autosave first, applies queued membership changes, waits for
 * parallel entity work, and then runs ordinary ticks and physics. Persistent
 * saves group entities by chunk and use the low half of each unique identifier
 * as the storage key, distinct from runtime membership keys. Removal requests
 * and writes are separate: queued removals can remain pending when saving
 * begins, and asynchronous writes are not observed for completion. Shutdown
 * drains queued additions before requesting removals and synchronous storage
 * writes, then drains removal tasks.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayEntityManager implements EntityManager {

    /**
     * Dimension owning managed entity membership.
     */
    protected final Dimension dimension;
    /**
     * {@code worldStorage} receives persistent entity groups. This manager does
     * not await or inspect asynchronous write results before proceeding.
     */
    protected final WorldStorage worldStorage;
    /**
     * {@code physicsService} owns dimension physics. The ticking thread updates
     * it after lifecycle queue processing and ordinary entity ticks complete.
     */
    protected final AllayEntityPhysicsEngine physicsService;
    /**
     * {@code queue} accepts requests concurrently for one ticking-context
     * consumer. A task exception stops the current drain without restoring the
     * consumed task.
     */
    protected final Queue<Runnable> queue;
    /**
     * {@code entities} holds live runtime-ID membership owned by the ticking
     * context. Returned unmodifiable views do not make concurrent reads safe.
     */
    protected final Long2ObjectOpenHashMap<Entity> entities;
    /**
     * {@code autoSaveTimer} counts elapsed autosave checks. Submission resets
     * the counter, while {@code checkAutoSaveImmediately()} raises it to
     * request checking during the next tick.
     */
    protected long autoSaveTimer = 0;

    /**
     * Creates an empty entity manager and dimension physics service using the
     * retained storage. Incoming requests use a concurrent queue, while callers
     * must keep live-map operations in the ticking context.
     */
    public AllayEntityManager(Dimension dimension, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldStorage = worldStorage;
        this.physicsService = new AllayEntityPhysicsEngine(dimension);
        this.queue = PlatformDependent.newMpscQueue();
        this.entities = new Long2ObjectOpenHashMap<>();
    }

    /**
     * Checks persistence, processes queued membership changes, ticks entities,
     * and advances physics in that order. Autosave can enqueue removals for
     * entities outside loaded chunks, so processing the queue immediately
     * afterward prevents them from remaining in the live tick set for another
     * cycle. Parallel entity callbacks are joined before ordinary entity ticks.
     * A failure in any uncaught phase stops subsequent phases without undoing
     * completed membership or persistence requests.
     */
    public void tick(long currentTick) {
        // Autosave can queue removal of entities outside loaded chunks; draining
        // those requests before entity ticks prevents them from surviving an extra
        // tick solely because the save check ran later.
        checkAutoSave();
        processQueue();
        tickEntities(currentTick);
        this.physicsService.tick();
    }

    /**
     * Processes queued lifecycle work while the dimension is otherwise idle.
     * The caller must provide the same serialized execution context as normal
     * ticking because tasks mutate live entity membership.
     */
    public void idle() {
        processQueue();
    }

    /**
     * Drains pending work, requests removal and synchronous saving of
     * persistent entities, then drains newly queued removals. The ticking
     * thread must already be stopped so these direct map operations cannot
     * overlap normal processing. Initial draining applies pending additions
     * before the save selection, but nonpersistent entities are excluded by the
     * removal helper. Synchronous storage implementations may still impose
     * batching limitations, and uncaught task or storage failures stop the
     * remaining shutdown sequence.
     */
    public void shutdown() {
        // The ticking thread is already stopped. Apply pending additions before
        // selecting persistent entities so the shutdown save includes accepted
        // entities that had not yet entered the live map.
        processQueue();
        removeAndSaveEntitiesIf($ -> true, false);
        // Saving queues removal requests rather than applying them immediately;
        // this final drain performs their lifecycle cleanup after write submission.
        processQueue();
    }

    /**
     * Requests stored entities asynchronously and queues each returned entity
     * for normal addition. Read failures are not handled by this continuation,
     * and the method does not wait for read completion or membership changes.
     */
    public void onChunkLoad(int chunkX, int chunkZ) {
        // Storage completion may occur off-thread, so each returned entity
        // enters the normal addition queue instead of directly mutating the
        // ticking-context membership map.
        this.worldStorage.readEntities(chunkX, chunkZ, dimension.getDimensionType()).thenAccept(entities -> {
            for (var entity : entities.values()) {
                addEntity(entity);
            }
        });
    }

    /**
     * Queues a check that clears saved entities when no persistent live entity
     * maps to the departing chunk. The check runs in the ticking context so
     * membership can be inspected without concurrent iteration. If any
     * persistent entity remains, its later grouped save is expected to replace
     * stored content instead. During world stopping the empty replacement uses
     * the synchronous storage API; otherwise its asynchronous result is
     * ignored.
     */
    public void onChunkUnload(int chunkX, int chunkZ) {
        // Deferring the membership scan avoids reading the nonconcurrent entity map
        // from a chunk completion thread while lifecycle tasks may still change it.
        this.queue.offer(() -> {
            // Preserve stored content when a persistent live entity still maps here;
            // its grouped save can replace the snapshot after the unload transition.
            for (var entry : entities.long2ObjectEntrySet()) {
                var entity = entry.getValue();
                if (!entity.isPersistent()) {
                    continue;
                }

                var loc = entity.getLocation();
                // Truncation before shifting maps negative fractional positions
                // differently from floor-based containing-chunk calculations.
                if (chunkX == (int) loc.x() >> 4 && chunkZ == (int) loc.z() >> 4) {
                    return;
                }
            }

            // An empty replacement removes stale saved actors when no persistent
            // live entity remains to overwrite them through a later grouped save.
            if (dimension.getWorld().getState() == WorldState.STOPPING) {
                // Shutdown selects the synchronous storage API because another world
                // tick cannot be assumed; backend batching can still defer the actual write.
                this.worldStorage.writeEntitiesSync(chunkX, chunkZ, dimension.getDimensionType(), Map.of());
            } else {
                this.worldStorage.writeEntities(chunkX, chunkZ, dimension.getDimensionType(), Map.of());
            }
        });
    }

    /**
     * Drains queued tasks on the current ticking or shutdown thread. Tasks are
     * removed before invocation, so a thrown callback consumes that task and
     * leaves subsequent entries pending for a later drain.
     */
    protected void processQueue() {
        Runnable task;
        // Polling transfers each request out of pending storage before
        // invocation; a failing task is not replayed automatically, and later
        // tasks remain in the queue.
        while ((task = queue.poll()) != null) {
            task.run();
        }
    }

    /**
     * Runs parallel-tick components on the compute pool and joins their
     * completion before ordinary entity ticks. The membership snapshot used for
     * parallel work does not clone entities, so component implementations must
     * respect their own shared-state rules. After all parallel work completes
     * normally, every currently managed entity receives its ordinary tick. A
     * failed parallel join prevents that ordinary pass, while an ordinary tick
     * failure stops later entries.
     */
    protected void tickEntities(long currentTick) {
        // Snapshot membership for parallel callbacks, then join before ordinary
        // ticking so one entity's two phases do not overlap through this manager.
        var parallelTickEntities = entities.values().stream()
                .filter(e -> e instanceof EntityParallelTickComponent)
                .toList();
        if (!parallelTickEntities.isEmpty()) {
            Utils.forEachInParallel(
                    parallelTickEntities,
                    Server.getInstance().getComputeThreadPool(),
                    entity -> ((EntityParallelTickComponent) entity).parallelTick()
            ).join();
        }

        for (var entity : entities.values()) {
            ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).tick(currentTick);
        }
    }

    /**
     * Advances the autosave counter and, at the configured cycle, selects
     * persistent entities outside loaded chunks for removal and saving.
     * Remaining loaded-chunk entities are grouped for asynchronous saving
     * afterward. Chunk classification casts locations to integers before
     * shifting, so negative fractional coordinates follow truncation rather
     * than mathematical floor. The timer resets before requests are issued, and
     * write results are not awaited; persistence failure does not automatically
     * repeat this cycle.
     */
    protected void checkAutoSave() {
        autoSaveTimer++;
        if (autoSaveTimer >= AllayServer.getSettings().storageSettings().entityAutoSaveCycle()) {
            // Reset before requesting writes so this cycle is counted as submitted;
            // ignored asynchronous failures do not restore the previous elapsed count.
            autoSaveTimer = 0;
            removeAndSaveEntitiesIf(entity -> {
                var loc = entity.getLocation();
                // Casting truncates negative fractions before chunk conversion, unlike
                // floor-based chunk selection used by loader activity checks.
                var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
                return !dimension.getChunkManager().isChunkLoaded(hashXZ);
            }, true);
            saveLoadedEntities();
        }
    }

    /**
     * Groups persistent entities currently mapped to loaded chunks and submits
     * one replacement snapshot per nonempty group. Membership uses runtime
     * identifiers, but each saved group uses the least significant bits of
     * entity unique identifiers, matching the storage API. Chunk coordinates
     * come from integer-cast positions, including truncation for negative
     * fractions. No empty groups are written and no futures are observed, so
     * this pass neither clears untouched chunks nor reports persistence
     * completion.
     */
    protected void saveLoadedEntities() {
        // Only nonempty persistent groups are written; empty-chunk cleanup
        // belongs to the unload check rather than this pass, preventing an
        // implicit all-chunks storage scan.
        var entitiesByChunk = new Long2ObjectOpenHashMap<Long2ObjectOpenHashMap<Entity>>();
        for (var entity : entities.values()) {
            if (!entity.isPersistent()) {
                continue;
            }

            var loc = entity.getLocation();
            // Casting truncates negative fractions before chunk conversion, unlike
            // floor-based chunk selection used by loader activity checks.
            var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
            if (!dimension.getChunkManager().isChunkLoaded(hashXZ)) {
                continue;
            }

            entitiesByChunk
                    .computeIfAbsent(hashXZ, $ -> new Long2ObjectOpenHashMap<>())
                    .put(entity.getUniqueId().getLeastSignificantBits(), entity);
        }

        for (var entry : entitiesByChunk.long2ObjectEntrySet()) {
            var hashXZ = entry.getLongKey();
            worldStorage.writeEntities(
                    HashUtils.getXFromHashXZ(hashXZ), HashUtils.getZFromHashXZ(hashXZ),
                    dimension.getDimensionType(), entry.getValue()
            );
        }
    }

    /**
     * Selects persistent entities matching the predicate, groups their shared
     * references by chunk, and queues their removal. Group keys use persistent
     * unique-ID low bits, while removal still addresses runtime membership.
     * Writes are then submitted through the requested synchronous or
     * asynchronous API before queued removals necessarily execute.
     * Nonpersistent entities are skipped entirely, and failures do not roll
     * back accepted removal requests or earlier group writes; callers must
     * account for that partial-work boundary.
     */
    protected void removeAndSaveEntitiesIf(Predicate<Entity> predicate, boolean asyncWrite) {
        // Keep selected entity references for serialization after queuing removal;
        // the live map remains unchanged until lifecycle tasks are drained later.
        var removedEntities = new Long2ObjectOpenHashMap<Long2ObjectOpenHashMap<Entity>>();

        for (var entity : entities.values()) {
            if (!entity.isPersistent()) {
                continue;
            }

            var loc = entity.getLocation();
            // Casting truncates negative fractions before chunk conversion, unlike
            // floor-based chunk selection used by loader activity checks.
            var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
            if (predicate.test(entity)) {
                removedEntities
                        .computeIfAbsent(hashXZ, $ -> new Long2ObjectOpenHashMap<>())
                        .put(entity.getUniqueId().getLeastSignificantBits(), entity);
                removeEntity(entity);
            }
        }

        // Removal requests already exist before persistence begins. A write failure
        // does not cancel those requests or restore earlier stored chunk groups.
        for (var entry : removedEntities.long2ObjectEntrySet()) {
            var hashXZ = entry.getLongKey();
            if (asyncWrite) {
                worldStorage.writeEntities(
                        HashUtils.getXFromHashXZ(hashXZ), HashUtils.getZFromHashXZ(hashXZ),
                        dimension.getDimensionType(), entry.getValue()
                );
            } else {
                worldStorage.writeEntitiesSync(
                        HashUtils.getXFromHashXZ(hashXZ), HashUtils.getZFromHashXZ(hashXZ),
                        dimension.getDimensionType(), entry.getValue()
                );
            }
        }
    }

    /**
     * Queues addition only when spawning is permitted and the base component
     * accepts {@code SPAWNED_LATER}. An accepted transition queues immediate addition
     * followed by the callback, then wakes the world. Rejected requests do not
     * invoke the callback, and a thrown addition prevents it from running. The
     * immediate helper can also return after detecting a duplicate runtime ID,
     * so callback execution alone does not guarantee that this request inserted
     * the entity.
     */
    @Override
    public void addEntity(Entity entity, Runnable callback) {
        if (!entity.canBeSpawned()) {
            log.warn("Trying to add an entity which can't be added! Entity: {}", entity);
            return;
        }

        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.SPAWNED_LATER)) {
            // The accepted state transition precedes queue publication and
            // world wakeup; the callback runs only after the queued addition
            // helper returns, including its duplicate-entry early return.
            queue.add(() -> {
                addEntityImmediately(entity);
                callback.run();
            });

            ((AllayWorld) this.dimension.getWorld()).wakeUp();
        }
    }

    /**
     * Publishes the spawn event, inserts runtime membership when absent, then
     * registers physics and current chunk viewers before marking the entity
     * {@code ALIVE}. Duplicate runtime identifiers log a warning and return after the
     * event without replacing the existing entity. The operation belongs to the
     * ticking context, and exceptions after insertion leave preceding
     * registration steps intact. Viewer spawning applies only to actual players
     * attached to the entity's current chunk.
     */
    protected void addEntityImmediately(Entity entity) {
        new EntitySpawnEvent(entity).call();
        // Membership is inserted after event delivery but before physics and
        // viewer setup; a later failure leaves earlier steps applied without
        // compensating removal in this helper.
        var current = entities.putIfAbsent(entity.getRuntimeId(), entity);
        if (current != null) {
            // A duplicate runtime ID preserves the existing map entry and skips
            // later registration, but the enclosing queued task still invokes
            // its callback after this return.
            log.warn("Trying to add an entity which already exists! Entity: {}", entity);
            return;
        }

        physicsService.addEntity(entity);
        var chunk = entity.getCurrentChunk();
        if (chunk != null) {
            chunk.getChunkLoaders().forEach(loader -> {
                if (loader instanceof EntityPlayer player && player.isActualPlayer()) {
                    entity.spawnTo(player.getController());
                }
            });
        }
        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.ALIVE);
    }

    /**
     * Queues removal only when the base component accepts {@code DESPAWNED_LATER}, then
     * wakes the world. The callback runs after immediate removal returns;
     * rejected transitions and thrown removal steps do not invoke it.
     */
    @Override
    public void removeEntity(Entity entity, Runnable callback) {
        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.DESPAWNED_LATER)) {
            // Queue publication follows the accepted despawn transition; wakeup makes
            // idle-world processing possible, but callback completion still depends on
            // every immediate removal step returning normally.
            queue.add(() -> {
                removeEntityImmediately(entity);
                callback.run();
            });

            ((AllayWorld) this.dimension.getWorld()).wakeUp();
        }
    }

    /**
     * Publishes the despawn event, wakes a sleeping entity, removes membership
     * and physics registration, and despawns viewers before marking {@code DESPAWNED}.
     * Waking first lets sleepable behavior clear occupied-bed state while the
     * entity is still available to that behavior. The caller must own the
     * ticking context. Exceptions stop subsequent steps without restoring
     * already removed membership or viewer state, so this sequence is not an
     * atomic lifecycle transaction.
     */
    protected void removeEntityImmediately(Entity entity) {
        new EntityDespawnEvent(entity).call();

        // Wake before detaching membership and physics so sleeping behavior can
        // clear the occupied bed as part of the entity's normal wake transition.
        if (entity instanceof EntitySleepableComponent sleepableComponent && sleepableComponent.isSleeping()) {
            sleepableComponent.wake();
        }
        entities.remove(entity.getRuntimeId());
        physicsService.removeEntity(entity);
        entity.despawnFromAll();

        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.DESPAWNED);
    }

    /**
     * Returns the shared entity mapped by runtime identifier, or {@code null} when
     * absent. The underlying membership map is not concurrent, so callers must
     * respect the manager's ticking-context access requirement.
     */
    @Override
    public Entity getEntity(long runtimeId) {
        return entities.get(runtimeId);
    }

    /**
     * Returns an unmodifiable live view of runtime-ID membership without
     * copying entities. The underlying map is not concurrent; the view
     * restricts mutation through its API but does not make iteration
     * thread-safe.
     */
    @Override
    public @UnmodifiableView Map<Long, Entity> getEntities() {
        return Collections.unmodifiableMap(entities);
    }

    /**
     * Queues chunk-filtered iteration for a later task drain in the ticking
     * context. Unlike addition and removal, this submission does not wake the
     * world, so idle processing must occur separately.
     */
    @Override
    public void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer) {
        queue.add(() -> forEachEntitiesInChunkImmediately(chunkX, chunkZ, consumer));
    }

    /**
     * Invokes the consumer immediately for live entities whose integer-cast
     * positions map to the requested chunk. Callers must own the ticking
     * context; negative fractional coordinates are truncated before shifting
     * rather than floored.
     */
    @Override
    public void forEachEntitiesInChunkImmediately(int chunkX, int chunkZ, Consumer<Entity> consumer) {
        for (var entry : entities.long2ObjectEntrySet()) {
            var entity = entry.getValue();
            var loc = entity.getLocation();
            // Truncation before shifting maps negative fractional positions
            // differently from floor-based containing-chunk calculations.
            if (chunkX == (int) loc.x() >> 4 && chunkZ == (int) loc.z() >> 4) {
                consumer.accept(entity);
            }
        }
    }

    /**
     * Returns the dimension physics service retained by this entity manager.
     */
    @Override
    public EntityPhysicsEngine getPhysicsService() {
        return physicsService;
    }

    /**
     * Raises the counter to the configured cycle so the next autosave check
     * reaches its threshold. It performs no write or removal immediately and
     * does not itself wake an idle world.
     */
    @Override
    public void checkAutoSaveImmediately() {
        this.autoSaveTimer = AllayServer.getSettings().storageSettings().entityAutoSaveCycle();
    }
}
