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
public interface ItemDeepslateCoalOre extends ItemStack {
  ItemType<ItemDeepslateCoalOre> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateCoalOre.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_COAL_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
