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
public interface ItemUnpoweredRepeater extends ItemStack {
  ItemType<ItemUnpoweredRepeater> TYPE = ItemTypeBuilder
          .builder(ItemUnpoweredRepeater.class)
          .vanillaItem(VanillaItemId.UNPOWERED_REPEATER, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
