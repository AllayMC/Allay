package org.allaymc.api.world.manager;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * EntityManager is responsible for adding, removing and ticking entities.
 *
 * @author Cool_Loong
 */
public interface EntityManager {

    /**
     * Add an entity to the dimension.
     *
     * @param entity the entity pending to be spawned
     */
    default void addEntity(Entity entity) {
        addEntity(entity, () -> {
        });
    }

    /**
     * Add an entity to the dimension.
     * <p>
     * The entity won't be added to the dimension immediately. It will be added to the dimension
     * later, and the callback will be called.
     *
     * @param entity   The entity pending to be spawned
     * @param callback The callback to be called after the entity is added to the dimension
     */
    void addEntity(Entity entity, Runnable callback);

    /**
     * Remove an entity from the dimension.
     *
     * @param entity the entity pending to be despawned
     */
    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {
        });
    }

    /**
     * Remove an entity from the dimension.
     * <p>
     * The entity won't be removed from the dimension immediately. It will be removed from the
     * dimension later, and the callback will be called.
     *
     * @param entity   The entity pending to be despawned
     * @param callback The callback to be called after the entity is removed from the dimension
     */
    void removeEntity(Entity entity, Runnable callback);

    /**
     * Get the entity in the service by its runtime id.
     *
     * @param runtimeId the runtime id of the entity
     * @return the entity in the service, or {@code null} if not found
     */
    Entity getEntity(long runtimeId);

    /**
     * Get all entities in the service.
     *
     * @return all entities in the service
     */
    @UnmodifiableView
    Map<Long, Entity> getEntities();

    /**
     * For-each all entities in specified chunk. Note that the consumer will be called
     * later on the main thread instead of the caller's thread immediately, so that this
     * method is safe to be called in any thread.
     *
     * @param chunkX the x coordinate of the chunk
     * @param chunkZ the z coordinate of the chunk
     */
    void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer);

    /**
     * For-each all entities in specified chunk. Different from {@link #forEachEntitiesInChunk(int, int, Consumer)},
     * this method will call the consumer immediately, so it is not safe to be called in non-ticking thread.
     *
     * @param chunkX the x coordinate of the chunk
     * @param chunkZ the z coordinate of the chunk
     */
    void forEachEntitiesInChunkImmediately(int chunkX, int chunkZ, Consumer<Entity> consumer);

    /**
     * Get all entities in specified chunk. Since it used {@link #forEachEntitiesInChunkImmediately(int, int, Consumer)},
     * it is not safe to be called in non-ticking thread.
     *
     * @param chunkX the x coordinate of the chunk
     * @param chunkZ the z coordinate of the chunk
     * @return all entities in the chunk
     */
    default Map<Long, Entity> getEntitiesInChunk(int chunkX, int chunkZ) {
        var map = new HashMap<Long, Entity>();
        forEachEntitiesInChunkImmediately(chunkX, chunkZ, entity -> map.put(entity.getRuntimeId(), entity));
        return map;
    }

    /**
     * Get all players in specified chunk. Same to {@link #getEntitiesInChunk(int, int)}, this method is not safe to be
     * called in non-ticking thread.
     *
     * @param chunkX the x coordinate of the chunk
     * @param chunkZ the z coordinate of the chunk
     * @return all players in the chunk
     */
    default Map<Long, EntityPlayer> getPlayersInChunk(int chunkX, int chunkZ) {
        var map = new HashMap<Long, EntityPlayer>();
        forEachEntitiesInChunkImmediately(chunkX, chunkZ, entity -> {
            if (entity instanceof EntityPlayer player) {
                map.put(player.getRuntimeId(), player);
            }
        });
        return map;
    }

    /**
     * Get the used physics service.
     *
     * @return the physics service
     */
    EntityPhysicsEngine getPhysicsService();

    /**
     * Let the service check auto save later immediately.
     */
    void checkAutoSaveImmediately();
}
