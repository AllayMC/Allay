package org.allaymc.api.entity.type;

import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.utils.Identified;

import java.util.List;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    List<ComponentProvider<? extends EntityComponent>> getComponentProviders();

    T createEntity(EntityInitInfo info);
}
