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
public interface ItemElement6 extends ItemStack {
  ItemType<ItemElement6> TYPE = ItemTypeBuilder
          .builder(ItemElement6.class)
          .vanillaItem(VanillaItemId.ELEMENT_6, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
