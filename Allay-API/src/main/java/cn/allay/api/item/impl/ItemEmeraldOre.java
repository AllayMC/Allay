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
public interface ItemEmeraldOre extends ItemStack {
  ItemType<ItemEmeraldOre> TYPE = ItemTypeBuilder
          .builder(ItemEmeraldOre.class)
          .vanillaItem(VanillaItemId.EMERALD_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
