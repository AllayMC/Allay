package org.allaymc.api.world.service;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.allaymc.api.entity.Entity;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.function.Consumer;

/**
 * EntityService is responsible for adding, removing and ticking entities.
 *
 * @author Cool_Loong
 */
public interface EntityService {

    /**
     * Add an entity to the world.
     *
     * @param entity the entity pending to be spawned.
     */
    default void addEntity(Entity entity) {
        addEntity(entity, () -> {});
    }

    /**
     * Add an entity to the world.
     * <p>
     * The entity won't be added to the world immediately.
     * It will be added to the world in the next tick, and
     * the callback will be called.
     *
     * @param entity   The entity pending to be spawned
     * @param callback The callback to be called after the entity is added to the world
     */
    void addEntity(Entity entity, Runnable callback);

    /**
     * Remove an entity from the world.
     *
     * @param entity the entity pending to be despawned.
     */
    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {});
    }

    /**
     * Remove an entity from the world.
     * <p>
     * The entity won't be removed from the world immediately.
     * It will be removed from the world in the next tick, and
     * the callback will be called.
     *
     * @param entity   The entity pending to be despawned
     * @param callback The callback to be called after the entity is removed from the world
     */
    void removeEntity(Entity entity, Runnable callback);

    /**
     * Get the entity in the service by its runtime id.
     *
     * @param runtimeId the runtime id of the entity.
     *
     * @return the entity in the service, or {@code null} if not found.
     */
    Entity getEntity(long runtimeId);

    /**
     * Get all entities in the service.
     *
     * @return all entities in the service.
     */
    @UnmodifiableView
    Map<Long, Entity> getEntities();

    /**
     * For-each all entities in specified chunk.
     *
     * @param chunkX the x coordinate of the chunk.
     * @param chunkZ the z coordinate of the chunk.
     */
    void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer);

    /**
     * Get all entities in specified chunk.
     *
     * @param chunkX the x coordinate of the chunk.
     * @param chunkZ the z coordinate of the chunk.
     *
     * @return all entities in the chunk.
     */
    default Map<Long, Entity> getEntitiesInChunk(int chunkX, int chunkZ) {
        var map = new Long2ObjectOpenHashMap<Entity>();
        forEachEntitiesInChunk(chunkX, chunkZ, entity -> map.put(entity.getRuntimeId(), entity));
        return map;
    }

    /**
     * Get the used physics service.
     *
     * @return the physics service.
     */
    EntityPhysicsService getPhysicsService();

    /**
     * Let the service check auto save in the next tick immediately.
     */
    void checkAutoSaveImmediately();
}
