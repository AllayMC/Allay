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
public interface ItemDeepslateTileDoubleSlab extends ItemStack {
  ItemType<ItemDeepslateTileDoubleSlab> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateTileDoubleSlab.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_TILE_DOUBLE_SLAB, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
