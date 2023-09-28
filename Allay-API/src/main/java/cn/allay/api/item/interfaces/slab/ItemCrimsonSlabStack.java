package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonSlabStack extends ItemStack {
  ItemType<ItemCrimsonSlabStack> CRIMSON_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonSlabStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_SLAB)
          .build();
}
