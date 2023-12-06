package org.allaymc.api.block.component;

import org.allaymc.api.ApiInstanceHolder;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface BlockComponentImplFactory {
    ApiInstanceHolder<BlockComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static BlockComponentImplFactory getFactory() {
        return FACTORY.get();
    }


}
