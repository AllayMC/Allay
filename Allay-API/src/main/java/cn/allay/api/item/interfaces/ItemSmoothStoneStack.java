package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothStoneStack extends ItemStack {
  ItemType<ItemSmoothStoneStack> SMOOTH_STONE_TYPE = ItemTypeBuilder
          .builder(ItemSmoothStoneStack.class)
          .vanillaItem(VanillaItemId.SMOOTH_STONE)
          .build();
}
