package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySlabStack extends ItemStack {
  ItemType<ItemCherrySlabStack> CHERRY_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCherrySlabStack.class)
          .vanillaItem(VanillaItemId.CHERRY_SLAB)
          .build();
}
