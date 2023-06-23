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
public interface ItemCaveVinesBodyWithBerries extends ItemStack {
  ItemType<ItemCaveVinesBodyWithBerries> TYPE = ItemTypeBuilder
          .builder(ItemCaveVinesBodyWithBerries.class)
          .vanillaItem(VanillaItemId.CAVE_VINES_BODY_WITH_BERRIES, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
