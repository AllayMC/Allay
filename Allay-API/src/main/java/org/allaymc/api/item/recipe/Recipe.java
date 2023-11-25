package org.allaymc.api.item.recipe;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface Recipe {

    /**
     * @return 此配方的标识符
     */
    Identifier getIdentifier();

    /**
     * @return 此配方的分组，若无分组则为null
     */
    @Nullable
    String getGroup();

    /**
     *
     * @param input 输入
     * @return 输入是否匹配配方
     */
    boolean match(Input input);

    /**
     * @return 此配方的输出
     */
    ItemStack getOutput();

    /**
     * @return 这个配方所适用的合成类型。例如，工作台配方应具有 "crafting_table" tag
     */
    String[] getTags();

    /**
     * @return 当玩家获得哪些物品时将解锁此配方
     */
    ItemDescriptor[] getUnlockItems();
}
