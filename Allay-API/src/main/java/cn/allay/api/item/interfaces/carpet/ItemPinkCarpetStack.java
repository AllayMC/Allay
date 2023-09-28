package cn.allay.api.item.interfaces.carpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCarpetStack extends ItemStack {
  ItemType<ItemPinkCarpetStack> PINK_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemPinkCarpetStack.class)
          .vanillaItem(VanillaItemId.PINK_CARPET)
          .build();
}
