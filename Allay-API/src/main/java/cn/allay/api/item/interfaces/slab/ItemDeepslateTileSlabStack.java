package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileSlabStack extends ItemStack {
  ItemType<ItemDeepslateTileSlabStack> DEEPSLATE_TILE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateTileSlabStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_TILE_SLAB)
          .build();
}
