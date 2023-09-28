package cn.allay.api.item.interfaces.shulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeShulkerBoxStack extends ItemStack {
  ItemType<ItemLimeShulkerBoxStack> LIME_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemLimeShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.LIME_SHULKER_BOX)
          .build();
}
