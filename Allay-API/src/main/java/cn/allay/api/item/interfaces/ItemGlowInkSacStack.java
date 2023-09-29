package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowInkSacStack extends ItemStack {
  ItemType<ItemGlowInkSacStack> GLOW_INK_SAC_TYPE = ItemTypeBuilder
          .builder(ItemGlowInkSacStack.class)
          .vanillaItem(VanillaItemId.GLOW_INK_SAC)
          .build();
}
