package cn.allay.entity.type;

import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.entity.Entity;
import cn.allay.entity.component.EntityComponentImpl;
import cn.allay.identifier.Identified;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityType<T extends Entity> extends Identified {
    List<ComponentProvider<? extends EntityComponentImpl>> getComponentProviders();

    T createEntity(EntityInitInfo info);

    //register()
}
