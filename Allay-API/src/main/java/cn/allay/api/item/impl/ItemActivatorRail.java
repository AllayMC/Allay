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
public interface ItemActivatorRail extends ItemStack {
  ItemType<ItemActivatorRail> TYPE = ItemTypeBuilder
          .builder(ItemActivatorRail.class)
          .vanillaItem(VanillaItemId.ACTIVATOR_RAIL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
