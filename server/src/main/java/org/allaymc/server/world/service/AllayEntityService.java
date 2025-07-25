package org.allaymc.server.world.service;

import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.entity.EntitySpawnEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityService;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Cool_Loong| daoge_cmd
 */
@Slf4j
public class AllayEntityService implements EntityService {

    protected final Dimension dimension;
    protected final WorldStorage worldStorage;
    protected final AllayEntityPhysicsService physicsService;
    protected final Queue<Runnable> queue;
    protected final Long2ObjectOpenHashMap<Entity> entities;
    protected long autoSaveTimer = 0;

    public AllayEntityService(Dimension dimension, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldStorage = worldStorage;
        this.physicsService = new AllayEntityPhysicsService(dimension);
        this.queue = PlatformDependent.newMpscQueue();
        this.entities = new Long2ObjectOpenHashMap<>();
    }

    public void tick(long currentTick) {
        // Check auto saving firstly, so that pending removed entities will be
        // removed this tick instead of remaining loading until the next tick.
        checkAutoSave();
        processQueue();
        tickEntities(currentTick);
        this.physicsService.tick();
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
                if (!entity.willBeSaved()) {
                    // Discard entities that will not be saved
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
        while (!queue.isEmpty()) {
            queue.poll().run();
        }
    }

    protected void tickEntities(long currentTick) {
        for (var entity : entities.values()) {
            ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).tick(currentTick);
        }
    }

    protected void checkAutoSave() {
        autoSaveTimer++;
        if (autoSaveTimer >= Server.SETTINGS.storageSettings().entityAutoSaveCycle()) {
            autoSaveTimer = 0;
            removeAndSaveEntitiesIf(entity -> {
                var loc = entity.getLocation();
                var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
                return !dimension.getChunkService().isChunkLoaded(hashXZ);
            }, true);
        }
    }

    protected void removeAndSaveEntitiesIf(Predicate<Entity> predicate, boolean asyncWrite) {
        // Removed entities grouped by chunk index
        var removedEntities = new Long2ObjectOpenHashMap<Long2ObjectOpenHashMap<Entity>>();

        // Find entities that should be removed
        for (var entity : entities.values()) {
            if (!entity.willBeSaved()) {
                continue;
            }

            var loc = entity.getLocation();
            var hashXZ = HashUtils.hashXZ(((int) loc.x()) >> 4, ((int) loc.z()) >> 4);
            if (predicate.test(entity)) {
                removedEntities
                        .computeIfAbsent(hashXZ, $ -> new Long2ObjectOpenHashMap<>())
                        .put(entity.getUniqueId(), entity);
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

        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.SPAWNED_NEXT_TICK)) {
            queue.add(() -> {
                addEntityImmediately(entity);
                callback.run();
            });
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
        entity.spawnTo(dimension.getChunkService().getChunk((int) entity.getLocation().x() >> 4, (int) entity.getLocation().z() >> 4).getPlayerChunkLoaders());
        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.ALIVE);
    }

    @Override
    public void removeEntity(Entity entity, Runnable callback) {
        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.DESPAWNED_NEXT_TICK)) {
            queue.add(() -> {
                removeEntityImmediately(entity);
                callback.run();
            });
        }
    }

    protected void removeEntityImmediately(Entity entity) {
        new EntityDespawnEvent(entity).call();

        entities.remove(entity.getRuntimeId());
        physicsService.removeEntity(entity);
        entity.despawnFromAll();

        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.DESPAWNED);
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
    public EntityPhysicsService getPhysicsService() {
        return physicsService;
    }

    @Override
    public void checkAutoSaveImmediately() {
        this.autoSaveTimer = Server.SETTINGS.storageSettings().entityAutoSaveCycle();
    }
}
