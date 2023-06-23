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
public interface ItemSilverGlazedTerracotta extends ItemStack {
  ItemType<ItemSilverGlazedTerracotta> TYPE = ItemTypeBuilder
          .builder(ItemSilverGlazedTerracotta.class)
          .vanillaItem(VanillaItemId.SILVER_GLAZED_TERRACOTTA, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
