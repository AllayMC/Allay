package cn.allay.api.item.interfaces.stainedglass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownStainedGlassStack extends ItemStack {
  ItemType<ItemBrownStainedGlassStack> BROWN_STAINED_GLASS_TYPE = ItemTypeBuilder
          .builder(ItemBrownStainedGlassStack.class)
          .vanillaItem(VanillaItemId.BROWN_STAINED_GLASS)
          .build();
}
