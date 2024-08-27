package org.allaymc.api.entity.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.utils.Identified;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    T createEntity(EntityInitInfo info);
}
