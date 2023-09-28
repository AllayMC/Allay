package cn.allay.api.item.interfaces.carpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCarpetStack extends ItemStack {
  ItemType<ItemLimeCarpetStack> LIME_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemLimeCarpetStack.class)
          .vanillaItem(VanillaItemId.LIME_CARPET)
          .build();
}
