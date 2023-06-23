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
public interface ItemLightGrayWool extends ItemStack {
  ItemType<ItemLightGrayWool> TYPE = ItemTypeBuilder
          .builder(ItemLightGrayWool.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_WOOL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
