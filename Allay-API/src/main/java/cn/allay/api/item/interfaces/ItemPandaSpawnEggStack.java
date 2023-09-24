package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPandaSpawnEggStack extends ItemStack {
    ItemType<ItemPandaSpawnEggStack> PANDA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPandaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PANDA_SPAWN_EGG)
            .build();
}
