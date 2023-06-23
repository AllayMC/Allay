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
public interface ItemLightBlueCarpet extends ItemStack {
  ItemType<ItemLightBlueCarpet> TYPE = ItemTypeBuilder
          .builder(ItemLightBlueCarpet.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_CARPET, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
