package org.allaymc.server.world.service;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityUpdateService;
import org.allaymc.server.world.chunk.AllayChunk;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public class AllayEntityUpdateService implements EntityUpdateService {
    protected final EntityPhysicsService entityPhysicsService;
    protected final Queue<EntityUpdateOperation> entityUpdateOperationQueue = new ConcurrentLinkedQueue<>();

    public AllayEntityUpdateService(EntityPhysicsService entityPhysicsService) {
        this.entityPhysicsService = entityPhysicsService;
    }

    @Override
    public void tick() {
        while (!entityUpdateOperationQueue.isEmpty()) {
            var operation = entityUpdateOperationQueue.poll();
            var entity = operation.entity;
            switch (operation.type) {
                case ADD -> addEntityImmediately(entity);
                case REMOVE -> removeEntityImmediately(entity);
            }
        }
    }

    private void removeEntityImmediately(Entity entity) {
        var chunk = (AllayChunk) entity.getCurrentChunk();
        if (chunk == null)
            throw new IllegalStateException("Trying to despawn an entity from an unload chunk!");
        entityPhysicsService.removeEntity(entity);
        chunk.removeEntity(entity.getUniqueId());
        entity.despawnFromAll();
        entity.setWillBeRemovedNextTick(false);
        entity.setSpawned(false);
    }

    private void addEntityImmediately(Entity entity) {
        var chunk = (AllayChunk) entity.getCurrentChunk();
        if (chunk == null)
            throw new IllegalStateException("Entity can't spawn in unloaded chunk!");
        chunk.addEntity(entity);
        entity.spawnTo(chunk.getPlayerChunkLoaders());
        entityPhysicsService.addEntity(entity);
        entity.setSpawned(true);
    }

    @Override
    public void addEntity(Entity entity) {
        entityUpdateOperationQueue.add(new EntityUpdateOperation(
                entity,
                EntityUpdateType.ADD
        ));
    }

    @Override
    public void removeEntity(Entity entity) {
        entity.setWillBeRemovedNextTick(true);
        entityUpdateOperationQueue.add(new EntityUpdateOperation(
                entity,
                EntityUpdateType.REMOVE
        ));
    }

    protected enum EntityUpdateType {
        ADD,
        REMOVE
    }

    protected record EntityUpdateOperation(Entity entity, EntityUpdateType type) {
    }
}
