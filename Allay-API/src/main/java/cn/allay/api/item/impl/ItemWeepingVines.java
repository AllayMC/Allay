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
public interface ItemWeepingVines extends ItemStack {
  ItemType<ItemWeepingVines> TYPE = ItemTypeBuilder
          .builder(ItemWeepingVines.class)
          .vanillaItem(VanillaItemId.WEEPING_VINES, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
