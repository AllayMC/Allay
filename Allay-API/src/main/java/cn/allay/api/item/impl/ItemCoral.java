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
public interface ItemCoral extends ItemStack {
  ItemType<ItemCoral> TYPE = ItemTypeBuilder
          .builder(ItemCoral.class)
          .vanillaItem(VanillaItemId.CORAL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
