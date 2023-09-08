package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.identifier.Identified;

import java.util.List;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    List<ComponentProvider<? extends EntityComponent>> getComponentProviders();

    T createEntity(EntityInitInfo<T> info);
}
