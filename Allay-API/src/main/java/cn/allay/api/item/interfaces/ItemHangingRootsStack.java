package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHangingRootsStack extends ItemStack {
  ItemType<ItemHangingRootsStack> HANGING_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemHangingRootsStack.class)
          .vanillaItem(VanillaItemId.HANGING_ROOTS)
          .build();
}
