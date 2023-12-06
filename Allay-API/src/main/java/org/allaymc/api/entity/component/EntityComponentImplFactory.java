package org.allaymc.api.entity.component;

import org.allaymc.api.ApiInstanceHolder;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface EntityComponentImplFactory {
    ApiInstanceHolder<EntityComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static EntityComponentImplFactory getFactory() {
        return FACTORY.get();
    }
}
