package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedRootsStack extends ItemStack {
  ItemType<ItemWarpedRootsStack> WARPED_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemWarpedRootsStack.class)
          .vanillaItem(VanillaItemId.WARPED_ROOTS)
          .build();
}
