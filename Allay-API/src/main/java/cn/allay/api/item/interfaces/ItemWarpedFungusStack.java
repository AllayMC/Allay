package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFungusStack extends ItemStack {
  ItemType<ItemWarpedFungusStack> WARPED_FUNGUS_TYPE = ItemTypeBuilder
          .builder(ItemWarpedFungusStack.class)
          .vanillaItem(VanillaItemId.WARPED_FUNGUS)
          .build();
}
