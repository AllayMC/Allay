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
public interface ItemElement14 extends ItemStack {
  ItemType<ItemElement14> TYPE = ItemTypeBuilder
          .builder(ItemElement14.class)
          .vanillaItem(VanillaItemId.ELEMENT_14, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
