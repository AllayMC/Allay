package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface Recipe {
    /**
     *
     * @param input 输入
     * @return 输入是否匹配配方
     */
    boolean match(Input input);

    /**
     * @return 此配方的输出
     */
    ItemStack[] getOutputs();

    /**
     * TODO
     * @return 当玩家获得哪些物品时将解锁此配方
     */
    ItemDescriptor[] getUnlockItems();

    /**
     * TODO
     * @return 配方解锁条件
     */
    String getUnlockCondition();
}
