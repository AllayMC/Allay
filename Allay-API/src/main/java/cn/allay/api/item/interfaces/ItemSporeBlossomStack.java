package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSporeBlossomStack extends ItemStack {
  ItemType<ItemSporeBlossomStack> SPORE_BLOSSOM_TYPE = ItemTypeBuilder
          .builder(ItemSporeBlossomStack.class)
          .vanillaItem(VanillaItemId.SPORE_BLOSSOM)
          .build();
}
