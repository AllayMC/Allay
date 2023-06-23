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
public interface ItemElement96 extends ItemStack {
  ItemType<ItemElement96> TYPE = ItemTypeBuilder
          .builder(ItemElement96.class)
          .vanillaItem(VanillaItemId.ELEMENT_96, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
