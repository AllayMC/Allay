package cn.allay.api.item.interfaces.llamaspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLlamaSpawnEggStack extends ItemStack {
    ItemType<ItemLlamaSpawnEggStack> LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemLlamaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.LLAMA_SPAWN_EGG)
            .build();
}
