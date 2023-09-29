package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenHoeStack extends ItemStack {
  ItemType<ItemWoodenHoeStack> WOODEN_HOE_TYPE = ItemTypeBuilder
          .builder(ItemWoodenHoeStack.class)
          .vanillaItem(VanillaItemId.WOODEN_HOE)
          .build();
}
