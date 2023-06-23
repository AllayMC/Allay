package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitDeepslateRedstoneOre extends ItemStack {
  ItemType<ItemLitDeepslateRedstoneOre> TYPE = ItemTypeBuilder
          .builder(ItemLitDeepslateRedstoneOre.class)
          .vanillaItem(VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
