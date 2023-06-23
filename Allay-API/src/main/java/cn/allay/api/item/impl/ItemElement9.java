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
public interface ItemElement9 extends ItemStack {
  ItemType<ItemElement9> TYPE = ItemTypeBuilder
          .builder(ItemElement9.class)
          .vanillaItem(VanillaItemId.ELEMENT_9, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
