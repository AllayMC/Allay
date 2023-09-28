package cn.allay.api.item.interfaces.fence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFenceStack extends ItemStack {
  ItemType<ItemCrimsonFenceStack> CRIMSON_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonFenceStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_FENCE)
          .build();
}
