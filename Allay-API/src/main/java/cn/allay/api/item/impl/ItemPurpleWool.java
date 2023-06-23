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
public interface ItemPurpleWool extends ItemStack {
  ItemType<ItemPurpleWool> TYPE = ItemTypeBuilder
          .builder(ItemPurpleWool.class)
          .vanillaItem(VanillaItemId.PURPLE_WOOL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
