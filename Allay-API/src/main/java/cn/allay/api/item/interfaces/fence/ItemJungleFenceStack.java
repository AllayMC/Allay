package cn.allay.api.item.interfaces.fence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleFenceStack extends ItemStack {
  ItemType<ItemJungleFenceStack> JUNGLE_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemJungleFenceStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_FENCE)
          .build();
}
