package org.allaymc.api.item.recipe;

import org.allaymc.api.item.recipe.input.Input;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Allay Project 2023/11/26
 *
 * @author daoge_cmd
 */
public interface NetworkRecipe extends Recipe {
    AtomicInteger NETWORK_ID_COUNTER = new AtomicInteger(1);

    static int assignNetworkId() {
        return NETWORK_ID_COUNTER.getAndIncrement();
    }

    /**
     * @return 配方的唯一网络id，由内部分配
     */
    int getNetworkId();
}
