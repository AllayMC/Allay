package cn.allay.api.item.interfaces.fence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakFenceStack extends ItemStack {
  ItemType<ItemDarkOakFenceStack> DARK_OAK_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakFenceStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_FENCE)
          .build();
}
