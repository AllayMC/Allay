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
public interface ItemWhiteCandleCake extends ItemStack {
  ItemType<ItemWhiteCandleCake> TYPE = ItemTypeBuilder
          .builder(ItemWhiteCandleCake.class)
          .vanillaItem(VanillaItemId.WHITE_CANDLE_CAKE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
