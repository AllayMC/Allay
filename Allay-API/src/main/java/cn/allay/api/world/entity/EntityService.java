package cn.allay.api.world.entity;

import cn.allay.api.entity.Entity;
import cn.allay.api.world.World;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/2
 *
 * @author daoge_cmd
 */
public interface EntityService {

    void tick();

    void addEntity(Entity entity);

    void removeEntity(Entity entity);

    @Nullable
    Entity removeEntity(long entityUniqueId);

    @UnmodifiableView
    Map<Long, Entity> getEntities();

    World getWorld();
}
