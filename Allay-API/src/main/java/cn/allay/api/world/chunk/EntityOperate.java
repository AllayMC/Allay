package cn.allay.api.world.chunk;

import cn.allay.api.entity.Entity;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Allay Project 2023/8/26
 *
 * @author daoge_cmd
 */
public interface EntityOperate {
    void addEntity(Entity entity);

    @Nullable
    Entity removeEntity(Long uniqueId);

    @Nullable
    default Entity removeEntity(Entity entity) {
        return removeEntity(entity.getUniqueId());
    }

    Map<Long, Entity> getEntities();
}
