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
public interface ItemElement42 extends ItemStack {
  ItemType<ItemElement42> TYPE = ItemTypeBuilder
          .builder(ItemElement42.class)
          .vanillaItem(VanillaItemId.ELEMENT_42, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
