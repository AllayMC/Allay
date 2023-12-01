package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface Recipe<INPUT extends Input> {
    /**
     *
     * @param input 输入
     * @return 输入是否匹配配方
     */
    boolean match(INPUT input);

    /**
     * @return 此配方的输出
     */
    ItemStack[] getOutputs();

    CraftingDataType getType();
}
