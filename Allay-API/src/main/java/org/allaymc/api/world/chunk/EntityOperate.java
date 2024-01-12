package org.allaymc.api.world.chunk;

import org.allaymc.api.entity.Entity;

import java.util.Map;

/**
 * Allay Project 2023/8/26
 *
 * @author daoge_cmd
 */
public interface EntityOperate {
    void addEntity(Entity entity);


    Entity removeEntity(Long uniqueId);


    default Entity removeEntity(Entity entity) {
        return removeEntity(entity.getUniqueId());
    }

    Map<Long, Entity> getEntities();
}
