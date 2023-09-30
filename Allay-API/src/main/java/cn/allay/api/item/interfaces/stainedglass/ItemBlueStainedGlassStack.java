package cn.allay.api.item.interfaces.stainedglass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueStainedGlassStack extends ItemStack {
  ItemType<ItemBlueStainedGlassStack> BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
          .builder(ItemBlueStainedGlassStack.class)
          .vanillaItem(VanillaItemId.BLUE_STAINED_GLASS)
          .build();
}
