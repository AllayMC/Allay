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
public interface ItemDeepslateBrickStairs extends ItemStack {
  ItemType<ItemDeepslateBrickStairs> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateBrickStairs.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_STAIRS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
