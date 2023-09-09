package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishSpawnEggStack extends ItemStack {
    ItemType<ItemPufferfishSpawnEggStack> PUFFERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPufferfishSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PUFFERFISH_SPAWN_EGG)
            .build();
}
