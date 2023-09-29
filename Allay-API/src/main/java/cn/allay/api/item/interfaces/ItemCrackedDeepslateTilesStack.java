package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateTilesStack extends ItemStack {
  ItemType<ItemCrackedDeepslateTilesStack> CRACKED_DEEPSLATE_TILES_TYPE = ItemTypeBuilder
          .builder(ItemCrackedDeepslateTilesStack.class)
          .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_TILES)
          .build();
}
