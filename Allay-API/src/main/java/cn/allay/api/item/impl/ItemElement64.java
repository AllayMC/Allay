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
public interface ItemElement64 extends ItemStack {
  ItemType<ItemElement64> TYPE = ItemTypeBuilder
          .builder(ItemElement64.class)
          .vanillaItem(VanillaItemId.ELEMENT_64, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
