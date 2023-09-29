package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronStack extends ItemStack {
  ItemType<ItemRawIronStack> RAW_IRON_TYPE = ItemTypeBuilder
          .builder(ItemRawIronStack.class)
          .vanillaItem(VanillaItemId.RAW_IRON)
          .build();
}
