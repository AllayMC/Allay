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
public interface ItemElement48 extends ItemStack {
  ItemType<ItemElement48> TYPE = ItemTypeBuilder
          .builder(ItemElement48.class)
          .vanillaItem(VanillaItemId.ELEMENT_48, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
