package org.allaymc.server.world.service;

import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.eventbus.event.entity.EntityDespawnEvent;
import org.allaymc.api.eventbus.event.entity.EntitySpawnEvent;
import org.allaymc.api.world.service.EntityService;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.util.Queue;

/**
 * @author Cool_Loong
 */
@Slf4j
public class AllayEntityService implements EntityService {
    protected final AllayEntityPhysicsService entityPhysicsService;
    protected final Queue<EntityUpdateOperation> entityUpdateOperationQueue;

    public AllayEntityService(AllayEntityPhysicsService entityPhysicsService) {
        this.entityPhysicsService = entityPhysicsService;
        this.entityUpdateOperationQueue = PlatformDependent.newMpscQueue();
    }

    public void tick() {
        while (!entityUpdateOperationQueue.isEmpty()) {
            var operation = entityUpdateOperationQueue.poll();
            var entity = operation.entity();
            switch (operation.type()) {
                case ADD -> addEntityImmediately(entity);
                case REMOVE -> removeEntityImmediately(entity);
            }

            operation.callback().run();
        }
    }

    private void removeEntityImmediately(Entity entity) {
        new EntityDespawnEvent(entity).call();

        var unsafeChunk = (AllayUnsafeChunk) entity.getCurrentChunk().toUnsafeChunk();
        if (unsafeChunk == null) {
            throw new IllegalStateException("Trying to despawn an entity from an unload chunk!");
        }

        unsafeChunk.removeEntity(entity.getRuntimeId());
        entityPhysicsService.removeEntity(entity);
        entity.despawnFromAll();

        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.DESPAWNED);
    }

    private void addEntityImmediately(Entity entity) {
        new EntitySpawnEvent(entity).call();

        var unsafeChunk = (AllayUnsafeChunk) entity.getCurrentChunk().toUnsafeChunk();
        if (unsafeChunk == null) {
            throw new IllegalStateException("Entity can't spawn in unloaded chunk!");
        }

        unsafeChunk.addEntity(entity);
        entityPhysicsService.addEntity(entity);
        entity.spawnTo(unsafeChunk.getPlayerChunkLoaders());

        ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.ALIVE);
    }

    @Override
    public void addEntity(Entity entity, Runnable callback) {
        if (!entity.canBeSpawned()) {
            log.warn("Trying to add an entity which can't be added! Entity: {}", entity);
            return;
        }

        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.SPAWNED_NEXT_TICK)) {
            entityUpdateOperationQueue.add(new EntityUpdateOperation(entity, EntityUpdateType.ADD, callback));
        }
    }

    @Override
    public void removeEntity(Entity entity, Runnable callback) {
        if (((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setStatus(EntityStatus.DESPAWNED_NEXT_TICK)) {
            entityUpdateOperationQueue.add(new EntityUpdateOperation(entity, EntityUpdateType.REMOVE, callback));
        }
    }

    protected enum EntityUpdateType {
        ADD,
        REMOVE
    }

    protected record EntityUpdateOperation(Entity entity, EntityUpdateType type, Runnable callback) {}
}
