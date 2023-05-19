package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantedGoldenApple extends ItemStack {
    ItemType<ItemEnchantedGoldenApple> TYPE = ItemTypeBuilder
            .builder(ItemEnchantedGoldenApple.class)
            .vanillaItem(VanillaItemId.ENCHANTED_GOLDEN_APPLE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
