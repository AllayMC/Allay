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
public interface ItemDoubleStoneBlockSlab2 extends ItemStack {
  ItemType<ItemDoubleStoneBlockSlab2> TYPE = ItemTypeBuilder
          .builder(ItemDoubleStoneBlockSlab2.class)
          .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB2, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
