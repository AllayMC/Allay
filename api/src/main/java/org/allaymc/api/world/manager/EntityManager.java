package org.allaymc.api.world.manager;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * {@code EntityManager} manages
 * a dimension's entity membership, deferred lifecycle changes, and
 * entity iteration. Add and remove requests are processed later with callbacks,
 * while immediate chunk iteration belongs to the ticking thread and returns
 * shared entity objects rather than copies.
 *
 * @author Cool_Loong
 */
public interface EntityManager {

    /**
     * Requests deferred addition of an entity without a completion callback.
     * This delegates to the callback overload with an empty action and does not
     * make the entity immediately available in the dimension.
     */
    default void addEntity(Entity entity) {
        addEntity(entity, () -> {
        });
    }

    /**
     * Requests deferred addition of an entity to this dimension and invokes the
     * callback after it is added. Submission does not establish immediate
     * membership; implementations define eligibility and handling of rejected
     * requests.
     */
    void addEntity(Entity entity, Runnable callback);

    /**
     * Requests deferred removal of an entity without a completion callback.
     * This delegates to the callback overload with an empty action, so
     * membership can remain visible until processing occurs.
     */
    default void removeEntity(Entity entity) {
        removeEntity(entity, () -> {
        });
    }

    /**
     * Requests deferred removal of an entity from this dimension and invokes
     * the callback after removal. Submission does not immediately end
     * membership, and callers must respect the entity's lifecycle until
     * processing completes.
     */
    void removeEntity(Entity entity, Runnable callback);

    /**
     * Returns the shared entity with the requested runtime identifier, or {@code null}
     * when absent. Runtime identifiers address live instances rather than the
     * persistent unique identifiers used by world storage.
     */
    Entity getEntity(long runtimeId);

    /**
     * Returns an unmodifiable view of entity membership keyed by runtime
     * identifier. Values remain shared entities, and callers must respect the
     * implementation's thread requirements when inspecting membership or entity
     * state.
     */
    @UnmodifiableView
    Map<Long, Entity> getEntities();

    /**
     * Queues iteration of a chunk's entities for the main ticking context
     * rather than the caller's thread. This method can be submitted from any
     * thread; membership is evaluated when the task runs.
     */
    void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer);

    /**
     * Invokes the consumer immediately for entities in the supplied chunk
     * coordinates. The caller must be on the ticking thread because iteration
     * accesses live membership and shared entity locations without deferred
     * scheduling.
     */
    void forEachEntitiesInChunkImmediately(int chunkX, int chunkZ, Consumer<Entity> consumer);

    /**
     * Returns a new mutable map of shared entity references in the requested
     * chunk, keyed by runtime identifier. The caller must be on the ticking
     * thread because collection uses immediate iteration.
     */
    default Map<Long, Entity> getEntitiesInChunk(int chunkX, int chunkZ) {
        var map = new HashMap<Long, Entity>();
        forEachEntitiesInChunkImmediately(chunkX, chunkZ, entity -> map.put(entity.getRuntimeId(), entity));
        return map;
    }

    /**
     * Returns a new mutable map of shared player entities in the requested
     * chunk, keyed by runtime identifier. The caller must be on the ticking
     * thread; filtering by player type does not copy entities.
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
     * Returns the physics service associated with this dimension's entity
     * manager.
     */
    EntityPhysicsEngine getPhysicsService();

    /**
     * Requests an autosave eligibility check at the next manager processing
     * opportunity. This is a scheduling hint rather than a synchronous save and
     * does not itself confirm persistence completion.
     */
    void checkAutoSaveImmediately();
}
