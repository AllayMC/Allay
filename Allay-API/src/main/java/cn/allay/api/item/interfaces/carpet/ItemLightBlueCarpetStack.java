package cn.allay.api.item.interfaces.carpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCarpetStack extends ItemStack {
  ItemType<ItemLightBlueCarpetStack> LIGHT_BLUE_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueCarpetStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_CARPET)
          .build();
}
