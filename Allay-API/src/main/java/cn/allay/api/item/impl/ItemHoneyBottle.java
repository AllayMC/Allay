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
public interface ItemHoneyBottle extends ItemStack {
  ItemType<ItemHoneyBottle> TYPE = ItemTypeBuilder
          .builder(ItemHoneyBottle.class)
          .vanillaItem(VanillaItemId.HONEY_BOTTLE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
