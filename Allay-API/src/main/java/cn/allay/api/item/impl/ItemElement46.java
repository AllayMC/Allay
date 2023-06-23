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
public interface ItemElement46 extends ItemStack {
  ItemType<ItemElement46> TYPE = ItemTypeBuilder
          .builder(ItemElement46.class)
          .vanillaItem(VanillaItemId.ELEMENT_46, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
