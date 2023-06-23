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
public interface ItemShroomlight extends ItemStack {
  ItemType<ItemShroomlight> TYPE = ItemTypeBuilder
          .builder(ItemShroomlight.class)
          .vanillaItem(VanillaItemId.SHROOMLIGHT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
