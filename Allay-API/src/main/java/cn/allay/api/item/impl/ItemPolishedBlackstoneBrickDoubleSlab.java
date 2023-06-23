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
public interface ItemPolishedBlackstoneBrickDoubleSlab extends ItemStack {
  ItemType<ItemPolishedBlackstoneBrickDoubleSlab> TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneBrickDoubleSlab.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
