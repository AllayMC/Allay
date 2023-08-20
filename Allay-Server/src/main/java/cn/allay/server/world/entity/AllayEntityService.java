package cn.allay.server.world.entity;

import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;
import cn.allay.api.world.World;
import cn.allay.api.world.entity.EntityService;
import lombok.Getter;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/7/2
 *
 * @author daoge_cmd
 */
@Getter
public class AllayEntityService implements EntityService {

    private final World world;
    private final Map<Long, Entity> entities = new ConcurrentHashMap<>();

    public AllayEntityService(World world) {
        this.world = world;
    }

    @Override
    public void tick() {
        entities.values().forEach(Entity::tick);
    }

    @Override
    public void addEntity(Entity entity) {
        entities.put(entity.getUniqueId(), entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        entities.remove(entity.getUniqueId());
    }

    @Override
    public Entity removeEntity(long entityUniqueId) {
        return entities.remove(entityUniqueId);
    }

    @Override
    @UnmodifiableView
    public Map<Long, Entity> getEntities() {
        return Collections.unmodifiableMap(entities);
    }
}
