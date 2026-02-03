package org.allaymc.server.world.manager;

import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.entity.EntitySpawnEvent;
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
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayEntityManager implements EntityManager {

    protected final Dimension dimension;
    protected final WorldStorage worldStorage;
    protected final AllayEntityPhysicsEngine physicsService;
    protected final Queue<Runnable> queue;
    protected final Long2ObjectOpenHashMap<Entity> entities;
    protected long autoSaveTimer = 0;

    public AllayEntityManager(Dimension dimension, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldStorage = worldStorage;
        this.physicsService = new AllayEntityPhysicsEngine(dimension);
        this.queue = PlatformDependent.newMpscQueue();
        this.entities = new Long2ObjectOpenHashMap<>();
    }

    public void tick(long currentTick) {
        // Check auto saving first, so that pending removed entities will be
        // removed this tick instead of remaining loading until the next tick.
        checkAutoSave();
        processQueue();
        tickEntities(currentTick);
        this.physicsService.tick();
    }

    public void idle() {
        processQueue();
    }

    public void shutdown() {
        // Make sure that all the tasks in the queue is done, and we are safe to call
        // processQueue() here because the ticking-thread have been stopped.
        // Do this before removing entities to handle potential add entity tasks first
        processQueue();
        removeAndSaveEntitiesIf($ -> true, false);
        // Do it again to handle new tasks due to the previous removeAndSaveEntitiesIf() call
        processQueue();
    }

    public void onChunkLoad(int chunkX, int chunkZ) {
        this.worldStorage.readEntities(chunkX, chunkZ, dimension.getDimensionInfo()).thenAccept(entities -> {
            for (var entity : entities.values()) {
                addEntity(entity);
            }
        });
    }

    public void onChunkUnload(int chunkX, int chunkZ) {
        // Run the check in ticking thread
        this.queue.offer(() -> {
            // Check if there is no entity in this chunk, and if so, remove the old saved entities in this chunk
            for (var entry : entities.long2ObjectEntrySet()) {
                var entity = entry.getValue();
                if (!entity.isPersistent()) {
                    // Discard entities that are not persistent
                    continue;
                }

                var loc = entity.getLocation();
                if (chunkX == (int) loc.x() >> 4 && chunkZ == (int) loc.z() >> 4) {
                    // There are still entities in this chunk, so we don't remove the old saved entities
                    // since the old saved entities will be overwritten by the new entities.
                    return;
                }
            }

            // No entity in this chunk, so we should remove the old saved entities
            if (dimension.getWorld().getState() == WorldState.STOPPING) {
                // Do it in sync if the world is stopping
                this.worldStorage.writeEntitiesSync(chunkX, chunkZ, dimension.getDimensionInfo(), Map.of());
            } else {
                this.worldStorage.writeEntities(chunkX, chunkZ, dimension.getDimensionInfo(), Map.of());
            }
        });
    }

    protected void processQueue() {
        Runnable task;
        while ((task = queue.poll()) != null) {
            task.run();
        }
    }

    protected void tickEntities(long currentTick) {
        for (var entity : entities.values()) {
            ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).tick(currentTick);
        }
    }

    protected void checkAutoSave() {
        autoSaveTimer++;
        if (autoSaveTimer >= AllayServer.getSettings().storageSettings().entityAutoSaveCycle()) {
            autoSaveTimer = 0;
            removeAndSaveEntitiesIf(entity -> {
                var loc = entity.getLocation();
                var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
                return !dimension.getChunkManager().isChunkLoaded(hashXZ);
            }, true);
        }
    }

    protected void removeAndSaveEntitiesIf(Predicate<Entity> predicate, boolean asyncWrite) {
        // Removed entities grouped by chunk index
        var removedEntities = new Long2ObjectOpenHashMap<Long2ObjectOpenHashMap<Entity>>();

        // Find entities that should be removed
        for (var entity : entities.values()) {
            if (!entity.isPersistent()) {
                continue;
            }

            var loc = entity.getLocation();
            var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
            if (predicate.test(entity)) {
                removedEntities
                        .computeIfAbsent(hashXZ, $ -> new Long2ObjectOpenHashMap<>())
                        .put(entity.getUniqueId().getLeastSignificantBits(), entity);
                removeEntity(entity);
            }
        }

        // Save the removed entities
        for (var entry : removedEntities.long2ObjectEntrySet()) {
            var hashXZ = entry.getLongKey();
            if (asyncWrite) {
                worldStorage.writeEntities(
                        HashUtils.getXFromHashXZ(hashXZ), HashUtils.getZFromHashXZ(hashXZ),
                        dimension.getDimensionInfo(), entry.getValue()
                );
            } else {
                worldStorage.writeEntitiesSync(
                        HashUtils.getXFromHashXZ(hashXZ), HashUtils.getZFromHashXZ(hashXZ),
                        dimension.getDimensionInfo(), entry.getValue()
                );
            }
        }
    }

    @Override
    public void addEntity(Entity entity, Runnable callback) {
        if (!entity.canBeSpawned()) {
            log.warn("Trying to add an entity which can't be added! Entity: {}", entity);
            return;
        }

        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.SPAWNED_NEXT_TICK)) {
            queue.add(() -> {
                addEntityImmediately(entity);
                callback.run();
            });

            ((AllayWorld) this.dimension.getWorld()).wakeUp();
        }
    }

    protected void addEntityImmediately(Entity entity) {
        new EntitySpawnEvent(entity).call();
        var current = entities.putIfAbsent(entity.getRuntimeId(), entity);
        if (current != null) {
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

    @Override
    public void removeEntity(Entity entity, Runnable callback) {
        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.DESPAWNED_NEXT_TICK)) {
            queue.add(() -> {
                removeEntityImmediately(entity);
                callback.run();
            });

            ((AllayWorld) this.dimension.getWorld()).wakeUp();
        }
    }

    protected void removeEntityImmediately(Entity entity) {
        new EntityDespawnEvent(entity).call();

        entities.remove(entity.getRuntimeId());
        physicsService.removeEntity(entity);
        entity.despawnFromAll();

        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setState(EntityState.DESPAWNED);
    }

    @Override
    public Entity getEntity(long runtimeId) {
        return entities.get(runtimeId);
    }

    @Override
    public @UnmodifiableView Map<Long, Entity> getEntities() {
        return Collections.unmodifiableMap(entities);
    }

    @Override
    public void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer) {
        queue.add(() -> forEachEntitiesInChunkImmediately(chunkX, chunkZ, consumer));
    }

    @Override
    public void forEachEntitiesInChunkImmediately(int chunkX, int chunkZ, Consumer<Entity> consumer) {
        for (var entry : entities.long2ObjectEntrySet()) {
            var entity = entry.getValue();
            var loc = entity.getLocation();
            if (chunkX == (int) loc.x() >> 4 && chunkZ == (int) loc.z() >> 4) {
                consumer.accept(entity);
            }
        }
    }

    @Override
    public EntityPhysicsEngine getPhysicsService() {
        return physicsService;
    }

    @Override
    public void checkAutoSaveImmediately() {
        this.autoSaveTimer = AllayServer.getSettings().storageSettings().entityAutoSaveCycle();
    }
}
