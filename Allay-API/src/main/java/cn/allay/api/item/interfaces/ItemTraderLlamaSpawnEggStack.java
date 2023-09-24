package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTraderLlamaSpawnEggStack extends ItemStack {
    ItemType<ItemTraderLlamaSpawnEggStack> TRADER_LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTraderLlamaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TRADER_LLAMA_SPAWN_EGG)
            .build();
}
