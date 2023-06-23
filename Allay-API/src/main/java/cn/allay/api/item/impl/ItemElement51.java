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
public interface ItemElement51 extends ItemStack {
  ItemType<ItemElement51> TYPE = ItemTypeBuilder
          .builder(ItemElement51.class)
          .vanillaItem(VanillaItemId.ELEMENT_51, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
