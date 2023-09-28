package cn.allay.api.item.interfaces.shulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueShulkerBoxStack extends ItemStack {
  ItemType<ItemLightBlueShulkerBoxStack> LIGHT_BLUE_SHULKER_BOX_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueShulkerBoxStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_SHULKER_BOX)
          .build();
}
