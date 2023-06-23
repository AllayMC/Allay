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
public interface ItemItemCampfire extends ItemStack {
  ItemType<ItemItemCampfire> TYPE = ItemTypeBuilder
          .builder(ItemItemCampfire.class)
          .vanillaItem(VanillaItemId.ITEM_CAMPFIRE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
