package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identified;

import java.util.List;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityType<T extends Entity> extends Identified {
    List<ComponentProvider<? extends EntityComponentImpl>> getComponentProviders();

    T createEntity(EntityInitInfo info);

    default EntityType<T> register(EntityTypeRegistry registry) {
        registry.register(getIdentifier(), this);
        return this;
    }
}
