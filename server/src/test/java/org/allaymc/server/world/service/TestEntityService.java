package org.allaymc.server.world.service;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.storage.WorldStorage;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public class TestEntityService extends AllayEntityService {
    public TestEntityService(Dimension dimension, WorldStorage worldStorage) {
        super(dimension, worldStorage);
    }

    @Override
    public void addEntity(Entity entity, Runnable callback) {}

    @Override
    public void removeEntity(Entity entity, Runnable callback) {}

    @Override
    public Entity getEntity(long runtimeId) {return null;}

    @Override
    public @UnmodifiableView Map<Long, Entity> getEntities() {return Map.of();}

    @Override
    public void forEachEntitiesInChunk(int chunkX, int chunkZ, Consumer<Entity> consumer) {}

    @Override
    public void forEachEntitiesInChunkImmediately(int chunkX, int chunkZ, Consumer<Entity> consumer) {}

    @Override
    public EntityPhysicsService getPhysicsService() {return null;}

    @Override
    public void checkAutoSaveImmediately() {}
}
