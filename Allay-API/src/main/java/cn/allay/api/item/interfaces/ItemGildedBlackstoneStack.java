package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGildedBlackstoneStack extends ItemStack {
  ItemType<ItemGildedBlackstoneStack> GILDED_BLACKSTONE_TYPE = ItemTypeBuilder
          .builder(ItemGildedBlackstoneStack.class)
          .vanillaItem(VanillaItemId.GILDED_BLACKSTONE)
          .build();
}
