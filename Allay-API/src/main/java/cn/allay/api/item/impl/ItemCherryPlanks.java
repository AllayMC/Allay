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
public interface ItemCherryPlanks extends ItemStack {
  ItemType<ItemCherryPlanks> TYPE = ItemTypeBuilder
          .builder(ItemCherryPlanks.class)
          .vanillaItem(VanillaItemId.CHERRY_PLANKS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
