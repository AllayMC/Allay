package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHoeStack extends ItemStack {
  ItemType<ItemDiamondHoeStack> DIAMOND_HOE_TYPE = ItemTypeBuilder
          .builder(ItemDiamondHoeStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_HOE)
          .build();
}
